package com.example.myapplication2.bean;

import java.io.Serializable;

public class Note implements Serializable {  //继承Serializable接口，使对象能够在addActivity和EditActivity之间传递
    private String title;
    private String content;
    private String createTime;

    private String author;
    private String id;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Note{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createTime='" + createTime + '\'' +
                ", author='" + author + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
