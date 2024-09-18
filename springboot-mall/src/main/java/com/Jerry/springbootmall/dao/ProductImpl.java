package com.Jerry.springbootmall.dao;

import com.Jerry.springbootmall.model.Product;
import com.Jerry.springbootmall.rowmapper.ProductRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName:txt
 * Package:com.Jerry.springbootmall.dao
 *
 * @Author:Jerry Create 2024/9/18 下午 03:26
 */
@Component
public class ProductImpl implements ProductDao {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Override
    public Product getProductById(Integer productId) {
        String sql="SELECT product_id,product_name, category, image_url," +
                " price, stock, description, created_date," +
                " last_modified_date FROM product WHERE product_id = :productId";
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("productId",productId);
        List<Product> product = namedParameterJdbcTemplate.query(sql, map, new ProductRowMapper());
        if(product.size()>0){
            return product.get(0);
        }else {
            return null;
        }
    }
}
