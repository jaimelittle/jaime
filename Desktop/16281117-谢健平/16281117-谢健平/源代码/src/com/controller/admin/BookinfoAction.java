package com.controller.admin;

import com.entity.Bookinfo;
import com.service.BookinfoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//定义为控制器
@Controller
// 设置路径
@RequestMapping("/admin/bookinfo")
public class BookinfoAction extends BaseAdminAction {
    // 注入AdminService 并getter setter
    @Resource
    private BookinfoService bookinfoService;

    // 准备添加数据
    @RequestMapping("create.action")
    public String create(Map<String, Object> map) {
        return "admin/bookinfo/add";//跳转到WebContent目录下对应的JSP页面
    }

    // 添加数据
    @RequestMapping("add.action")
    public String add(Bookinfo bookinfo) {
         bookinfoService.insert(bookinfo);
        return "redirect:/admin/bookinfo/getAll.action";//跳转到action
    }

    // 通过主键删除数据
    @RequestMapping("delete.action")
    public String delete(String id) {
        bookinfoService.delete(id);
        return "redirect:/admin/bookinfo/getAll.action";//跳转到action
    }

    // 更新数据
    @RequestMapping("update.action")
    public String update(Bookinfo bookinfo) {
        bookinfoService.update(bookinfo);
        return "redirect:/admin/bookinfo/getAll.action";//跳转到action
    }

    // 显示全部数据
    @RequestMapping("getAll.action")
    public String getAll(HttpServletRequest request, Map<String, Object> map) {
        Map<String, Object> parameter = new HashMap<String, Object>();
        String keyword = request.getParameter("keyword");//搜索关键词
        if (keyword != null && keyword != "") parameter.put("keyword", keyword);//搜索参数





        int rowCountTotal = bookinfoService.getRowSize(parameter);//获取总数
        int pageSize = 10;// 分页大小
        int pageNumber = 1; //设定页面参数,传递给JSP页面

        if (request.getParameter("pageNumber") != null && request.getParameter("pageNumber") != "") {
            pageNumber = Integer.parseInt(request.getParameter("pageNumber"));//当前页
        }
        parameter.put("limit", pageSize);//搜索参数
        parameter.put("start", (pageNumber - 1) * pageSize);//搜索参数
        List<Bookinfo> bookinfoList = bookinfoService.getAll(parameter);





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

        map.put("bookinfoList", bookinfoList);
        return "admin/bookinfo/list";//跳转到WebContent目录下对应的JSP页面
    }

    // 按条件查询数据 (模糊查询)----备用
/*
    @RequestMapping("queryBookinfoByCond.action")
    public String queryBookinfoByCond(String cond, String name, Map<String, Object> map) {
        List<Bookinfo> bookinfoList = new ArrayList<Bookinfo>();
        Bookinfo bookinfo = new Bookinfo();
        if (cond != null) {
            if ("bookinfoname".equals(cond)) {
                bookinfo.setBookinfoname(name);
                bookinfoList = bookinfoService.getBookinfoByLike(bookinfo);
            }
        }
        map.put("bookinfoList", bookinfoList);
        return "admin/query";
    }
*/

    // 按主键查询数据
    @RequestMapping("getById.action")
    public String getBookinfoById(String id, Map<String, Object> map) {
        Bookinfo bookinfo = bookinfoService.getById(id);
        map.put("bookinfo", bookinfo);
        return "admin/bookinfo/edit";//跳转到WebContent目录下对应的JSP页面
    }



}
