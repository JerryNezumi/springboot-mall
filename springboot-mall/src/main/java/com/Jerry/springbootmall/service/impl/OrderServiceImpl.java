package com.Jerry.springbootmall.service.impl;

import com.Jerry.springbootmall.dao.OrderDao;
import com.Jerry.springbootmall.dao.ProductDao;
import com.Jerry.springbootmall.dto.BuyItem;
import com.Jerry.springbootmall.dto.CreateOrderRequest;
import com.Jerry.springbootmall.model.OrderItem;
import com.Jerry.springbootmall.model.Product;
import com.Jerry.springbootmall.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ProductDao productDao;



    @Transactional
    @Override
    public Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest) {
        int totalAmount = 0;
        List<OrderItem> list = new ArrayList<>();
        for(BuyItem buyItem : createOrderRequest.getBuyItemList()){
            //創建總價錢
            Product product = productDao.getProductById(buyItem.getProductId());
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
}