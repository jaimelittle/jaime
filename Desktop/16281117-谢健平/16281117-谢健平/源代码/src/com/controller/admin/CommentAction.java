package com.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.entity.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.service.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

//定义为控制器
@Controller
// 设置路径
@RequestMapping("/admin/comment")
public class CommentAction extends BaseAdminAction {
     // 注入AdminService
     @Resource
    private CommentService commentService;
    @Resource
    private UsersService usersService;
    @Resource
    private ArticleService articleService;

    // 准备添加数据
   /* @RequestMapping("create.action")
    public String createComment(Map<String, Object> map) {
        List<Users> usersList = usersService.getAllUsers();
        map.put("usersList", usersList);
        List<Doctor> doctorList = doctorService.getAllDoctor();
        map.put("doctorList", doctorList);
        return "admin/comment/add";//跳转到WebContent目录下对应的JSP页面
    }

    // 添加数据
    @RequestMapping("add.action")
    public String addComment(Comment comment) {
        comment.setCommentid(VeDate.getStringDatex());
        commentService.insertComment(comment);
        return "redirect:/admin/comment/create.action";//跳转到action
    }
*/
    // 通过主键删除数据
    @RequestMapping("delete.action")
    public String deleteComment(String id) {
        commentService.delete(id);
        return "redirect:/admin/comment/getAll.action";//跳转到action
    }

    // 更新数据
 /*   @RequestMapping("update.action")
    public String update(Comment comment) {
        commentService.update(comment);
        return "redirect:/admin/comment/getAll.action";//跳转到action
    }*/

    // 显示全部数据
    @RequestMapping("getAll.action")
    public String getAll(HttpServletRequest request, Map<String, Object> map) {
        Map<String, Object> parameter = new HashMap<String, Object>();
        String keyword = request.getParameter("keyword");//搜索关键词
        if (keyword != null && keyword != "") parameter.put("keyword", keyword);//搜索参数





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
        return "admin/comment/list";//跳转到WebContent目录下对应的JSP页面
    }

    // 按条件查询数据 (模糊查询)----备用
 /*
    @RequestMapping("queryCommentByCond.action")
    public String queryCommentByCond(String cond, String name, Map<String, Object> map) {
        List<Comment> commentList = new ArrayList<Comment>();
        Comment comment = new ();
        if (cond != null) {
            if ("usersid".equals(cond)) {
                comment.setUsersid(name);
                commentList = commentService.getCommentByLike(comment);
            }
            if ("articleid".equals(cond)) {
                comment.setArticleid(name);
                commentList = commentService.getCommentByLike(comment);
            }
            if ("addtime".equals(cond)) {
                comment.setAddtime(name);
                commentList = commentService.getCommentByLike(comment);
            }
            if ("status".equals(cond)) {
                comment.setStatus(name);
                commentList = commentService.getCommentByLike(comment);
            }
            if ("memo".equals(cond)) {
                comment.setMemo(name);
                commentList = commentService.getCommentByLike(comment);
            }
        }
        map.put("commentList", commentList);
        return "admin/comment/query";
    }
*/

    // 按主键查询数据
    @RequestMapping("getById.action")
    public String getCommentById(String id, Map<String, Object> map) {
        Map<String, Object> parameter = new HashMap<String, Object>();
        Comment comment = commentService.getById(id);
        map.put("comment", comment);
        List<Users> usersList = usersService.getAll(parameter);
        map.put("usersList", usersList);
        List<Article> articleList = articleService.getAll(parameter);
        map.put("doctorList", articleList);
        return "admin/comment/edit";//跳转到WebContent目录下对应的JSP页面
    }



}
