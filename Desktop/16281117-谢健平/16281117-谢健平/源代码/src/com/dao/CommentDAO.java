package com.dao;

import java.util.List;
import java.util.Map;


import com.entity.Comment;

public interface  CommentDAO {
// 插入数据 调用entity包comment.xml里的insertComment配置
    void insert(Comment comment) ;
    // 更新数据 调用entity包comment.xml里的updateComment配置
    void update(Comment comment) ;

    // 删除数据 调用entity包comment.xml里的deleteComment配置
    void delete(String commentid);

    // 查询全部数据 调用entity包comment.xml里的getAllComment配置
    List<Comment> getAll(Map<String, Object> params) ;
    // 查询总行数 调用entity包Comment.xml里的配置
    int getRowSize(Map<String, Object> params) ;




    // 按照Admin类里面的值精确查询 调用entity包comment.xml里的getCommentByCond配置
    List<Comment> getByCond(Comment comment) ;

    // 按照Comment类里面的值模糊查询 调用entity包comment.xml里的getCommentByLike配置
    List<Comment> getByLike(Comment comment) ;

    // 按主键查询表返回单一的Comment实例 调用entity包comment.xml里的getCommentById配置
    Comment getById(String commentid) ;

}
