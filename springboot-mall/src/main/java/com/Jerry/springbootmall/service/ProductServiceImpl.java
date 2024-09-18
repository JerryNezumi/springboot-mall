package com.Jerry.springbootmall.service;

import com.Jerry.springbootmall.dao.ProductDao;
import com.Jerry.springbootmall.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * ClassName:ProductServiceImpl
 * Package:com.Jerry.springbootmall.service
 *
 * @Author:Jerry Create 2024/9/18 下午 03:49
 */
@Component
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductDao productDao;
    @Override
    public Product getProductById(Integer productId) {
        return productDao.getProductById(productId);
    }
}