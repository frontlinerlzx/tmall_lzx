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

import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    ProductImageService productImageService;

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

    public void fill(List<Category> categories) {
        for (Category category : categories)
            fill(category);
    }

    public void fill(Category category) {
        List<Product> products = listByCategory(category);
        productImageService.setFirstProdutImages(products);
        category.setProducts(products);
    }

    public void fillByRow(List<Category> categorys) {
        int productEachRowNumber = 8;
        for (Category category : categorys) {
            List<Product> products = category.getProducts();
            List<List<Product>> productByRow = new ArrayList<>();
            for (int i = 0; i < products.size(); i += productEachRowNumber) {
                int size = productEachRowNumber + i;
                size = size < products.size() ? size : products.size();
                List<Product> productsOfEachRow = products.subList(i, size);
                productByRow.add(productsOfEachRow);
            }
            category.setProductsByRow(productByRow);
        }
    }

    public List<Product> listByCategory(Category category) {
        return productDao.findByCategoryOrderById(category);
    }
}
