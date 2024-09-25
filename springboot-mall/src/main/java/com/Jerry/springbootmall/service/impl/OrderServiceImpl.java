package com.Jerry.springbootmall.service.impl;

import com.Jerry.springbootmall.dao.OrderDao;
import com.Jerry.springbootmall.dao.ProductDao;
import com.Jerry.springbootmall.dao.UserDao;
import com.Jerry.springbootmall.dto.BuyItem;
import com.Jerry.springbootmall.dto.CreateOrderRequest;
import com.Jerry.springbootmall.model.Order;
import com.Jerry.springbootmall.model.OrderItem;
import com.Jerry.springbootmall.model.Product;
import com.Jerry.springbootmall.model.User;
import com.Jerry.springbootmall.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderServiceImpl implements OrderService {

    private final static Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private UserDao userDao;



    @Transactional
    @Override
    public Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest) {
        //先確認該User是否存在
        User user = userDao.getUserById(userId);
        if (user == null) {
            logger.warn("該{}User尚未註冊",userId);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        }
        int totalAmount = 0;
        List<OrderItem> list = new ArrayList<>();
        for(BuyItem buyItem : createOrderRequest.getBuyItemList()){


            Product product = productDao.getProductById(buyItem.getProductId());

            //檢查商品是否存在、庫存是否足夠
            if (product == null) {
                logger.warn("商品{}不存在",buyItem.getProductId());
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }
            if(product.getStock()<buyItem.getQuantity()){
                logger.warn("該{}商品庫存不足。剩餘庫存為{}，目前欲購買數量{}",product.getProductName(),
                        product.getStock(),buyItem.getQuantity());
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }

            //扣除商品庫存
            productDao.updateStock(product.getProductId(),product.getStock()- buyItem.getQuantity());


            //計算總金額
            int total = product.getPrice()*buyItem.getQuantity();
            totalAmount += total;

            //轉換BuyItem to OrderItem
            OrderItem orderItem = new OrderItem();
            orderItem.setProductId(buyItem.getProductId());
            orderItem.setQuantity(buyItem.getQuantity());
            orderItem.setAmount(total);
            list.add(orderItem);


        }
    //創建訂單
        Integer orderId =  orderDao.createOrder(userId,totalAmount);

        orderDao.createOrderItems(orderId,list);
        return orderId;
    }

    @Override
    public Order getOrderById(Integer orderId) {
        Order order = orderDao.getOrderById(orderId);
        List<OrderItem> orderItems = orderDao.getOrderItemsByOrderId(orderId);
        order.setOrderItemsList(orderItems);
        return order;

    }
}