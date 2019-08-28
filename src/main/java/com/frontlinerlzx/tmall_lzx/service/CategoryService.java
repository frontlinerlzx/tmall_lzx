package com.frontlinerlzx.tmall_lzx.service;

import com.frontlinerlzx.tmall_lzx.dao.CategoryDao;
import com.frontlinerlzx.tmall_lzx.pojo.Category;
import com.frontlinerlzx.tmall_lzx.util.PageNavigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lzx
 * @create 2019-08-27-9:57
 * 分类的业务层
 */

@Service
public class CategoryService {
    @Autowired
    CategoryDao categoryDao;

    public List<Category> list() {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        return categoryDao.findAll(sort);

    }

    /**
     * @param number        当前页码
     * @param size          一页分配显示的条目数量
     * @param navigatePages 导航栏页码显示数量
     * @return
     */
    public PageNavigator<Category> list(int number, int size, int navigatePages) {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(number, size, sort);
        Page<Category> pageFromJPA = categoryDao.findAll(pageable);
        return new PageNavigator<Category>(pageFromJPA, navigatePages);

    }

    /**
     * 添加category分类进数据库
     *
     * @param bean 商品分类的对象
     */
    public void add(Category bean) {
        categoryDao.save(bean);
    }

    public void delete(int id) {
        categoryDao.delete(id);
    }

    /**
     * @param id
     * @return 返回根据id查找的商品分类
     */
    public Category get(int id) {
        return categoryDao.findOne(id);
    }

    /**
     * 更新
     *
     * @param bean
     */
    public void update(Category bean) {
        categoryDao.save(bean);
    }
}
