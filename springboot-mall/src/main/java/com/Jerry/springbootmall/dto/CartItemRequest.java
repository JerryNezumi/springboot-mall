package com.Jerry.springbootmall.dto;

import jakarta.validation.constraints.NotNull;

public class CartItemRequest {

    @NotNull
    private Integer product_id;

    @NotNull
    private Integer quantity;

    @NotNull
    private Integer price;

    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}