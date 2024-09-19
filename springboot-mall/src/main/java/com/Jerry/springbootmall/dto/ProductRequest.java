package com.Jerry.springbootmall.dto;

import com.Jerry.springbootmall.constans.ProductCategory;
import jakarta.validation.constraints.NotNull;

public class ProductRequest {

    @NotNull
    private String productName;

    @NotNull
    private ProductCategory category;

    @NotNull
    private String imageUrl;

    @NotNull
    private Integer price;

    @NotNull
    private Integer stock;


    private String description;

    public @NotNull String getProductName() {
        return productName;
    }

    public void setProductName(@NotNull String productName) {
        this.productName = productName;
    }

    public @NotNull ProductCategory getCategory() {
        return category;
    }

    public void setCategory(@NotNull ProductCategory category) {
        this.category = category;
    }

    public @NotNull String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(@NotNull String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public @NotNull Integer getPrice() {
        return price;
    }

    public void setPrice(@NotNull Integer price) {
        this.price = price;
    }

    public @NotNull Integer getStock() {
        return stock;
    }

    public void setStock(@NotNull Integer stock) {
        this.stock = stock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}