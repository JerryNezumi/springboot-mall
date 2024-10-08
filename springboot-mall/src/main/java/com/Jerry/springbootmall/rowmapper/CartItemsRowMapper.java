package com.Jerry.springbootmall.rowmapper;

import com.Jerry.springbootmall.model.CartItem;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CartItemsRowMapper implements RowMapper<CartItem> {
    @Override
    public CartItem mapRow(ResultSet rs, int rowNum) throws SQLException {
        CartItem cartItem = new CartItem();
        cartItem.setProduct_id(rs.getInt("product_id"));
        cartItem.setPrice(rs.getInt("price"));
        cartItem.setCart_item_id(rs.getInt("cart_item_id"));
        cartItem.setQuantity(rs.getInt("quantity"));
        return cartItem;

    }
}