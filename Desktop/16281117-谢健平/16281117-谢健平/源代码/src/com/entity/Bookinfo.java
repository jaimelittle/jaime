package com.entity;

import java.util.ArrayList;
import java.util.List;

public class Bookinfo {
    private String bookinfoid;
    private String bookinfoname;
    private String authorname;
    private List<Article> articleList = new ArrayList<Article>();

    public List<Article> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<Article> articleList) {
        this.articleList = articleList;
    }

    public String getBookinfoid() {
        return bookinfoid;
    }

    public void setBookinfoid(String bookinfoid) {
        this.bookinfoid = bookinfoid;
    }

    public String getBookinfoname() {
        return this.bookinfoname;
    }

    public void setBookinfoname(String bookinfoname) {
        this.bookinfoname = bookinfoname;
    }

    public String getAuthorname() {
        return authorname;
    }

    public void setAuthorname(String authorname) {
        this.authorname = authorname;
    }
}
