package com.frontlinerlzx.tmall_lzx.web;

import com.frontlinerlzx.tmall_lzx.pojo.Category;
import com.frontlinerlzx.tmall_lzx.service.CategoryService;
import com.frontlinerlzx.tmall_lzx.util.PageNavigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lzx
 * @create 2019-08-27-10:03
 */
@RestController
public class CategoryController {

    @Autowired
    CategoryService categoryService;


//    @RequestMapping("/categories")
//    public List<Category> list() throws Exception {
//        return categoryService.list();
//    }


    @RequestMapping("/categories")
    public PageNavigator<Category> list(@RequestParam(value = "number", defaultValue = "0") int number, @RequestParam(value = "size", defaultValue = "5") int size) throws Exception {
        number = number < 0 ? 0 : number;
        return categoryService.list(number, size, 5);
    }

}
