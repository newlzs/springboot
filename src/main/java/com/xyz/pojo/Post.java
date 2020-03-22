package com.xyz.pojo;

import java.io.Serializable;

public class Post implements Serializable,Model{
    private static final long serialVersionUID = 1L;
    private long id;
    private String theme;
    private String content;
    private long createAt;
    private long updateAt;
    private long creatorId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getCreateAt() {
        return createAt;
    }

    public void setCreateAt(long createAt) {
        this.createAt = createAt;
    }

    public long getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(long updateAt) {
        this.updateAt = updateAt;
    }

    public long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(long creatorId) {
        this.creatorId = creatorId;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", theme='" + theme + '\'' +
                ", content='" + content + '\'' +
                ", createAt=" + createAt +
                ", updateAt=" + updateAt +
                ", creatorId=" + creatorId +
                '}';
    }
}
