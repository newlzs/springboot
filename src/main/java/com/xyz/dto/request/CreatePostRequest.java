package com.xyz.dto.request;

import javax.validation.constraints.NotBlank;

/**
* @author lzs
* @Date 2020/3/18
*/

public class CreatePostRequest {
    @NotBlank(message = "主题不能为空")
    private String theme;
    @NotBlank(message = "内容不能为空")
    private String content;
    private long createAt;
    private long updateAt;
    private long creatorId;

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
}
