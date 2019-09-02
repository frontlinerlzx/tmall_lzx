package com.frontlinerlzx.tmall_lzx.web;

import com.frontlinerlzx.tmall_lzx.pojo.Order;
import com.frontlinerlzx.tmall_lzx.service.OrderItemService;
import com.frontlinerlzx.tmall_lzx.service.OrderService;
import com.frontlinerlzx.tmall_lzx.util.PageNavigator;
import com.frontlinerlzx.tmall_lzx.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @author lzx
 * @create 2019-08-31-15:29
 */
@RestController
public class OrderController {

    @Autowired
    OrderService orderService;
    @Autowired
    OrderItemService orderItemService;

    @GetMapping("/orders")
    public PageNavigator<Order> list(@RequestParam(value = "number", defaultValue = "0") int number, @RequestParam(value = "size", defaultValue = "5") int size, @RequestParam(value = "navigatePages", defaultValue = "5") int navigatePages) throws Exception {
        number = number < 0 ? 0 : number;
        PageNavigator<Order> page = orderService.list(number, size, navigatePages);
        orderItemService.fill(page.getContent());
        orderService.removerOrderFromOrderItem(page.getContent());
        return page;
    }

    @PutMapping("/deliveryOrder/{oid}")
    public Object deliveryOrder(@PathVariable("oid") int id) {
        Order order = orderService.get(id);
        order.setDeliveryDate(new Date());
        order.setStatus(orderService.waitConfirm);
        orderService.update(order);
        return Result.success();

    }
}
