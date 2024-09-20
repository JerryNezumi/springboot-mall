package com.Jerry.springbootmall.dao;

import com.Jerry.springbootmall.constans.ProductCategory;
import com.Jerry.springbootmall.dto.ProductQueryParam;
import com.Jerry.springbootmall.dto.ProductRequest;
import com.Jerry.springbootmall.model.Product;

import java.util.List;


public interface ProductDao {
     Product getProductById(Integer productId);
     Integer createProduct(ProductRequest productRequest);
     void updateProduct(ProductRequest productRequest, Integer productId);
     void deleteProductById(Integer productId);
     List<Product> getAllProduct(ProductQueryParam productQueryParam);
     Integer countProduct(ProductQueryParam productQueryParam);
}
