package com.frontlinerlzx.tmall_lzx.service;

import com.frontlinerlzx.tmall_lzx.dao.PropertyValueDao;
import com.frontlinerlzx.tmall_lzx.pojo.Product;
import com.frontlinerlzx.tmall_lzx.pojo.Property;
import com.frontlinerlzx.tmall_lzx.pojo.PropertyValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lzx
 * @create 2019-08-30-20:19
 */
@Service
public class PropertyValueService {

    @Autowired
    PropertyValueDao propertyValueDao;
    @Autowired
    PropertyService propertyService;

    public void update(PropertyValue bean) {
        propertyValueDao.save(bean);
    }

    /**
     * 为了防止拿出属性值是空指针
     *
     * @param product
     */
    public void init(Product product) {
        List<Property> propertyList = propertyService.list(product.getCategory());
        for (Property property : propertyList) {
            PropertyValue propertyValue = propertyValueDao.getByProductAndProperty(product, property);
            if (propertyValue == null) {
                PropertyValue bean = new PropertyValue();
                bean.setProduct(product);
                bean.setProperty(property);
                propertyValueDao.save(bean);
            }
        }
    }

    public List<PropertyValue> list(Product product) {
        return propertyValueDao.findByProductOrderByIdDesc(product);
    }

//    public PropertyValue getByPropertyAndProduct(Product product, Property property) {
//        return propertyValueDao.getByProductAndProperty(product,property);
//    }

}
