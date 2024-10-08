package com.Jerry.springbootmall.service.impl;

import com.Jerry.springbootmall.dao.ProductDao;
import com.Jerry.springbootmall.dto.ProductQueryParam;
import com.Jerry.springbootmall.dto.ProductRequest;
import com.Jerry.springbootmall.model.Product;
import com.Jerry.springbootmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;
    @Override
    public Product getProductById(Integer productId) {
        return productDao.getProductById(productId);
    }

    @Override
    public Integer createProduct(ProductRequest productRequest) {
        return productDao.createProduct(productRequest);
    }

    @Override
    public void updateProduct(ProductRequest productRequest, Integer productId) {
         productDao.updateProduct(productRequest,productId);

    }

    @Override
    public void deleteProductById(Integer productId) {
        productDao.deleteProductById(productId);

    }

    @Override
    public List<Product> getAllProduct(ProductQueryParam productQueryParam) {
        return productDao.getAllProduct(productQueryParam);
    }

    @Override
    public Integer countProduct(ProductQueryParam productQueryParam) {
        return productDao.countProduct(productQueryParam);
    }
}
