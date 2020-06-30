package com.controller.web;

import com.entity.*;
import com.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

//定义为控制器
@Controller
// 设置路径
@RequestMapping("/web")
public class BbsWebAction extends BaseWebAction {


    @Resource
    private BbsService bbsService;
    @Resource
    private RebbsService rebbsService;
    // 注入AdminService 并getter setter
    @Resource
    private UsersService usersService;

    @Resource
    private ArticleService articleService;


    // 留言板
    @RequestMapping("bbs.action")
    public String bbs(HttpServletRequest request, Map<String, Object> map) {
        this.front();
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
        return "Web/comment";//跳转到WebContent目录下对应的JSP页面
    }

    // 发布留言
    @RequestMapping("addbbs.action")
    public String addbbs(Map<String, Object> map) {
        this.front();
        if (getRequest().getSession().getAttribute("userid") == null) {
            return "redirect:/web/preLogin.action";//跳转到action
        }
        String userid = (String) getRequest().getSession().getAttribute("userid");
        Bbs bbs = new Bbs();
        bbs.setAddtime(new Date());
        bbs.setContents(getRequest().getParameter("contents"));
        bbs.setHits("0");
        bbs.setRepnum("0");
        bbs.setTitle(getRequest().getParameter("title"));
        bbs.setUsersid(userid);


        bbsService.insert(bbs);
        return "redirect:/web/bbs.action";//跳转到action
    }

    // 查看回复
    @RequestMapping("bbsDetail.action")
    public String bbsDetail(HttpServletRequest request, Map<String, Object> map) {
        this.front();
        Bbs bbs = bbsService.getById(getRequest().getParameter("id"));
        bbs.setHits("" + (Integer.parseInt(bbs.getHits()) + 1));//增加主贴的浏览量
        bbsService.update(bbs);
        map.put("bbs", bbs);


        //-------------------------------------------获取回帖
        Map<String, Object> parameter = new HashMap<String, Object>();


        String keyword = request.getParameter("keyword");//搜索关键词
        if (keyword != null && keyword != "") parameter.put("keyword", keyword);//搜索参数


        parameter.put("bbsid", bbs.getBbsid());//搜索参数


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
        return "Web/commentDetail";//跳转到WebContent目录下对应的JSP页面
    }

    // 回复留言
    @RequestMapping("rebbs.action")
    public String rebbs(Map<String, Object> map) {
        this.front();
        if (getRequest().getSession().getAttribute("userid") == null) {
            return "redirect:/web/preLogin.action";//跳转到action
        }
        String userid = (String) getRequest().getSession().getAttribute("userid");
        Rebbs rebbs = new Rebbs();
        rebbs.setAddtime(new Date());
        rebbs.setContents(getRequest().getParameter("contents"));
        rebbs.setBbsid(getRequest().getParameter("bbsid"));
        rebbs.setUsersid(userid);
        rebbsService.insert(rebbs);
        Bbs bbs = bbsService.getById(rebbs.getBbsid());
        bbs.setRepnum("" + (Integer.parseInt(bbs.getRepnum()) + 1));//增加主贴的回复数
        bbsService.update(bbs);
        String path = "redirect:/web/bbsDetail.action?id=" + bbs.getBbsid();
        return path;//跳转到ACTION
    }


}
