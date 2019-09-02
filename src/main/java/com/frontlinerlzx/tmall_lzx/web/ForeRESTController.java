package com.frontlinerlzx.tmall_lzx.web;

import com.frontlinerlzx.tmall_lzx.pojo.Category;
import com.frontlinerlzx.tmall_lzx.pojo.User;
import com.frontlinerlzx.tmall_lzx.service.CategoryService;
import com.frontlinerlzx.tmall_lzx.service.ProductService;
import com.frontlinerlzx.tmall_lzx.service.UserService;
import com.frontlinerlzx.tmall_lzx.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

import java.util.List;

/**
 * @author lzx
 * @create 2019-09-01-23:48
 */
@RestController
public class ForeRESTController {
    @Autowired
    CategoryService categoryService;

    @Autowired
    ProductService productService;

    @Autowired
    UserService userService;

    @GetMapping("/forehome")
    public Object home() {
        List<Category> categories = categoryService.list();
        productService.fill(categories);
        productService.fillByRow(categories);
        categoryService.removeCategoryFromProduct(categories);
        return categories;
    }

    @PostMapping("foreregister")
    public Object register(@RequestBody User user) {
        String name = user.getName();
        String Password = user.getPassword();
        name = HtmlUtils.htmlEscape(name);
        user.setName(name);
        boolean exsit = userService.isExist(name);
        if (exsit) {

            String message = "用户名已经被使用";
            return Result.fail(message);
        }
        user.setPassword(Password);
        userService.add(user);
        return Result.success();
    }

}
