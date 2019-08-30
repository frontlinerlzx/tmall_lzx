package com.frontlinerlzx.tmall_lzx.dao;

import com.frontlinerlzx.tmall_lzx.pojo.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author lzx
 * @create 2019-08-27-9:54
 * <p>
 * 分类dao接口继承jpa的接口可以拿到对数据库操作的所有方法
 */

public interface CategoryDao extends JpaRepository<Category, Integer> {
}
