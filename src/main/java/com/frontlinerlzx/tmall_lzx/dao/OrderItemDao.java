package com.frontlinerlzx.tmall_lzx.dao;

import com.frontlinerlzx.tmall_lzx.pojo.Order;
import com.frontlinerlzx.tmall_lzx.pojo.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author lzx
 * @create 2019-08-31-14:39
 */
public interface OrderItemDao extends JpaRepository<OrderItem, Integer> {
    List<OrderItem> findByOrderOrderByIdDesc(Order order);
}
