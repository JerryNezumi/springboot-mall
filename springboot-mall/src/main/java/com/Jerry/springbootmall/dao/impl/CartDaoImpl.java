package com.Jerry.springbootmall.dao.impl;

import com.Jerry.springbootmall.dao.CartDao;
import com.Jerry.springbootmall.dto.CartItemRequest;
import com.Jerry.springbootmall.model.CartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class CartDaoImpl implements CartDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Override
    public Integer createCart(Integer userId) {
        String sql ="INSERT INTO Cart(user_id,created_date,last_modified_date) " +
                "VALUES(:userId,:created_date,:last_modified_date)";
        Map<String, Object> map = new HashMap<>();
        map.put("userId",userId);
        map.put("created_date",new Date());
        map.put("last_modified_date",new Date());
        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql,new MapSqlParameterSource(map),keyHolder);
         int id = keyHolder.getKey().intValue();
         return id;

    }
    public Integer createCartItem( Integer cartId,CartItemRequest cartItemRequest) {
        String sql = "INSERT INTO CartItem(cart_id,product_id,quantity,price,created_date,last_modified_date)" +
                "VALUES(:cart_id,:product_id,:quantity,:price,:created_date,:last_modified_date)";
        Map<String, Object> map = new HashMap<>();
        map.put("cart_id",cartId);
        map.put("product_id",cartItemRequest.getProduct_id());
        map.put("quantity",cartItemRequest.getQuantity());
        map.put("price",cartItemRequest.getPrice());
        map.put("created_date",new Date());
        map.put("last_modified_date",new Date());
        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql,new MapSqlParameterSource(map),keyHolder);
        int id = keyHolder.getKey().intValue();
        return id;
    }
    public void deleteCartItem(Integer cartItemId) {
        String sql = "DELETE FROM CartItem WHERE cart_item_id = :cart_item_id";
        Map<String, Object> map = new HashMap<>();
        map.put("cart_item_id", cartItemId);
        namedParameterJdbcTemplate.update(sql, map);
    }
}