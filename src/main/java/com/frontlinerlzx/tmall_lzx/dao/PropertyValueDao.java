package com.frontlinerlzx.tmall_lzx.dao;

import com.frontlinerlzx.tmall_lzx.pojo.Product;
import com.frontlinerlzx.tmall_lzx.pojo.Property;
import com.frontlinerlzx.tmall_lzx.pojo.PropertyValue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author lzx
 * @create 2019-08-30-20:15
 */
public interface PropertyValueDao extends JpaRepository<PropertyValue, Integer> {
    PropertyValue getByProductAndProperty(Product product, Property property);
    List<PropertyValue> findByProductOrderByIdDesc(Product product);
}
