package com.frontlinerlzx.tmall_lzx.web;

import com.frontlinerlzx.tmall_lzx.pojo.User;
import com.frontlinerlzx.tmall_lzx.service.UserService;
import com.frontlinerlzx.tmall_lzx.util.PageNavigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lzx
 * @create 2019-08-31-10:37
 */
@RestController
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/users")
    public PageNavigator<User> list(@RequestParam(value = "number", defaultValue = "0") int number, @RequestParam(value = "size", defaultValue = "5") int size, @RequestParam(value = "size", defaultValue = "5") int navigatePages) throws Exception {
        number = number < 0 ? 0 : number;
        return userService.list(number, size, navigatePages);
    }
}
