package com.frontlinerlzx.tmall_lzx.web;

import com.frontlinerlzx.tmall_lzx.pojo.Property;
import com.frontlinerlzx.tmall_lzx.service.PropertyService;
import com.frontlinerlzx.tmall_lzx.util.PageNavigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author lzx
 * @create 2019-08-29-12:59
 */
@RestController
public class PropertyController {

    @Autowired
    PropertyService propertyService;

    @GetMapping("/categories/{cid}/properties")
    public PageNavigator<Property> list(@PathVariable(name = "cid") int cid, @RequestParam(name = "number", defaultValue = "0") int number, @RequestParam(name = "size", defaultValue = "3") int size) throws Exception {
        number = number < 0 ? 0 : number;
        return propertyService.list(cid, number, size, 3);
    }

    @PostMapping("/properties")
    public Object add(@RequestBody Property bean) {
        System.out.println(bean);
        propertyService.add(bean);
        return bean;
    }


    @DeleteMapping("/properties/{id}")
    public String delete(@PathVariable("id") int id) throws Exception {
        propertyService.delete(id);
        return null;
    }

    @GetMapping("/properties/{id}")
    public Property get(@PathVariable("id") int id) throws Exception {
        return propertyService.get(id);

    }


    @PutMapping("/properties")

    public Object update(@RequestBody Property bean) throws Exception {
        System.out.println(bean);
        propertyService.update(bean);
        return bean;
    }


}
