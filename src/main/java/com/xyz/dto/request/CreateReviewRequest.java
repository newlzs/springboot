package com.xyz.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CreateReviewRequest {
    @NotNull
    private long postId;
    @NotBlank
    private String message;
    //    如果评论的是主文章则为0, 否则为回复的commentId
    @NotNull
    private long commentTo;

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getCommentTo() {
        return commentTo;
    }

    public void setCommentTo(long commentTo) {
        this.commentTo = commentTo;
    }
}
