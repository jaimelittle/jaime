package com.entity;

import java.util.ArrayList;
import java.util.List;

public class Catalog {
    private String catalogid;
    private String catalogname;
    private List<Article> articleList = new ArrayList<Article>();

    public List<Article> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<Article> articleList) {
        this.articleList = articleList;
    }

    public String getCatalogid() {
        return catalogid;
    }

    public void setCatalogid(String catalogid) {
        this.catalogid = catalogid;
    }

    public String getCatalogname() {
        return this.catalogname;
    }

    public void setCatalogname(String catalogname) {
        this.catalogname = catalogname;
    }
}
