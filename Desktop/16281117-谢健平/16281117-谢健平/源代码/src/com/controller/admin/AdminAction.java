package com.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.service.AdminService;
import com.entity.Admin;

//定义为控制器
@Controller
// 设置路径
@RequestMapping("/admin/admin")
public class AdminAction extends BaseAdminAction {
    // 注入AdminService
    @Resource
    private AdminService adminService;

    // 管理员登录 1 验证用户名是否存在 2 验证密码是否正确
	/*@RequestMapping("login.action")
	public String login(HttpServletRequest request) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Admin adminEntity = new Admin();
		adminEntity.setUsername(username);
		List<Admin> adminlist = adminService.getAdminByCond(adminEntity);
		if (adminlist.size() == 0) {
			request.setAttribute("message", "用户名不存在");
			return "admin/index";
		} else {
			Admin admin = adminlist.get(0);
			if (password.equals(admin.getPassword())) {
				request.getSession().setAttribute("adminid", admin.getAdminid());
				request.getSession().setAttribute("adminname", admin.getUsername());
				request.getSession().setAttribute("realname", admin.getRealname());
			} else {
				request.setAttribute("message", "密码错误");
				return "admin/index";
			}
		}
		return "admin/main";
	}*/

    // 修改密码
    @RequestMapping("editpwd.action")
    public String editpwd(HttpServletRequest request) {
        String adminid = (String) request.getSession().getAttribute("adminid");
        String password = request.getParameter("password");
        String repassword = request.getParameter("repassword");
        Admin admin = adminService.getById(adminid);
        if (password.equals(admin.getPassword())) {
            admin.setPassword(repassword);
            adminService.update(admin);
        } else {
            request.setAttribute("message", "旧密码错误");
        }
        return "admin/admin/editpwd";//跳转到WebContent目录下对应的JSP页面
    }


    // 准备添加数据
    @RequestMapping("create.action")
    public String create(Map<String, Object> map) {

        return "admin/admin/add";//跳转到WebContent目录下对应的JSP页面
    }

    // 添加数据
    @RequestMapping("add.action")
    public String add(Admin admin) {
        adminService.insert(admin);
        return "redirect:/admin/admin/getAll.action";//跳转到action
    }

    // 通过主键删除数据
    @RequestMapping("delete.action")
    public String delete(String id) {
        adminService.delete(id);
        return "redirect:/admin/admin/getAll.action";//跳转到action
    }

    // 更新数据
    @RequestMapping("update.action")
    public String update(Admin admin) {
        adminService.update(admin);
        return "redirect:/admin/admin/getAll.action";//跳转到action
    }

    // 显示全部数据
    @RequestMapping("getAll.action")
    public String getAll(HttpServletRequest request, Map<String, Object> map) {
        Map<String, Object> parameter = new HashMap<String, Object>();
        String keyword = request.getParameter("keyword");//搜索关键词
        if (keyword != null && keyword != "") parameter.put("keyword", keyword);//搜索参数


        int rowCountTotal = adminService.getRowSize(parameter);//获取总数
        int pageSize = 10;// 分页大小
        int pageNumber = 1; //设定页面参数,传递给JSP页面

        if (request.getParameter("pageNumber") != null && request.getParameter("pageNumber") != "") {
            pageNumber = Integer.parseInt(request.getParameter("pageNumber"));//当前页
        }
        parameter.put("limit", pageSize);//搜索参数
        parameter.put("start", (pageNumber - 1) * pageSize);//搜索参数
        List<Admin> adminList = adminService.getAll(parameter);


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


        map.put("adminList", adminList);
        map.put("keyword", keyword);
        map.put("pager", pager);
        return "admin/admin/list";//跳转到WebContent目录下对应的JSP页面
    }

    // 按条件查询数据 (模糊查询)----备用
    /*@RequestMapping("queryByCond.action")
    public String queryByCond(String cond, String name, Map<String, Object> map) {
        List<Admin> adminList = new ArrayList<Admin>();
        Admin admin = new Admin();
        if (cond != null) {
            if ("username".equals(cond)) {
                admin.setUsername(name);
                adminList = adminService.getByLike(admin);
            }
            if ("password".equals(cond)) {
                admin.setPassword(name);
                adminList = adminService.getByLike(admin);
            }
            if ("realname".equals(cond)) {
                admin.setRealname(name);
                adminList = adminService.getByLike(admin);
            }
            if ("contact".equals(cond)) {
                admin.setContact(name);
                adminList = adminService.getByLike(admin);
            }
        }
        map.put("adminList", adminList);
        return "admin/admin/list";//跳转到WebContent目录下对应的JSP页面
    }*/

    // 按主键查询数据
    @RequestMapping("getById.action")
    public String getById(String id, Map<String, Object> map) {
        Admin admin = adminService.getById(id);
        map.put("admin", admin);
        return "admin/admin/edit";//跳转到WebContent目录下对应的JSP页面
    }


}
