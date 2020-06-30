package com.controller.web;

import com.entity.*;
import com.service.ArticleService;
import com.service.BookinfoService;
import com.service.CatalogService;
import com.service.ClickService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Action 基类
 */
@Controller
public class BaseWebAction {

    /* 日志 */
    protected final Log log = LogFactory.getLog(getClass());

    /* 获取基本环境 */
    public Map<String, String[]> getParameters() {// 封装为Map的requestParameters
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return attrs.getRequest().getParameterMap();
    }

    public HttpServletRequest getRequest() {
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return attrs.getRequest();
    }

    public HttpSession getSession() {
        HttpSession session = null;
        try {
            session = this.getRequest().getSession();
        } catch (Exception e) {
        }
        return session;
    }




    @Resource
    private CatalogService catalogService;
    @Resource
    private BookinfoService bookinfoService;
    // 注入AdminService 并getter setter
    @Resource
    private ArticleService articleService;
    @Resource
    private ClickService clickService;

    // 公共方法 提供公共查询数据
    //不用再到每个方法里提取
    public void front() {
        Map<String, Object> parameter = new HashMap<String, Object>();
        getRequest().setAttribute("title", "小说网站");
        List<Catalog> catalogList = catalogService.getAll(parameter);
        this.getRequest().setAttribute("catalogList", catalogList);//分类列表


        List<Bookinfo> bookinfoList = bookinfoService.getAll(parameter);
        this.getRequest().setAttribute("bookinfoList", bookinfoList);

        List<Article> hitsList = articleService.getHits();
        System.out.println(hitsList.size());

        this.getRequest().setAttribute("hitsList", hitsList);//排行

        String userid="";
        if (getRequest().getSession().getAttribute("userid") == null) {



              userid = (String) this.getSession().getAttribute("userid");
        }
        Click click = new Click();
        click.setUsersid(userid);
        List<Click> clickList = clickService.getByCond(click);


        this.getRequest().setAttribute("clickList", clickList);//猜你喜欢,有登录显示会员最近浏览分类的图书,无登录显示随机的


    }


}
