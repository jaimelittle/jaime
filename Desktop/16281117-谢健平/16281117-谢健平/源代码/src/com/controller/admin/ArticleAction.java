package com.controller.admin;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.entity.Bookinfo;
import com.service.BookinfoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.service.ArticleService;
import com.service.CatalogService;
import com.entity.Article;
import com.entity.Catalog;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

//定义为控制器
@Controller
// 设置路径
@RequestMapping("/admin/article")
public class ArticleAction extends BaseAdminAction {
    // 注入AdminService 并getter setter
    @Resource
    private ArticleService articleService;
    @Resource
    private CatalogService catalogService;

   @Resource
    private BookinfoService bookinfoService;

    // 准备添加数据
    @RequestMapping("create.action")
    public String create(Map<String, Object> map) {
        Map<String, Object> parameter = new HashMap<String, Object>();
        List<Catalog> catalogList = catalogService.getAll(parameter);
        List<Bookinfo> bookinfoList = bookinfoService.getAll(parameter);
        map.put("bookinfoList", bookinfoList);
        map.put("catalogList", catalogList);
        return "admin/article/add";//跳转到WebContent目录下对应的JSP页面
    }

    // 添加数据
    @RequestMapping("add.action")
    public String add(Article article) {
        article.setHits("0");
        article.setAddtime(new Date());
        articleService.insert(article);
        return "redirect:/admin/article/getAll.action";//跳转到action
    }

    // 通过主键删除数据
    @RequestMapping("delete.action")
    public String delete(String id) {
        articleService.delete(id);
        return "redirect:/admin/article/getAll.action";//跳转到action
    }

    // 更新数据
    @RequestMapping("update.action")
    public String update(Article article) {
        articleService.update(article);
        return "redirect:/admin/article/getAll.action";//跳转到action
    }

    // 显示全部数据
    @RequestMapping("getAll.action")
    public String getAll(HttpServletRequest request, Map<String, Object> map) {
        Map<String, Object> parameter = new HashMap<String, Object>();
        String keyword = request.getParameter("keyword");//搜索关键词
        if (keyword != null && keyword != "") parameter.put("keyword", keyword);//搜索参数


        int rowCountTotal = articleService.getRowSize(parameter);//获取总数
        int pageSize = 10;// 分页大小
        int pageNumber = 1; //设定页面参数,传递给JSP页面

        if (request.getParameter("pageNumber") != null && request.getParameter("pageNumber") != "") {
            pageNumber = Integer.parseInt(request.getParameter("pageNumber"));//当前页
        }
        parameter.put("limit", pageSize);//搜索参数
        parameter.put("start", (pageNumber - 1) * pageSize);//搜索参数
        List<Article> articleList = articleService.getAll(parameter);


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
        map.put("articleList", articleList);
        return "admin/article/list";//跳转到WebContent目录下对应的JSP页面
    }

    // 按条件查询数据 (模糊查询)----备用
/*
    @RequestMapping("queryArticleByCond.action")
    public String queryArticleByCond(String cond, String name, Map<String, Object> map) {
        List<Article> articleList = new ArrayList<Article>();
        Article article = new Article();
        if (cond != null) {
            if ("title".equals(cond)) {
                article.setTitle(name);
                articleList = articleService.getArticleByLike(article);
            }
            if ("image".equals(cond)) {
                article.setImage(name);
                articleList = articleService.getArticleByLike(article);
            }
            if ("catalogid".equals(cond)) {
                article.setCatalogid(name);
                articleList = articleService.getArticleByLike(article);
            }
            if ("istop".equals(cond)) {
                article.setIstop(name);
                articleList = articleService.getArticleByLike(article);
            }
            if ("isflv".equals(cond)) {
                article.setIsflv(name);
                articleList = articleService.getArticleByLike(article);
            }
            if ("contents".equals(cond)) {
                article.setContents(name);
                articleList = articleService.getArticleByLike(article);
            }
            if ("addtime".equals(cond)) {
                article.setAddtime(name);
                articleList = articleService.getArticleByLike(article);
            }
            if ("hits".equals(cond)) {
                article.setHits(name);
                articleList = articleService.getArticleByLike(article);
            }
        }
        map.put("articleList", articleList);
        return "admin/query";
    }
*/

    // 按主键查询数据
    @RequestMapping("getById.action")
    public String getById(String id, Map<String, Object> map) {
        Map<String, Object> parameter = new HashMap<String, Object>();
        Article article = articleService.getById(id);
        map.put("article", article);
        List<Catalog> catalogList = catalogService.getAll(parameter);
        List<Bookinfo> bookinfoList = bookinfoService.getAll(parameter);
        map.put("bookinfoList", bookinfoList);
        map.put("catalogList", catalogList);
        return "admin/article/edit";//跳转到WebContent目录下对应的JSP页面
    }

}
