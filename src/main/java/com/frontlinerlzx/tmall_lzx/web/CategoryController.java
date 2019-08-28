package com.frontlinerlzx.tmall_lzx.web;

import com.frontlinerlzx.tmall_lzx.pojo.Category;
import com.frontlinerlzx.tmall_lzx.service.CategoryService;
import com.frontlinerlzx.tmall_lzx.util.ImageUtil;
import com.frontlinerlzx.tmall_lzx.util.PageNavigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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


    @GetMapping("/categories")
    public PageNavigator<Category> list(@RequestParam(value = "number", defaultValue = "0") int number, @RequestParam(value = "size", defaultValue = "5") int size) throws Exception {
        number = number < 0 ? 0 : number;
        return categoryService.list(number, size, 5);
    }

    @PostMapping("/categories")
    public Object add(Category bean, MultipartFile image, HttpServletRequest request) throws IOException {
        categoryService.add(bean);
        saveOrupdateImageFile(bean, image, request);
        return bean;
    }

    public void saveOrupdateImageFile(Category bean, MultipartFile image, HttpServletRequest request) throws IOException {
        //创建文件对象，获取保存路径和文件名
        File imageFile = new File(request.getServletContext().getRealPath("img/category"), bean.getId() + ".jpg");
        //检查是否存在该category文件夹，不存在就创建
        if (!imageFile.getParentFile().exists()) imageFile.getParentFile().mkdirs();
        //将缓存的image转换成imageFile
        image.transferTo(imageFile);
        //将各种图片强制转成真jpg格式
        BufferedImage img = ImageUtil.change2jpg(imageFile);
        //写出该文件
        ImageIO.write(img, "jpg", imageFile);
    }

    @DeleteMapping("/categories/{id}")
    public String delete(@PathVariable("id") int id, HttpServletRequest request) throws Exception {
        categoryService.delete(id);
        File file = new File(request.getServletContext().getRealPath("img/category"), id + ".jpg");
        file.delete();
        return null;
    }

    @GetMapping("/categories/{id}")
    public Category get(@PathVariable("id") int id) throws Exception {
        return categoryService.get(id);
    }

    @PutMapping("categories/{id}")
    public Object update(Category bean, MultipartFile image, HttpServletRequest request) throws IOException {
        bean.setName(request.getParameter("name"));
        categoryService.update(bean);
        if (image != null) saveOrupdateImageFile(bean, image, request);
        return bean;
    }

}
