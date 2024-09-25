package com.Jerry.springbootmall.dao.impl;

import com.Jerry.springbootmall.dao.OrderDao;
import com.Jerry.springbootmall.dto.CreateOrderRequest;
import com.Jerry.springbootmall.model.Order;
import com.Jerry.springbootmall.model.OrderItem;
import com.Jerry.springbootmall.rowmapper.OrderItemRowMapper;
import com.Jerry.springbootmall.rowmapper.OrderRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class OrderDaoImpl implements OrderDao {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Integer createOrder(Integer userId,Integer totalAmount) {
        String sql = "INSERT INTO `order` (user_id,total_amount,created_date,last_modified_date) " +
                "VALUES (:userId,:totalAmount,:createdDate,:lastModifiedDate)";
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("totalAmount", totalAmount);
        map.put("createdDate", new Date());
        map.put("lastModifiedDate", new Date());
        KeyHolder keyHolder = new GeneratedKeyHolder();

         namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map),keyHolder);
         int orderId = keyHolder.getKey().intValue();
         return orderId;

    }

    @Override
    public void createOrderItems(Integer orderId, List<OrderItem> orderItemList) {
        for (OrderItem orderItem : orderItemList) {
            String sql = "INSERT INTO order_item(order_id,product_id,quantity,amount)" +
                    "VALUES (:orderId,:productId,:quantity,:amount)";
            Map<String, Object> map = new HashMap<>();
            map.put("orderId", orderId);
            map.put("productId", orderItem.getProductId());
            map.put("quantity",orderItem.getQuantity() );
            map.put("amount",orderItem.getAmount() );

        namedParameterJdbcTemplate.update(sql, map);}

    }

    @Override
    public Order getOrderById(Integer orderId) {
        String sql = "SELECT  order_id,user_id,total_amount,created_date,last_modified_date FROM" +
                " `order` WHERE order_id =:orderId";
        Map<String, Object> map = new HashMap<>();
        map.put("orderId", orderId);
        List<Order> order = namedParameterJdbcTemplate.query(sql, map, new OrderRowMapper());
        if(order.size()>0){
            return order.get(0);
        }else {
            return null;
        }

    }

    @Override
    public List<OrderItem> getOrderItemsByOrderId(Integer orderId) {
        String sql = "SELECT oi.order_item_id, oi.order_id, oi.product_id, oi.quantity, " +
                "oi.amount, p.product_name, p.image_url FROM order_item as oi " +
                "LEFT JOIN product as p ON oi.product_id = p.product_id " +
                "WHERE oi.order_id = :orderId";
        Map<String, Object> map = new HashMap<>();
        map.put("orderId", orderId);
        List<OrderItem> list = namedParameterJdbcTemplate.query(sql, map,new OrderItemRowMapper());
        return  list;

    }
}