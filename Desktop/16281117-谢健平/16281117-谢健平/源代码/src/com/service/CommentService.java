package com.service;

import java.util.List;
import java.util.Map;

import com.dao.*;

import com.entity.Comment;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("comment")

public class CommentService {
    @Resource
    private CommentDAO commentDao;

    // 插入数据 调用entity包comment.xml里的insertComment配置
    public void insert(Comment comment) {
        commentDao.insert(  comment);
    }

    // 更新数据 调用entity包comment.xml里的updateComment配置
    public void update(Comment comment) {
        commentDao.update(  comment);
    }

    // 删除数据 调用entity包comment.xml里的deleteComment配置
    public void delete(String commentid) {
        commentDao.delete(  commentid);
    }

    // 查询全部数据 调用entity包comment.xml里的getAllComment配置
    public List<Comment> getAll(Map<String, Object> params) {
        return commentDao.getAll( params);
    }
    // 查询总行数 调用entity包Comment.xml里的配置
    public int getRowSize(Map<String, Object> params) {
        return commentDao.getRowSize( params);
    }




    // 按照Admin类里面的值精确查询 调用entity包comment.xml里的getCommentByCond配置
    public List<Comment> getByCond(Comment comment) {
        return commentDao.getByCond(  comment);
    }

    // 按照Comment类里面的值模糊查询 调用entity包comment.xml里的getCommentByLike配置
    public List<Comment> getByLike(Comment comment) {
        return commentDao.getByLike(  comment);
    }

    // 按主键查询表返回单一的Comment实例 调用entity包comment.xml里的getCommentById配置
    public Comment getById(String commentid) {
        return commentDao.getById(  commentid);
    }

}
