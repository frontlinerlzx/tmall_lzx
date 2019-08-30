package com.frontlinerlzx.tmall_lzx.web;

import com.frontlinerlzx.tmall_lzx.pojo.Product;
import com.frontlinerlzx.tmall_lzx.service.ProductImageService;
import com.frontlinerlzx.tmall_lzx.service.ProductService;
import com.frontlinerlzx.tmall_lzx.util.PageNavigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author lzx
 * @create 2019-08-29-19:45
 */
@RestController
public class ProductController {

    @Autowired
    ProductService productService;
    @Autowired
    ProductImageService productImageService;

    @GetMapping("/categories/{cid}/products")
    public PageNavigator<Product> list(@PathVariable("cid") int cid, @RequestParam(value = "number", defaultValue = "0") int number, @RequestParam(value = "size", defaultValue = "3") int size) throws Exception {
        number = number < 0 ? 0 : number;
        PageNavigator page = productService.list(cid, number, size, 3);
        productImageService.setFirstProdutImages(page.getContent());

        return page;
    }

    @PostMapping("/products")
    public Object add(@RequestBody Product bean) throws Exception {
        productService.add(bean);
        return bean;
    }

    @DeleteMapping("/products/{id}")
    public String delete(@PathVariable("id") int id) throws Exception {
        productService.delete(id);
        return null;
    }


    @PutMapping("/products")
    public Object update(@RequestBody Product bean) throws Exception {
        productService.update(bean);
        return bean;
    }

    @GetMapping("/products/{id}")
    public Product get(@PathVariable("id") int id) throws Exception {
        return productService.get(id);

    }

}
