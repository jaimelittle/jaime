package com.dao;

import java.util.List;
import java.util.Map;


import com.entity.Article;

public interface  ArticleDAO {
// 插入数据 调用entity包article.xml里的insertArticle配置
    void insert(Article article) ;

    // 更新数据 调用entity包article.xml里的updateArticle配置
    void update(Article article) ;

    // 删除数据 调用entity包article.xml里的deleteArticle配置
    void delete(String articleid) ;

    // 查询全部数据 调用entity包article.xml里的getAllArticle配置
    List<Article> getAll(Map<String, Object> params) ;
    // 查询总行数 调用entity包Article.xml里的配置
    int getRowSize(Map<String, Object> params) ;




    //固顶
    List<Article> getTop() ;


    //轮播
    List<Article> getFlv() ;

    //排行
    List<Article> getHits() ;

    List<Article> getByCatalog(String catalogid) ;

    // 按照Admin类里面的值精确查询 调用entity包article.xml里的getArticleByCond配置
    List<Article> getByCond(Article article) ;

    // 按照Article类里面的值模糊查询 调用entity包article.xml里的getArticleByLike配置
    List<Article> getByLike(Article article) ;
    // 按主键查询表返回单一的Article实例 调用entity包article.xml里的getArticleById配置
    Article getById(String articleid) ;

}
