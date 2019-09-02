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

    @GetMapping("/admin_property_list")
    public String listProperty() {
        return "admin/listProperty";
    }

    @GetMapping("/admin_property_edit")
    public String editProperty() {
        return "admin/editProperty";
    }

    @GetMapping("/admin_product_list")
    public String listProduct() {
        return "admin/listProduct";
    }

    @GetMapping("/admin_product_edit")
    public String editProduct() {
        return "admin/editProduct";
    }

    @GetMapping("/admin_productImage_list")
    public String listProductImage() {
        return "admin/listProductImage";
    }

    @GetMapping("/admin_propertyValue_edit")
    public String editPropertyValue() {
        return "admin/editPropertyValue";
    }

    @GetMapping("/admin_user_list")
    public String listUser() {
        return "admin/listUser";
    }

    @GetMapping("/admin_order_list")
    public String listOrder() {
        return "admin/listOrder";
    }




}
