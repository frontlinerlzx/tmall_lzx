package com.frontlinerlzx.tmall_lzx.web;

import com.frontlinerlzx.tmall_lzx.pojo.Product;
import com.frontlinerlzx.tmall_lzx.pojo.PropertyValue;
import com.frontlinerlzx.tmall_lzx.service.ProductService;
import com.frontlinerlzx.tmall_lzx.service.PropertyValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author lzx
 * @create 2019-08-30-21:07
 */
@RestController
public class PropertyValueController {
    @Autowired
    PropertyValueService propertyValueService;

    @Autowired
    ProductService productService;

    @GetMapping("/products/{pid}/propertyValues")
    public List<PropertyValue> list(@PathVariable("pid") int pid) throws Exception {
        Product product = productService.get(pid);
        propertyValueService.init(product);
        return propertyValueService.list(product);
    }

    @PutMapping("/propertyValues")
    public Object update(@RequestBody PropertyValue bean) throws Exception {
        propertyValueService.update(bean);
        return bean;
    }

}
