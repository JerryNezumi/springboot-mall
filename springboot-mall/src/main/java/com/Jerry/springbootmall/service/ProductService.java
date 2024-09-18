package com.Jerry.springbootmall.service;

import com.Jerry.springbootmall.model.Product;

/**
 * ClassName:ProductService
 * Package:com.Jerry.springbootmall.service
 *
 * @author:Jerry Create 2024/9/18 下午 03:49
 */
public interface ProductService {
    public Product getProductById(Integer productId);
}
