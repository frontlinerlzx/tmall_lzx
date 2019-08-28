package com.frontlinerlzx.tmall_lzx.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author lzx
 * @create 2019-08-27-10:19
 */
@Controller
public class AdminPageController {

    @GetMapping("/admin")
    public String admin() {
        return "redirect:admin_category_list";
    }

    @GetMapping("/admin_category_list")
    public String categoryList() {
        return "admin/listCategory";
    }

    @GetMapping("/admin_category_edit")
    public String editcategory() {
        return "admin/editCategory";
    }
}
