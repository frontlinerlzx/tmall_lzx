package com.frontlinerlzx.tmall_lzx.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

/**
 * @author lzx
 * @create 2019-08-27-9:40
 */

@Entity
@Table(name = "category")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})//忽略handler和hibernateLazyInitializer属性
public class Category {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;

    @Transient
    List<Product> products;
    @Transient
    List<List<Product>> productsByRow;

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<List<Product>> getProductsByRow() {
        return productsByRow;
    }

    public void setProductsByRow(List<List<Product>> productsByRow) {
        this.productsByRow = productsByRow;
    }

    public void removerCategoryFromProduct(List<Category> categories) {
        for (Category category : categories) {
            removerCategoryFromProduct(category);
        }
    }

    public void removerCategoryFromProduct(Category category) {
        List<Product> products = category.getProducts();
        if (products != null) {
            for (Product product : products)
                product.setCategory(null);

        }
        List<List<Product>> productsByRow = category.getProductsByRow();

        if (productsByRow != null) {
            for (List<Product> products1 : productsByRow)
                for (Product product : products1)
                    product.setCategory(null);
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
