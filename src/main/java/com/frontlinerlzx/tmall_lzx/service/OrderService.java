package com.frontlinerlzx.tmall_lzx.service;

import com.frontlinerlzx.tmall_lzx.dao.OrderDao;
import com.frontlinerlzx.tmall_lzx.pojo.Order;
import com.frontlinerlzx.tmall_lzx.pojo.OrderItem;
import com.frontlinerlzx.tmall_lzx.util.PageNavigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lzx
 * @create 2019-08-31-14:41
 */
@Service
public class OrderService {
    public static final String waitPay = "waitPay";
    public static final String waitDelivery = "waitDelivery";
    public static final String waitConfirm = "waitConfirm";
    public static final String waitReview = "waitReview";
    public static final String finish = "finish";
    public static final String delete = "delete";

    @Autowired
    OrderDao orderDao;

    public PageNavigator list(int number, int size, int navigatePages) {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(number, size, sort);
        Page pageFromJPA = orderDao.findAll(pageable);
        return new PageNavigator<Order>(pageFromJPA, navigatePages);
    }

    /**
     * removerOrderFromOrderItem :由于order里面有orderitem，而且orderitem里面有order所以springmvc在包装成json数据的时候会无限循环，因此要置空
     *
     * @param orders
     */
    public void removerOrderFromOrderItem(List<Order> orders) {
        for (Order order : orders)
            removerOrderFromOrderItem(order);
    }

    public void removerOrderFromOrderItem(Order order) {
        List<OrderItem> orderItems = order.getOrderItems();
        for (OrderItem orderItem : orderItems) {
            orderItem.setOrder(null);
        }
    }

    public Order get(int id) {
        return orderDao.findOne(id);
    }

    public void update(Order bean) {
        orderDao.save(bean);
    }

}
