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
import com.service.RebbsService;
import com.service.UsersService;
import com.entity.Rebbs;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

//定义为控制器
@Controller
// 设置路径
@RequestMapping("/admin/rebbs")
public class RebbsAction extends BaseAdminAction {
    // 注入AdminService 并getter setter
    @Resource
    private RebbsService rebbsService;
    @Resource
    private UsersService usersService;
    @Resource
    private BbsService bbsService;


    // 准备添加数据
 /*   @RequestMapping("create.action")
    public String create(Map<String, Object> map) {
        Map<String, Object> parameter = new HashMap<String, Object>();
        List<Users> usersList = usersService.getAllUsers(parameter);
        map.put("usersList", usersList);
        List<Bbs> bbsList = bbsService.getAllBbs(parameter);
        map.put("bbsList", bbsList);
        return "admin/bbs/addrebbs";
    }

    // 添加数据
    @RequestMapping("add.action")
    public String add(Rebbs rebbs) {
        rebbs.setRebbsid(VeDate.getStringDatex());
        rebbsService.insert(rebbs);
        return "redirect:/admin/rebbs/create.action";//跳转到action
    }*/

    // 通过主键删除数据
    @RequestMapping("delete.action")
    public String delete(String id) {

        Rebbs rebbs = rebbsService.getById(id);
        Bbs bbs = bbsService.getById(rebbs.getBbsid());
        bbs.setRepnum("" + (Integer.parseInt(bbs.getRepnum()) - 1));//删除主贴的回复数
        bbsService.update(bbs);

        rebbsService.delete(id);
        return "redirect:/admin/rebbs/getAll.action";//跳转到action
    }

    // 更新数据
    @RequestMapping("update.action")
    public String update(Rebbs rebbs) {
        rebbsService.update(rebbs);
        return "redirect:/admin/rebbs/getAll.action";//跳转到action
    }

    // 显示全部数据
    @RequestMapping("getAll.action")
    public String getAll(HttpServletRequest request, Map<String, Object> map) {
        Map<String, Object> parameter = new HashMap<String, Object>();
        String keyword = request.getParameter("keyword");//搜索关键词
        if (keyword != null && keyword != "") parameter.put("keyword", keyword);//搜索参数





        int rowCountTotal = rebbsService.getRowSize(parameter);//获取总数
        int pageSize = 10;// 分页大小
        int pageNumber = 1; //设定页面参数,传递给JSP页面

        if (request.getParameter("pageNumber") != null && request.getParameter("pageNumber") != "") {
            pageNumber = Integer.parseInt(request.getParameter("pageNumber"));//当前页
        }
        parameter.put("limit", pageSize);//搜索参数
        parameter.put("start", (pageNumber - 1) * pageSize);//搜索参数
        List<Rebbs> rebbsList = rebbsService.getAll(parameter);





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

        map.put("rebbsList", rebbsList);
        return "admin/bbs/listrebbs";//跳转到WebContent目录下对应的JSP页面
    }

    // 按条件查询数据 (模糊查询)----备用
/*
    @RequestMapping("queryRebbsByCond.action")
    public String queryRebbsByCond(String cond, String name, Map<String, Object> map) {
        List<Rebbs> rebbsList = new ArrayList<Rebbs>();
        Rebbs rebbs = new Rebbs();
        if (cond != null) {
            if ("usersid".equals(cond)) {
                rebbs.setUsersid(name);
                rebbsList = rebbsService.getRebbsByLike(rebbs);
            }
            if ("bbsid".equals(cond)) {
                rebbs.setBbsid(name);
                rebbsList = rebbsService.getRebbsByLike(rebbs);
            }
            if ("contents".equals(cond)) {
                rebbs.setContents(name);
                rebbsList = rebbsService.getRebbsByLike(rebbs);
            }
            if ("addtime".equals(cond)) {
                rebbs.setAddtime(name);
                rebbsList = rebbsService.getRebbsByLike(rebbs);
            }
        }
        map.put("rebbsList", rebbsList);
        return "admin/queryrebbs";
    }
*/

    // 按主键查询数据
   /* @RequestMapping("getRebbsById.action")
    public String getById(String id, Map<String, Object> map) {
        Rebbs rebbs = rebbsService.getById(id);
        map.put("rebbs", rebbs);
        List<Users> usersList = usersService.getAllUsers();
        map.put("usersList", usersList);
        List<Bbs> bbsList = bbsService.getAllBbs();
        map.put("bbsList", bbsList);
        return "admin/bbs/editrebbs";//跳转到WebContent目录下对应的JSP页面
    }
*/

}
