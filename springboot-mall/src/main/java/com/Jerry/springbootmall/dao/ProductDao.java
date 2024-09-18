package com.Jerry.springbootmall.dao;

import com.Jerry.springbootmall.model.Product;


public interface ProductDao {
     Product getProductById(Integer productId);
}
