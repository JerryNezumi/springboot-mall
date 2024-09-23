package com.Jerry.springbootmall.controller;

import com.Jerry.springbootmall.constans.ProductCategory;
import com.Jerry.springbootmall.dto.ProductRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
    //取得商品
    @Test
    public void getProductById_success() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/products/{productId}", 5);
            mockMvc.perform(requestBuilder)
                    .andDo(print())
                    .andExpect(jsonPath("$.productName",equalTo("BMW")))
                    .andExpect(jsonPath("$.productId",equalTo(5)))
                    .andExpect(jsonPath("$.price",notNullValue()))
                    .andExpect(jsonPath("$.stock",notNullValue()))
                    .andExpect(jsonPath("$.category",notNullValue()))
                    .andExpect(jsonPath("$.imageUrl",notNullValue()))
                    .andExpect(jsonPath("$.description",notNullValue()))
                    .andExpect(jsonPath("$.createTime",notNullValue()));
    }
    @Test
    public void getProductById_fail() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.
                get("/products/{productId}", 5555);
        mockMvc.perform(requestBuilder)
                .andExpect(status().is(404));

    }


    //創建商品
    @Transactional
    @Test
    public void createProduct_success() throws Exception {
        ProductRequest productRequest = new ProductRequest();
        productRequest.setProductName("Porsche");
        productRequest.setCategory(ProductCategory.FOOD);
        productRequest.setPrice(122222222);
        productRequest.setStock(100);
        productRequest.setImageUrl("http://test.com");
        String json = objectMapper.writeValueAsString(productRequest);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);
        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().is(201))
                .andExpect(jsonPath("$.productName",equalTo("Porsche")))
                .andExpect(jsonPath("$.productId",notNullValue()))
                .andExpect(jsonPath("$.price",notNullValue()))
                .andExpect(jsonPath("$.stock",notNullValue()))
                .andExpect(jsonPath("$.category",notNullValue()));
    }

    @Transactional
    @Test
    public void createProduct_fail() throws Exception {
        ProductRequest productRequest = new ProductRequest();
        productRequest.setProductName("Porsche");
        productRequest.setCategory(ProductCategory.CAR);
        String json = objectMapper.writeValueAsString(productRequest);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(400));

    }

    //更新商品

    @Transactional
    @Test
    public void updateProduct_success() throws Exception {
        ProductRequest productRequest = new ProductRequest();

        productRequest.setProductName("Porsche");
        productRequest.setCategory(ProductCategory.CAR);
        productRequest.setPrice(122222222);
        productRequest.setStock(100);
        productRequest.setImageUrl("http://test.com");
        String json = objectMapper.writeValueAsString(productRequest);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/products/{productId}", 5)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);
        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.productName",equalTo("Porsche")))
                .andExpect(jsonPath("$.productId",equalTo(5)))
                .andExpect(jsonPath("$.price",equalTo(122222222)))
                .andExpect(jsonPath("$.stock",equalTo(100)))
                .andExpect(jsonPath("$.category",equalTo("CAR")))
                .andExpect(jsonPath("$.imageUrl",equalTo("http://test.com")))
                .andExpect(jsonPath("$.description",nullValue()))
                .andExpect(jsonPath("$.createTime",notNullValue()))
                .andExpect(jsonPath("$.lastModifiedDate",notNullValue()));

    }

    @Transactional
    @Test
    public void updateProduct_fail() throws Exception {
        ProductRequest productRequest = new ProductRequest();
        productRequest.setProductName("Porsche");
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/products/{productId}", 100)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(productRequest));
        mockMvc.perform(requestBuilder)
                .andExpect(status().is(400));

    }

    //刪除商品

    @Transactional
    @Test
    public void deleteProduct_success() throws Exception {
        RequestBuilder requestBuilder =MockMvcRequestBuilders
                .delete("/products/{productId}", 5);

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(204));

    }

    @Transactional
    @Test
    public void deleteProduct_fail() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/products/{productId}", 100);
        mockMvc.perform(requestBuilder)
                .andExpect(status().is(204));

    }

    //查詢商品列表
    @Test
    public void getAllProducts_success() throws Exception {
    RequestBuilder requestBuilder = MockMvcRequestBuilders
            .get("/products");
    mockMvc.perform(requestBuilder)
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.limit",equalTo(5)))
            .andExpect(jsonPath("$.offset",equalTo(0)))
            .andExpect(jsonPath("$.total",notNullValue()))
            .andExpect(jsonPath("$.results",hasSize(5)));

    }

    @Test
    public void getAllProducts_filtering() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/products")
                .param("category","CAR")
                .param("search","M");
        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.limit",notNullValue()))
                .andExpect(jsonPath("$.offset",notNullValue()))
                .andExpect(jsonPath("$.total",equalTo(1)))
                .andExpect(jsonPath("$.results",hasSize(1)));
    }

    @Test
    public void getProduct_sorting() throws Exception {
        RequestBuilder requestBuilder =MockMvcRequestBuilders
                .get("/products")
                .param("sort","asc")
                .param("orderBy","price");
        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.limit",notNullValue()))
                .andExpect(jsonPath("$.offset",notNullValue()))
                .andExpect(jsonPath("$.total",notNullValue()))
                .andExpect(jsonPath("$.results",hasSize(5)))
                .andExpect(jsonPath("$.results[0].productId",equalTo(3)));

    }

    @Test
    public void getProduct_page() throws Exception {
        RequestBuilder requestBuilder  = MockMvcRequestBuilders
                .get("/products")
                .param("limit","2")
                .param("offset","2");
        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.limit",notNullValue()))
                .andExpect(jsonPath("$.offset",notNullValue()))
                .andExpect(jsonPath("$.total",notNullValue()))
                .andExpect(jsonPath("$.results",hasSize(2)))
                .andExpect(jsonPath("$.results[0].productId",equalTo(5)));

    }


}