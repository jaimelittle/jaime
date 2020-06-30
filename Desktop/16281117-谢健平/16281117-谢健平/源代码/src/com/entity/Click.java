package com.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Click {
    private String clickid;
    private String usersid;
    private String articleid;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date addtime;

    private String memo;
    private String username;
    private String realname;


    private String title;
    private String image;


    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getClickid() {
        return clickid;
    }

    public void setClickid(String clickid) {
        this.clickid = clickid;
    }

    public String getUsersid() {
        return this.usersid;
    }

    public void setUsersid(String usersid) {
        this.usersid = usersid;
    }

    public String getArticleid() {
        return this.articleid;
    }

    public void setArticleid(String articleid) {
        this.articleid = articleid;
    }

    public Date getAddtime() {
        return this.addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }


    public String getMemo() {
        return this.memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
