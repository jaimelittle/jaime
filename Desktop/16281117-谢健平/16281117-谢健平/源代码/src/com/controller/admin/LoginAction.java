package com.controller.admin;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.service.*;
import com.entity.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/*后台登录*/
//定义为控制器
@Controller
// 设置路径
@RequestMapping("/admin/login")
public class LoginAction extends BaseAdminAction {

    @Resource
    private AdminService adminService;


    // 管理员  登录
    @RequestMapping("login.action")
    public String login(Map<String, Object> map) {
        String username = this.getRequest().getParameter("username");
        String password = this.getRequest().getParameter("password");
        String code = this.getRequest().getParameter("code");


        Object loginCpacha = this.getRequest().getSession().getAttribute("loginCpacha");
        if (loginCpacha == null) {
            map.put("message", "会话超时，请刷新页面！");
            map.put("path", "admin/login.jsp");
            return "Web/message";//跳转到WebContent目录下对应的JSP页面
        }
        if (!code.toUpperCase().equals(loginCpacha.toString().toUpperCase())) {
            map.put("message", "验证码错误！");
            map.put("path", "admin/login.jsp");
            return "Web/message";//跳转到WebContent目录下对应的JSP页面
        }

        Admin adminEntity = new Admin();
        adminEntity.setUsername(username);
        List<Admin> adminlist = adminService.getByCond(adminEntity);
        if (adminlist.size() == 0) {
            map.put("message", "用户名不存在");
            map.put("path", "admin/login.jsp");
            return "Web/message";//跳转到WebContent目录下对应的JSP页面
        } else {
            Admin admin = adminlist.get(0);
            if (password.equals(admin.getPassword())) {
                this.getRequest().getSession().setAttribute("adminid", admin.getAdminid());
                this.getRequest().getSession().setAttribute("adminname", admin.getUsername());
                this.getRequest().getSession().setAttribute("realname", admin.getRealname());
                return "redirect:/system/main.action";//跳转到action
                //return "admin/main";//跳转到WebContent目录下对应的JSP页面
            } else {
                map.put("message", "密码错误");
                return "admin/login";//跳转到WebContent目录下对应的JSP页面
            }
        }

    }


    // 管理员 退出登录
    @RequestMapping("exit.action")
    public String exit(HttpServletRequest request) {
        request.getSession().removeAttribute("adminid");
        request.getSession().removeAttribute("adminname");
        request.getSession().removeAttribute("realname");

        return "admin/login";//跳转到WebContent目录下对应的JSP页面
    }


}
