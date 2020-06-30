package com.controller.web;

import com.entity.*;
import com.service.ArticleService;
import com.service.CatalogService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//定义为控制器
@Controller
// 设置路径
@RequestMapping("/web")
public class IndexAction extends BaseWebAction {


    @Resource
    private CatalogService catalogService;
    // 注入AdminService 并getter setter
    @Resource
    private ArticleService articleService;



    // 首页显示
    @RequestMapping("index.action")
    public String index( Map<String, Object> map) {
        this.front();
        Map<String, Object> parameter = new HashMap<String, Object>();
        List<Catalog> catalogList = catalogService.getAll(parameter);
        List<Catalog> frontList = new ArrayList<Catalog>();
        for (Catalog catalog : catalogList) {
            List<Article> articleList = articleService.getByCatalog(catalog.getCatalogid());
            catalog.setArticleList(articleList);
            frontList.add(catalog);
        }
        map.put("frontList", frontList);

        List<Article> favList = articleService.getFlv();
        System.out.println(favList.size());

        map.put("favList", favList);


        List<Article> topList = articleService.getTop();
        System.out.println(topList.size());

        map.put("topList", topList);


        return "Web/index";//跳转到WebContent目录下对应的JSP页面
    }



}
