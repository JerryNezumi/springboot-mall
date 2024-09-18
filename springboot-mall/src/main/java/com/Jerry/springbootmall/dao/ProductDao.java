package com.Jerry.springbootmall.dao;

import com.Jerry.springbootmall.dto.ProductRequest;
import com.Jerry.springbootmall.model.Product;


public interface ProductDao {
     Product getProductById(Integer productId);
     Integer createProduct(ProductRequest productRequest);
}
