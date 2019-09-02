package com.frontlinerlzx.tmall_lzx.web;

import com.frontlinerlzx.tmall_lzx.pojo.Product;
import com.frontlinerlzx.tmall_lzx.pojo.ProductImage;
import com.frontlinerlzx.tmall_lzx.service.ProductImageService;
import com.frontlinerlzx.tmall_lzx.service.ProductService;
import com.frontlinerlzx.tmall_lzx.util.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lzx
 * @create 2019-08-30-12:02
 */
@RestController
public class ProductImageController {
    @Autowired
    ProductImageService productImageService;
    @Autowired
    ProductService productService;

    /**
     * 查询
     *
     * @param type
     * @param pid
     * @return
     */
    @GetMapping("/products/{pid}/productImages")
    public List<ProductImage> list(@RequestParam("type") String type, @PathVariable("pid") int pid) throws Exception {
        Product product = productService.get(pid);
        if (type.equals(productImageService.type_single))
            return productImageService.listSingleProductImages(product);
        else if (type.equals(productImageService.type_detail))
            return productImageService.listDetailProductImages(product);
        return new ArrayList<ProductImage>();
    }

    @PostMapping("/productImages")
    public Object add(@RequestParam("pid") int pid, @RequestParam("type") String type, MultipartFile image, HttpServletRequest request) throws Exception {
        Product product = productService.get(pid);
        ProductImage bean = new ProductImage();
        bean.setProduct(product);
        bean.setType(type);
        productImageService.add(bean);


        StringBuffer path = new StringBuffer("img/");
        if (type.equals(productImageService.type_single))
            path.append("productSingle");
        else if (type.equals(productImageService.type_detail))
            path.append("productDetail");

        File file = new File(request.getServletContext().getRealPath(path.toString()), bean.getId() + ".jpg");
        String filename = file.getName();
        if (!file.getParentFile().exists())
            file.getParentFile().mkdirs();

        try {
            image.transferTo(file);
            BufferedImage img = ImageUtil.change2jpg(file);
            ImageIO.write(img, "jpg", file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (type.equals(productImageService.type_single)) {
            String smallFilePath = request.getServletContext().getRealPath("img/productSingle_small/" + filename);
            String middleFilePath = request.getServletContext().getRealPath("img/productSingle_middle/" + filename);
            File smallFile = new File(smallFilePath);
            File middleFile = new File(middleFilePath);
            if (!smallFile.getParentFile().exists()) smallFile.getParentFile().mkdirs();
            if (!middleFile.getParentFile().exists()) middleFile.getParentFile().mkdirs();
            ImageUtil.resizeImage(file, 56, 56, smallFile);
            ImageUtil.resizeImage(file, 217, 190, middleFile);
        }
        return bean;

    }

    @DeleteMapping("/productImages/{id}")
    public String delete(@PathVariable("id") int id, HttpServletRequest request) throws Exception {
        ProductImage bean = productImageService.get(id);
        productImageService.delete(id);
        String path = "img/";
        if (bean.getType().equals(productImageService.type_single))
            path = path + "productSingle/";
        else if (bean.getType().equals(productImageService.type_detail))
            path = path + "productDetail/";
        File file = new File(request.getServletContext().getRealPath(path + bean.getId() + ".jpg"));
        String filename = file.getName();
        file.delete();
        if (bean.getType().equals(productImageService.type_single)) {
            String smallFilePath = request.getServletContext().getRealPath("img/productSingle_small/" + filename);
            String middleFilePath = request.getServletContext().getRealPath("img/productSingle_middle/" + filename);
            File smallFile = new File(smallFilePath);
            File middleFile = new File(middleFilePath);
            smallFile.delete();
            middleFile.delete();
        }
        return null;
    }


}
