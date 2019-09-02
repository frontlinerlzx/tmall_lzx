package com.frontlinerlzx.tmall_lzx.dao;

import com.frontlinerlzx.tmall_lzx.pojo.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author lzx
 * @create 2019-08-31-14:40
 */
public interface OrderDao extends JpaRepository<Order, Integer> {
}
