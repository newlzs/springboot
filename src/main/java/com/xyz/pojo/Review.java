package com.xyz.pojo;

import java.io.Serializable;

/**
* @author lzs
* @Date 2020/3/29
*/

public class Review implements Serializable,Model{
    private static final long serialVersionUID = 1L;
    private long commentId;
    private long postId;
    private long creatorId;
    private String message;
    private long createAt;
//    如果评论的是主文章则为0, 否则为回复的commentId
    private long commentTo;

    public long getCommentTo() {
        return commentTo;
    }

    public void setCommentTo(long commentTo) {
        this.commentTo = commentTo;
    }

    public long getCommentId() {
        return commentId;
    }

    public void setCommentId(long commentId) {
        this.commentId = commentId;
    }

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    public long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(long creatorId) {
        this.creatorId = creatorId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getCreateAt() {
        return createAt;
    }

    public void setCreateAt(long createAt) {
        this.createAt = createAt;
    }
}
