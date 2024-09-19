package com.Jerry.springbootmall.controller;

import com.Jerry.springbootmall.constans.ProductCategory;
import com.Jerry.springbootmall.dto.ProductQueryParam;
import com.Jerry.springbootmall.dto.ProductRequest;
import com.Jerry.springbootmall.model.Product;
import com.Jerry.springbootmall.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class ProductController {
    @Autowired
    private ProductService productService;


    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts(
            //查詢條件
            @RequestParam(required = false) ProductCategory category,
            @RequestParam(required = false) String search,
            //排序 預設為新增時間 且降序
            @RequestParam(defaultValue ="created_date" ) String orderBy,
            @RequestParam(defaultValue ="desc") String sort
            ) {
        ProductQueryParam productQueryParam = new ProductQueryParam();
        productQueryParam.setCategory(category);
        productQueryParam.setSearch(search);
        productQueryParam.setOrderBy(orderBy);
        productQueryParam.setSort(sort);
        List<Product> allProduct = productService.getAllProduct(productQueryParam);
        return ResponseEntity.status(HttpStatus.OK).body(allProduct);
    }

    @GetMapping("/products/{product_id}")
    public ResponseEntity<Product> getProductById(@PathVariable Integer product_id) {
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
    @PutMapping("/products/{productId}")
    public ResponseEntity<Product> updateProduct(@RequestBody @Valid ProductRequest productRequest
    , @PathVariable Integer productId) {
        //檢查 product 是否存在
        Product product = productService.getProductById(productId);
        if (product == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        //修改商品的數據
        productService.updateProduct(productRequest, productId);
        Product upProduct = productService.getProductById(productId);
        return ResponseEntity.status(HttpStatus.OK).body(upProduct);
    }

    @DeleteMapping("/products/{productId}")
    public ResponseEntity<?> deleteProductById(@PathVariable Integer productId) {
        productService.deleteProductById(productId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }



}