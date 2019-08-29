package com.frontlinerlzx.tmall_lzx.service;

import com.frontlinerlzx.tmall_lzx.dao.ProductDao;
import com.frontlinerlzx.tmall_lzx.pojo.Category;
import com.frontlinerlzx.tmall_lzx.pojo.Product;
import com.frontlinerlzx.tmall_lzx.util.PageNavigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * @author lzx
 * @create 2019-08-29-19:34
 */
@Service
public class ProductService {

    @Autowired
    ProductDao productDao;

    @Autowired
    CategoryService categoryService;

    public void add(Product bean) {
        productDao.save(bean);
    }

    public void delete(int id) {
        productDao.delete(id);
    }

    public void update(Product bean) {
        productDao.save(bean);
    }

    public Product get(int id) {
        return productDao.findOne(id);
    }

    public PageNavigator list(int cid, int number, int size, int navigatePages) {
        Category category = categoryService.get(cid);
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(number, size, sort);
        Page<Product> pageFromJPA = productDao.findByCategory(category, pageable);
        return new PageNavigator(pageFromJPA, navigatePages);
    }
}
