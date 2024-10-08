package com.Jerry.springbootmall.dao.impl;

import com.Jerry.springbootmall.dao.ProductDao;
import com.Jerry.springbootmall.dto.ProductQueryParam;
import com.Jerry.springbootmall.dto.ProductRequest;
import com.Jerry.springbootmall.model.Product;
import com.Jerry.springbootmall.rowmapper.ProductRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
public class ProductDaoImpl implements ProductDao {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Override
    public Product getProductById(Integer productId) {
        String sql="SELECT product_id,product_name, category, image_url," +
                " price, stock, description, created_date," +
                " last_modified_date FROM product WHERE product_id = :productId";
        Map<String,Object> map=new HashMap<>();
        map.put("productId",productId);
        List<Product> product = namedParameterJdbcTemplate.query(sql, map, new ProductRowMapper());
        if(product.size()>0){
            return product.get(0);
        }else {
            return null;
        }
    }

    @Override
    public Integer createProduct(ProductRequest productRequest) {
        String sql= "INSERT INTO product (product_name, category, image_url, " +
                "price, stock, description," +
                " created_date,last_modified_date) VALUES(:productName,:category,:imageUrl," +
                ":price,:stock,:description,:createDate,:lastModifiedDate) ";
        Map<String,Object> map=new HashMap<>();
        map.put("productName",productRequest.getProductName());
        map.put("category",productRequest.getCategory().toString());
        map.put("imageUrl",productRequest.getImageUrl());
        map.put("price",productRequest.getPrice());
        map.put("stock",productRequest.getStock());
        map.put("description",productRequest.getDescription());
        Date now = new Date();
        map.put("createDate",now);
        map.put("lastModifiedDate",now);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql,new MapSqlParameterSource(map),keyHolder);
        int productId=keyHolder.getKey().intValue();


        return productId;

    }

    @Override
    public void updateProduct(ProductRequest productRequest, Integer productId) {
        String sql = "UPDATE product SET product_name = :productName, " +
                "category=:category, image_url=:imageUrl, price=:price" +
                ", stock=:stock, description=:description" +
                ",last_modified_date=:lastModifiedDate WHERE product_id = :productId";
        Map<String,Object> map=new HashMap<>();
        map.put("productId",productId);
        map.put("productName",productRequest.getProductName());
        map.put("category",productRequest.getCategory().toString());
        map.put("imageUrl",productRequest.getImageUrl());
        map.put("price",productRequest.getPrice());
        map.put("stock",productRequest.getStock());
        map.put("description",productRequest.getDescription());
        Date now = new Date();
        map.put("lastModifiedDate",now);
        namedParameterJdbcTemplate.update(sql,map);

    }

    @Override
    public void deleteProductById(Integer productId) {
        String sql = "DELETE FROM product WHERE product_id = :productId";
        Map<String,Object> map=new HashMap<>();
        map.put("productId",productId);
        namedParameterJdbcTemplate.update(sql,map);
    }

    @Override
    public List<Product> getAllProduct(ProductQueryParam productQueryParam) {
        String sql = "SELECT product_id,product_name, " +
                "category, image_url, price, stock," +
                " description, created_date, last_modified_date" +
                " FROM product WHERE 1=1";
        Map<String,Object> map=new HashMap<>();
        sql=addFilteringSql(sql,map,productQueryParam);
        sql += " ORDER BY " + productQueryParam.getOrderBy() + " " + productQueryParam.getSort();
        sql += " LIMIT " + productQueryParam.getLimit() + " OFFSET " + productQueryParam.getOffset();
        List<Product> products = namedParameterJdbcTemplate.query(sql,map, new ProductRowMapper());
        return products;

    }

    @Override
    public Integer countProduct(ProductQueryParam productQueryParam) {
        String sql = "SELECT count(*) FROM product WHERE 1=1";
        Map<String,Object> map=new HashMap<>();
        sql=addFilteringSql(sql,map,productQueryParam);
        Integer total = namedParameterJdbcTemplate.queryForObject(sql, map, Integer.class);
        return total;

    }
    private String addFilteringSql(String sql,Map<String,Object> map,
                          ProductQueryParam productQueryParam){
        if(productQueryParam.getCategory()!=null){
            sql += " AND category = :category";
            map.put("category",productQueryParam.getCategory().name());

        }
        if(productQueryParam.getSearch()!=null){
            sql += " AND product_name LIKE :search";
            map.put("search", "%" +  productQueryParam.getSearch() + "%");
        }
        return sql;
    }

    @Override
    public void updateStock(Integer productId, Integer stock) {
        String sql = "UPDATE product SET stock = :stock, last_modified_date = :lastModifiedDate WHERE product_id = :productId";
        Map<String,Object> map=new HashMap<>();
        map.put("stock",stock);
        map.put("productId",productId);
        map.put("lastModifiedDate",new Date());
        namedParameterJdbcTemplate.update(sql,map);
    }
}
