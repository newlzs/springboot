package com.xyz.services;

import com.xyz.mapper.ReviewMapper;
import com.xyz.pojo.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewService {
    @Autowired
    ReviewMapper commentMapper;
    public long createComment(Review comment) {
        commentMapper.createComment(comment);
        return comment.getCommentId();
    }
    public boolean deleteComment(long commentId){
       return commentMapper.deleteComment(commentId) == 1;
    }
    public List<Review> getCommentBypostId(long postId) {
        return new ArrayList<>(commentMapper.getCommentBypostId(postId));
    }
    public List<Review> getCommentBycreatorId(long creatorId) {
        return new ArrayList<>(commentMapper.getCommentBycreatorId(creatorId));
    }
}
