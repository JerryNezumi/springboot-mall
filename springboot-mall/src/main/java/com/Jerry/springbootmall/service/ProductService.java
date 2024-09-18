package com.Jerry.springbootmall.service;

import com.Jerry.springbootmall.dto.ProductRequest;
import com.Jerry.springbootmall.model.Product;


public interface ProductService {
     Product getProductById(Integer productId);
     Integer createProduct(ProductRequest productRequest);
     void updateProduct(ProductRequest productRequest, Integer productId);
     void deleteProduct(Integer productId);
}
