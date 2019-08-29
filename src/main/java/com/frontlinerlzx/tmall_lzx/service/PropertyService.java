package com.frontlinerlzx.tmall_lzx.service;

import com.frontlinerlzx.tmall_lzx.dao.PropertyDao;
import com.frontlinerlzx.tmall_lzx.pojo.Category;
import com.frontlinerlzx.tmall_lzx.pojo.Property;
import com.frontlinerlzx.tmall_lzx.util.PageNavigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * @author lzx
 * @create 2019-08-29-11:54
 */
@Service
public class PropertyService {
    @Autowired
    CategoryService categoryService;

    @Autowired
    PropertyDao propertyDao;


    public void add(Property bean) {
        propertyDao.save(bean);
    }

    public void delete(int id) {
        propertyDao.delete(id);
    }

    public void update(Property bean) {
        propertyDao.save(bean);
    }

    public Property get(int id) {
        return propertyDao.findOne(id);
    }

    public PageNavigator<Property> list(int cid, int number, int size, int navigatePages) {
        Category category = categoryService.get(cid);
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(number, size, sort);
        Page<Property> pageFromJPA = propertyDao.findByCategory(category, pageable);
        return new PageNavigator<Property>(pageFromJPA, navigatePages);
    }


}
