package com.frontlinerlzx.tmall_lzx.service;

import com.frontlinerlzx.tmall_lzx.dao.OrderItemDao;
import com.frontlinerlzx.tmall_lzx.pojo.Order;
import com.frontlinerlzx.tmall_lzx.pojo.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lzx
 * @create 2019-08-31-14:41
 */
@Service
public class OrderItemService {

    @Autowired
    OrderItemDao orderItemDao;
    @Autowired
    ProductImageService productImageService;

    public void fill(List<Order> orders) {
        for (Order order : orders)
            fill(order);
    }

    public void fill(Order order) {
        List<OrderItem> orderItems = orderItemDao.findByOrderOrderByIdDesc(order);
        float total = 0;
        int totalNumber = 0;
        for (OrderItem orderItem : orderItems) {
            totalNumber += orderItem.getNumber();
            total = orderItem.getProduct().getPromotePrice() * orderItem.getNumber();
            productImageService.setFirstProdutImage(orderItem.getProduct());
        }
        order.setTotal(total);
        order.setTotalNumber(totalNumber);
        order.setOrderItems(orderItems);
    }

    public List<OrderItem> list(Order order) {
        return orderItemDao.findByOrderOrderByIdDesc(order);
    }
}
