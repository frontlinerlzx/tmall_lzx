package com.frontlinerlzx.tmall_lzx.service;

import com.frontlinerlzx.tmall_lzx.dao.ProductImageDao;
import com.frontlinerlzx.tmall_lzx.pojo.Product;
import com.frontlinerlzx.tmall_lzx.pojo.ProductImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lzx
 * @create 2019-08-30-10:50
 */
@Service
public class ProductImageService {
    @Autowired
    ProductImageDao productImageDao;


    public static final String type_single = "single";
    public static final String type_detail = "detail";


    public void add(ProductImage bean) {
        productImageDao.save(bean);
    }

    public void delete(int id) {
        productImageDao.delete(id);
    }

    public ProductImage get(int id) {
        return productImageDao.findOne(id);
    }

    public List<ProductImage> listSingleProductImages(Product product) {
        return productImageDao.findByProductAndTypeOrderByIdDesc(product, type_single);
    }

    public List<ProductImage> listDetailProductImages(Product product) {
        return productImageDao.findByProductAndTypeOrderByIdDesc(product, type_detail);
    }

    /**
     * 防止页面取图片显示的时候异常，当此产品没有图片就是一个空对象，如果有就拿出第一张
     *
     * @param product
     */
    public void setFirstProdutImage(Product product) {
        List<ProductImage> singleproductImageList = listSingleProductImages(product);
        if (!singleproductImageList.isEmpty())
            product.setFirstProductImage(singleproductImageList.get(0));
        else
            product.setFirstProductImage(new ProductImage());
    }

    /**
     * 在产品列表显示的时候把空指针的图片对象设置上
     *
     * @param listProduct
     */
    public void setFirstProdutImages(List<Product> listProduct) {
        for (Product product : listProduct)
            setFirstProdutImage(product);

    }


}
