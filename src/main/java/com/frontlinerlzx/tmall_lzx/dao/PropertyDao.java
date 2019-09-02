package com.frontlinerlzx.tmall_lzx.dao;

import com.frontlinerlzx.tmall_lzx.pojo.Category;
import com.frontlinerlzx.tmall_lzx.pojo.Property;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


/**
 * @author lzx
 * @create 2019-08-29-11:40
 */
public interface PropertyDao extends JpaRepository<Property, Integer> {
    Page<Property> findByCategory(Category category, Pageable pageable);
    List<Property> findByCategory(Category category);
}
