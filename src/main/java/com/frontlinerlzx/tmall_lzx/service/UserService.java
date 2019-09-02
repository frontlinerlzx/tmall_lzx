package com.frontlinerlzx.tmall_lzx.service;

import com.frontlinerlzx.tmall_lzx.dao.UserDao;
import com.frontlinerlzx.tmall_lzx.pojo.User;
import com.frontlinerlzx.tmall_lzx.util.PageNavigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


/**
 * @author lzx
 * @create 2019-08-31-10:28
 */
@Service
public class UserService {
    @Autowired
    UserDao userDao;

    public PageNavigator<User> list(int number, int size, int navigatePages) {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(number, size, sort);
        Page pagefromJPA = userDao.findAll(pageable);
        return new PageNavigator(pagefromJPA, navigatePages);
    }

    public User getByName(String name) {
        return userDao.findByName(name);
    }

    public boolean isExist(String name) {
        User user = getByName(name);
        return user != null;
    }

    public void add(User user) {
        userDao.save(user);
    }
}
