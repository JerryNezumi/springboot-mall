package com.Jerry.springbootmall.service;

import com.Jerry.springbootmall.dto.ProductRequest;
import com.Jerry.springbootmall.model.Product;


public interface ProductService {
     Product getProductById(Integer productId);
     Integer createProduct(ProductRequest productRequest);
}
