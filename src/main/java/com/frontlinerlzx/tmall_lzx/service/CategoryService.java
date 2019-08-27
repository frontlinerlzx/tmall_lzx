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

    public PageNavigator<Category> list(int number, int size, int navigatePages) {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(number, size, sort);
        Page<Category> pageFromJPA = categoryDao.findAll(pageable);
        return new PageNavigator<Category>(pageFromJPA, navigatePages);

    }

}
