package com.controller.web;

import com.entity.*;
import com.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

//定义为控制器
@Controller
// 设置路径
@RequestMapping("/web")
public class CommentWebAction extends BaseWebAction {


    @Resource
    private ArticleService articleService;
    // 注入AdminService 并getter setter
    @Resource
    private CommentService commentService;


    /*-----------------------------------------用户*/
    // 准备提交评论
    @RequestMapping("preComment.action")
    public String preComment(String id, Map<String, Object> map) {
        this.front();
        if (this.getSession().getAttribute("userid") == null) {
            return "redirect:/web/preLogin.action";//跳转到action
        }
        Article article = articleService.getById(id);
        map.put("article", article);
        return "users/addComment";//跳转到WebContent目录下对应的JSP页面
    }

    // 提交评论
    @RequestMapping("addComment.action")
    public String addComment() {
        this.front();
        if (this.getSession().getAttribute("userid") == null) {
            return "redirect:/web/preLogin.action";//跳转到action
        }
        String userid = (String) this.getSession().getAttribute("userid");
        Comment comment = new Comment();
        comment.setAddtime(new Date());
        comment.setArticleid(this.getRequest().getParameter("articleid"));
        comment.setMemo(this.getRequest().getParameter("memo"));
        comment.setUsersid(userid);


        commentService.insert(comment);
        return "redirect:/web/articleDetail.action?id="+this.getRequest().getParameter("articleid");//跳转到action
    }

    // 我的评论
    @RequestMapping("myComment.action")
    public String myComment(Map<String, Object> map) {
        this.front();
        if (this.getSession().getAttribute("userid") == null) {
            return "redirect:/web/preLogin.action";//跳转到action
        }
        String userid = (String) this.getSession().getAttribute("userid");
        Comment comment = new Comment();
        comment.setUsersid(userid);
        List<Comment> commentList = commentService.getByCond(comment);
        map.put("commentList", commentList);
        return "users/myComment";//跳转到WebContent目录下对应的JSP页面
    }


    // 删除评论
    @RequestMapping("delete.action")
    public String delete(String id, Map<String, Object> map) {
        this.front();
        commentService.delete(id);
        return "redirect:/web/myComment.action";//跳转到action
    }




}
