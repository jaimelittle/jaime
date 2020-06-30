package com.controller.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.entity.*;
import com.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.service.BbsService;
import com.service.UsersService;
import com.entity.Bbs;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

//定义为控制器
@Controller
// 设置路径
@RequestMapping("/admin/bbs")
public class BbsAction extends BaseAdminAction {
    // 注入AdminService 并getter setter
    @Resource
    private BbsService bbsService;
    @Resource
    private UsersService usersService;



    // 准备添加数据
  /*  @RequestMapping("create.action")
    public String create(Map<String, Object> map) {
        List<Users> usersList = usersService.getAllUsers();
        map.put("usersList", usersList);
        return "admin/bbs/add";//跳转到WebContent目录下对应的JSP页面
    }

    // 添加数据
    @RequestMapping("add.action")
    public String add(Bbs bbs) {
        bbs.setBbsid(VeDate.getStringDatex());
        bbsService.insert(bbs);
        return "redirect:/admin/bbs/create.action";//跳转到action
    }*/

    // 通过主键删除数据
    @RequestMapping("delete.action")
    public String delete(String id) {
        bbsService.delete(id);
        return "redirect:/admin/bbs/getAll.action";//跳转到action
    }

    // 更新数据
    @RequestMapping("update.action")
    public String update(Bbs bbs) {
        bbsService.update(bbs);
        return "redirect:/admin/bbs/getAll.action";//跳转到action
    }

    // 显示全部数据
    @RequestMapping("getAll.action")
    public String getAll(HttpServletRequest request, Map<String, Object> map) {
        Map<String, Object> parameter = new HashMap<String, Object>();
        String keyword = request.getParameter("keyword");//搜索关键词
        if (keyword != null && keyword != "") parameter.put("keyword", keyword);//搜索参数


        int rowCountTotal = bbsService.getRowSize(parameter);//获取总数
        int pageSize = 10;// 分页大小
        int pageNumber = 1; //设定页面参数,传递给JSP页面

        if (request.getParameter("pageNumber") != null && request.getParameter("pageNumber") != "") {
            pageNumber = Integer.parseInt(request.getParameter("pageNumber"));//当前页
        }
        parameter.put("limit", pageSize);//搜索参数
        parameter.put("start", (pageNumber - 1) * pageSize);//搜索参数
        List<Bbs> bbsList = bbsService.getAll(parameter);




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


        map.put("bbsList", bbsList);
        return "admin/bbs/list";//跳转到WebContent目录下对应的JSP页面
    }

    // 按条件查询数据 (模糊查询)----备用
/*
    @RequestMapping("queryByCond.action")
    public String queryBbsByCond(String cond, String name, Map<String, Object> map) {
        List<Bbs> bbsList = new ArrayList<Bbs>();
        Bbs bbs = new Bbs();
        if (cond != null) {
            if ("usersid".equals(cond)) {
                bbs.setUsersid(name);
                bbsList = bbsService.getBbsByLike(bbs);
            }
            if ("title".equals(cond)) {
                bbs.setTitle(name);
                bbsList = bbsService.getBbsByLike(bbs);
            }
            if ("contents".equals(cond)) {
                bbs.setContents(name);
                bbsList = bbsService.getBbsByLike(bbs);
            }
            if ("addtime".equals(cond)) {
                bbs.setAddtime(name);
                bbsList = bbsService.getBbsByLike(bbs);
            }
            if ("hits".equals(cond)) {
                bbs.setHits(name);
                bbsList = bbsService.getBbsByLike(bbs);
            }
            if ("repnum".equals(cond)) {
                bbs.setRepnum(name);
                bbsList = bbsService.getBbsByLike(bbs);
            }
        }
        map.put("bbsList", bbsList);
        return "admin/query";
    }
*/

/*
    // 按主键查询数据
    @RequestMapping("getById.action")
    public String getById(String id, Map<String, Object> map) {
        Bbs bbs = bbsService.getById(id);
        map.put("bbs", bbs);
        List<Users> usersList = usersService.getAllUsers();
        map.put("usersList", usersList);
        return "admin/bbs/edit";//跳转到WebContent目录下对应的JSP页面
    }
*/

}
