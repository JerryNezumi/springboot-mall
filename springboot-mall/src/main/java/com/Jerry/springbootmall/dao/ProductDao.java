package com.Jerry.springbootmall.dao;

import com.Jerry.springbootmall.model.Product;
import org.springframework.stereotype.Component;

/**
 * ClassName:product
 * Package:com.Jerry.springbootmall.dao
 *
 * @Author:Jerry Create 2024/9/18 下午 03:19
 */

public interface ProductDao {
    public Product getProductById(Integer productId);
}
