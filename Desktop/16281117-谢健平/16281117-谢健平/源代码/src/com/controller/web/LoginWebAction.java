package com.controller.web;

import com.entity.*;
import com.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.*;

//定义为控制器
@Controller
// 设置路径
@RequestMapping("/web")
public class LoginWebAction extends BaseWebAction {


    @Resource
    private UsersService usersService;
    // 注入AdminService 并getter setter


    /*-------------------------------------用户*/
    // 准备登录
    @RequestMapping("preLogin.action")
    public String prelogin() {
        this.front();
        return "Web/login";//跳转到WebContent目录下对应的JSP页面
    }

    // 用户登录
    @RequestMapping("login.action")
    public String login(Map<String, Object> map) {
        this.front();
        String username = this.getRequest().getParameter("username");
        String password = this.getRequest().getParameter("password");
        Users u = new Users();
        u.setUsername(username);
        List<Users> usersList = usersService.getByCond(u);
        if (usersList.size() == 0) {
            map.put("message", "用户名不存在");
            map.put("path", "/web/preLogin.action");
            return "Web/message";//跳转到WebContent目录下对应的JSP页面

        } else {
            Users users = usersList.get(0);
            if (password.equals(users.getPassword())) {
                this.getSession().setAttribute("userid", users.getUsersid());
                this.getSession().setAttribute("users", users);
                return "redirect:/web/index.action";//跳转到action
            } else {
                map.put("message", "密码错误");
                map.put("path", "/web/preLogin.action");
                return "Web/message";//跳转到WebContent目录下对应的JSP页面

            }
        }
    }


    // 退出登录
    @RequestMapping("exit.action")
    public String exit() {
        this.front();
        this.getSession().removeAttribute("userid");
        this.getSession().removeAttribute("users");
        return "redirect:/web/index.action";//跳转到action
    }


}
