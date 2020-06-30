package com.controller.web;

import com.entity.Article;
import com.entity.Click;
import com.entity.Comment;
import com.service.ArticleService;
import com.service.ClickService;
import com.service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//定义为控制器
@Controller
// 设置路径
@RequestMapping("/web")
public class ArticleWebAction extends BaseWebAction {


    // 注入AdminService 并getter setter
    @Resource
    private ArticleService articleService;

   @Resource
    private CommentService commentService;

   @Resource
    private ClickService clickService;



    @RequestMapping("article.action")
    public String article(HttpServletRequest request, Map<String, Object> map) {
        this.front();
        Map<String, Object> parameter = new HashMap<String, Object>();
        String catalogid = request.getParameter("catalogid");//搜索关键词
        String bookinfoid = request.getParameter("bookinfoid");//搜索关键词
        String keyword = request.getParameter("keyword");//搜索关键词

        if (catalogid != null && catalogid != "") parameter.put("catalogid", catalogid);//搜索参数
        if (bookinfoid != null && bookinfoid != "") parameter.put("bookinfoid", bookinfoid);//搜索参数
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
        map.put("catalogid", catalogid);
        map.put("bookinfoid", bookinfoid);
        map.put("pager", pager);
        map.put("articleList", articleList);
        return "Web/book";//跳转到WebContent目录下对应的JSP页面
    }

    // 阅读
    @RequestMapping("articleDetail.action")
    public String articleDetail(HttpServletRequest request,String id, Map<String, Object> map) {
        this.front();
        Article article = articleService.getById(id);
        article.setHits("" + (Integer.parseInt(article.getHits()) + 1));
        articleService.update(article);
        map.put("article", article);



        if (getRequest().getSession().getAttribute("userid") == null) {

            //如果登录插入最近浏览记录
            String userid = (String) this.getSession().getAttribute("userid");
            Click click = new Click();
            click.setAddtime(new Date());
            click.setArticleid(id);
            click.setMemo(this.getRequest().getParameter("memo"));
            click.setUsersid(userid);
            clickService.insert(click);

        }















        //---------------------------------------------获取评论
        Map<String, Object> parameter = new HashMap<String, Object>();


        String keyword = request.getParameter("keyword");//搜索关键词
        if (keyword != null && keyword != "") parameter.put("keyword", keyword);//搜索参数


        parameter.put("articleid", article.getArticleid());//搜索参数


        int rowCountTotal = commentService.getRowSize(parameter);//获取总数
        int pageSize = 10;// 分页大小
        int pageNumber = 1; //设定页面参数,传递给JSP页面

        if (request.getParameter("pageNumber") != null && request.getParameter("pageNumber") != "") {
            pageNumber = Integer.parseInt(request.getParameter("pageNumber"));//当前页
        }
        parameter.put("limit", pageSize);//搜索参数
        parameter.put("start", (pageNumber - 1) * pageSize);//搜索参数
        List<Comment> commentList = commentService.getAll(parameter);





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

        map.put("commentList", commentList);

        return "Web/bookContent";//跳转到WebContent目录下对应的JSP页面
    }


}
