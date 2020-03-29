package com.xyz.mapper;

import com.xyz.pojo.Review;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
* @author lzs
* @Date 2020/3/22
*/

@Repository
public interface ReviewMapper {
    public void createComment(Review comment);
    Collection<Review> getCommentBypostId(long postId);
    Collection<Review> getCommentBycreatorId(long creatorId);
    public int deleteComment(long postId);
}
