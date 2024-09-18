package com.Jerry.springbootmall.controller;

import com.Jerry.springbootmall.dto.ProductRequest;
import com.Jerry.springbootmall.model.Product;
import com.Jerry.springbootmall.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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
    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody @Valid ProductRequest productRequest) {
        Integer productId = productService.createProduct(productRequest);
        Product product = productService.getProductById(productId);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

}