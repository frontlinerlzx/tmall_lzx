package com.frontlinerlzx.tmall_lzx.dao;

import com.frontlinerlzx.tmall_lzx.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author lzx
 * @create 2019-08-31-10:23
 */
public interface UserDao extends JpaRepository<User, Integer> {
    User findByName(String name);
}
