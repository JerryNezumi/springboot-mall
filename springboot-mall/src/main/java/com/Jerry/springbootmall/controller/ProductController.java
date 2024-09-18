package com.Jerry.springbootmall.controller;

import com.Jerry.springbootmall.model.Product;
import com.Jerry.springbootmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName:ProductController
 * Package:com.Jerry.springbootmall.controller
 *
 * @author:Jerry Create 2024/9/18 下午 03:50
 */
@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/products/{product_id}")
    public ResponseEntity<Product> getProduct(@PathVariable Integer product_id) {
        Product product = productService.getProductById(product_id);
        if (product != null) {
            return ResponseEntity.status(200).body(product);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }


    }

}