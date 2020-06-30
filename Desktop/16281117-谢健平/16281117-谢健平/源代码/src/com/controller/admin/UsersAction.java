package com.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.service.UsersService;
import com.entity.Users;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

//定义为控制器
@Controller
// 设置路径
@RequestMapping("/admin/users")
public class UsersAction extends BaseAdminAction {
    // 注入AdminService
    @Resource
    private UsersService usersService;

    // 准备添加数据
/*
    @RequestMapping("create.action")
    public String create(Map<String, Object> map) {

        return "admin/users/add";//跳转到WebContent目录下对应的JSP页面
    }

    // 添加数据
    @RequestMapping("add.action")
    public String add(Users users) {
        usersService.insert(users);
        return "redirect:/admin/users/getAll.action";//跳转到action//跳转到action
    }
*/

    // 通过主键删除数据
    @RequestMapping("delete.action")
    public String delete(String id) {
        usersService.delete(id);
        return "redirect:/admin/users/getAll.action";//跳转到action//跳转到action
    }

   /* // 更新数据
    @RequestMapping("update.action")
    public String update(Users users) {
        usersService.update(users);
        return "redirect:/admin/users/getAll.action";//跳转到action//跳转到action
    }
*/
    // 显示全部数据
    @RequestMapping("getAll.action")
    public String getAll(HttpServletRequest request, Map<String, Object> map) {
        Map<String, Object> parameter = new HashMap<String, Object>();
        String keyword = request.getParameter("keyword");//搜索关键词
        if (keyword != null && keyword != "") parameter.put("keyword", keyword);//搜索参数


        int rowCountTotal = usersService.getRowSize(parameter);//获取总数
        int pageSize = 10;// 分页大小
        int pageNumber = 1; //设定页面参数,传递给JSP页面

        if (request.getParameter("pageNumber") != null && request.getParameter("pageNumber") != "") {
            pageNumber = Integer.parseInt(request.getParameter("pageNumber"));//当前页
        }
        parameter.put("limit", pageSize);//搜索参数
        parameter.put("start", (pageNumber - 1) * pageSize);//搜索参数
        List<Users> usersList = usersService.getAll(parameter);


        //设定页面参数,传递给JSP页面
        Map<String, Object> pager = new HashMap<String, Object>();
        int pageCount = 1;// 总页数
        // 计算总页数
        if (rowCountTotal % pageSize == 0) {
            pageCount = rowCountTotal / pageSize;
        } else {
            pageCount = rowCountTotal / pageSize + 1;
        }
        pager.put("pageNumber", pageNumber);//当前页
        pager.put("pageCount", pageCount);//总页数
        pager.put("rowCountTotal", rowCountTotal);//记录总条数

        map.put("keyword", keyword);
        map.put("pager", pager);

        map.put("usersList", usersList);
        return "admin/users/list";//跳转到WebContent目录下对应的JSP页面
    }

    // 按条件查询数据 (模糊查询)
/*    @RequestMapping("queryUsersByCond.action")
    public String queryUsersByCond(String cond, String name, Map<String, Object> map) {
        List<Users> usersList = new ArrayList<Users>();
        Users users = new Users();
        if (cond != null) {
            if ("username".equals(cond)) {
                users.setUsername(name);
                usersList = usersService.getUsersByLike(users);
            }
            if ("password".equals(cond)) {
                users.setPassword(name);
                usersList = usersService.getUsersByLike(users);
            }
            if ("realname".equals(cond)) {
                users.setRealname(name);
                usersList = usersService.getUsersByLike(users);
            }
            if ("sex".equals(cond)) {
                users.setSex(name);
                usersList = usersService.getUsersByLike(users);
            }
            if ("birthday".equals(cond)) {
                users.setBirthday(name);
                usersList = usersService.getUsersByLike(users);
            }
            if ("contact".equals(cond)) {
                users.setContact(name);
                usersList = usersService.getUsersByLike(users);
            }
            if ("image".equals(cond)) {
                users.setImage(name);
                usersList = usersService.getUsersByLike(users);
            }
            if ("regdate".equals(cond)) {
                users.setRegdate(name);
                usersList = usersService.getUsersByLike(users);
            }
        }
        map.put("usersList", usersList);
        return "admin/query";
    }*/

/*
    // 按主键查询数据
    @RequestMapping("getById.action")
    public String getUsersById(String id, Map<String, Object> map) {
        Users users = usersService.getById(id);
        map.put("users", users);
        return "admin/users/edit";//跳转到WebContent目录下对应的JSP页面
    }
*/


}
