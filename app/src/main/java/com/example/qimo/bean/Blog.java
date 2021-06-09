package com.example.qimo.bean;

public class Blog {
    private String id,blogName,blogContent,blogTime;

    public String getBlogTime() {
        return blogTime;
    }

    public void setBlogTime(String blogTime) {
        this.blogTime = blogTime;
    }

    public Blog(String id, String blogName, String blogContent, String blogTime) {
        this.id=id;
        this.blogName = blogName;
        this.blogContent = blogContent;
        this.blogTime=blogTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Blog() {

    }

    public String getBlogName() {
        return blogName;
    }

    public void setBlogName(String blogName) {
        this.blogName = blogName;
    }

    public String getBlogContent() {
        return blogContent;
    }

    public void setBlogContent(String blogContent) {
        this.blogContent = blogContent;
    }
}
