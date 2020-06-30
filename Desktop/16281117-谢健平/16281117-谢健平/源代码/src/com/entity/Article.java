package com.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Article {
    private String articleid;
    private String title;
    private String image;
    private String catalogid;
    private String istop;
    private String isflv;
    private String contents;


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date addtime;

    private String hits;
    private String catalogname;
    private String authorname;
    private String bookinfoname;
    private String bookinfoid;

    public String getArticleid() {
        return articleid;
    }

    public void setArticleid(String articleid) {
        this.articleid = articleid;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCatalogid() {
        return this.catalogid;
    }

    public void setCatalogid(String catalogid) {
        this.catalogid = catalogid;
    }

    public String getIstop() {
        return this.istop;
    }

    public void setIstop(String istop) {
        this.istop = istop;
    }

    public String getIsflv() {
        return this.isflv;
    }

    public void setIsflv(String isflv) {
        this.isflv = isflv;
    }

    public String getContents() {
        return this.contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public Date getAddtime() {
        return this.addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public String getHits() {
        return this.hits;
    }

    public void setHits(String hits) {
        this.hits = hits;
    }

    public String getCatalogname() {
        return catalogname;
    }

    public void setCatalogname(String catalogname) {
        this.catalogname = catalogname;
    }

    public String getAuthorname() {
        return authorname;
    }

    public void setAuthorname(String authorname) {
        this.authorname = authorname;
    }

    public String getBookinfoname() {
        return bookinfoname;
    }

    public void setBookinfoname(String bookinfoname) {
        this.bookinfoname = bookinfoname;
    }

    public String getBookinfoid() {
        return bookinfoid;
    }

    public void setBookinfoid(String bookinfoid) {
        this.bookinfoid = bookinfoid;
    }
}
