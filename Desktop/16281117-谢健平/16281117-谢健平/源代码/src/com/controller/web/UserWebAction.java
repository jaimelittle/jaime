package com.controller.web;

import com.entity.Users;
import com.service.UsersService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

//定义为控制器
@Controller
// 设置路径
@RequestMapping("/web")
public class UserWebAction extends BaseWebAction {


    @Resource
    private UsersService usersService;
    // 注入AdminService 并getter setter

    // 准备注册
    @RequestMapping("preReg.action")
    public String preReg() {
        this.front();
        return "Web/register";//跳转到WebContent目录下对应的JSP页面
    }

    // 用户注册
    @RequestMapping("register.action")
    public String register(Users users, Map<String, Object> map) {
        this.front();
        Users u = new Users();
        u.setUsername(users.getUsername());
        List<Users> usersList = usersService.getByCond(u);
        if (usersList.size() == 0) {

            users.setRegdate(new Date());
            usersService.insert(users);
            map.put("message", "注册成功,请登录");
            map.put("path", "/web/preLogin.action");
            return "Web/message";//跳转到WebContent目录下对应的JSP页面

        } else {
            map.put("message", "用户名已存在");
            map.put("path", "/web/preReg.action");
            return "Web/message";//跳转到WebContent目录下对应的JSP页面
        }
    }


    // 准备修改密码
    @RequestMapping("prePwd.action")
    public String prePwd() {
        this.front();
        if (this.getSession().getAttribute("userid") == null) {
            return "redirect:/web/preLogin.action";//跳转到action
        }
        return "users/editpwd";//跳转到WebContent目录下对应的JSP页面
    }

    // 修改密码
    @RequestMapping("editpwd.action")
    public String editpwd( Map<String, Object> map) {
        this.front();
        if (this.getSession().getAttribute("userid") == null) {
            return "redirect:/web/preLogin.action";//跳转到action
        }
        String userid = (String) this.getSession().getAttribute("userid");
        String password = this.getRequest().getParameter("password");
        String repassword = this.getRequest().getParameter("repassword");
        Users users = usersService.getById(userid);
        if (password.equals(users.getPassword())) {
            users.setPassword(repassword);
            usersService.update(users);
            map.put("message", "修改成功");
            map.put("path", "/web/prePwd.action");
            return "Web/message";//跳转到WebContent目录下对应的JSP页面
        } else {
            map.put("message", "旧密码错误");
            map.put("path", "/web/prePwd.action");
            return "Web/message";//跳转到WebContent目录下对应的JSP页面
        }
    }


    // 准备修改信息
    @RequestMapping("preInfo.action")
    public String preInfo() {
        this.front();
        if (this.getSession().getAttribute("userid") == null) {
            return "redirect:/web/preLogin.action";//跳转到action
        }
        return "users/editinfo";//跳转到WebContent目录下对应的JSP页面
    }

    // 修改信息
    @RequestMapping("editinfo.action")
    public String editinfo( Map<String, Object> map) {
        this.front();
        if (this.getSession().getAttribute("userid") == null) {
            return "redirect:/web/preLogin.action";//跳转到action
        }
        String userid = (String) this.getSession().getAttribute("userid");
        String realname = this.getRequest().getParameter("realname");
        String sex = this.getRequest().getParameter("sex");
        String birthday = this.getRequest().getParameter("birthday");
        String contact = this.getRequest().getParameter("contact");
        String image = this.getRequest().getParameter("image");
        Users users = usersService.getById(userid);
        users.setRealname(realname);
        users.setSex(sex);
        users.setBirthday(birthday);
        users.setContact(contact);
        users.setImage(image);

        usersService.update(users);
        map.put("message", "修改成功");
        map.put("path", "/web/preInfo.action");
        this.getSession().setAttribute("users", users);
        return "Web/message";//跳转到WebContent目录下对应的JSP页面

    }


}
