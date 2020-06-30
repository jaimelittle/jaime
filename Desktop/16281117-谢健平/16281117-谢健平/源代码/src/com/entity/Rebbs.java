package com.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Rebbs {
    private String rebbsid;
    private String usersid;
    private String bbsid;
    private String contents;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date addtime;
    private String username;
    private String title;
    private String image;



    public String getRebbsid() {
        return rebbsid;
    }

    public void setRebbsid(String rebbsid) {
        this.rebbsid = rebbsid;
    }

    public String getUsersid() {
        return this.usersid;
    }

    public void setUsersid(String usersid) {
        this.usersid = usersid;
    }

    public String getBbsid() {
        return this.bbsid;
    }

    public void setBbsid(String bbsid) {
        this.bbsid = bbsid;
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
