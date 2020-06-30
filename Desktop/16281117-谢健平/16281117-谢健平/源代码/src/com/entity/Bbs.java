package com.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Bbs {
    private String bbsid;
    private String usersid;
    private String title;
    private String contents;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date addtime;

    private String hits;
    private String repnum;
    private String username;
    private String image;





    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getBbsid() {
        return bbsid;
    }

    public void setBbsid(String bbsid) {
        this.bbsid = bbsid;
    }

    public String getUsersid() {
        return this.usersid;
    }

    public void setUsersid(String usersid) {
        this.usersid = usersid;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getRepnum() {
        return this.repnum;
    }

    public void setRepnum(String repnum) {
        this.repnum = repnum;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
