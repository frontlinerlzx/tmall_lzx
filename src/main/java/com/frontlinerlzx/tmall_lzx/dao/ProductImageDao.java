package com.frontlinerlzx.tmall_lzx.dao;

import com.frontlinerlzx.tmall_lzx.pojo.Product;
import com.frontlinerlzx.tmall_lzx.pojo.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author lzx
 * @create 2019-08-30-10:47
 */
public interface ProductImageDao extends JpaRepository<ProductImage, Integer> {
    List<ProductImage> findByProductAndTypeOrderByIdDesc(Product product, String type);
}
