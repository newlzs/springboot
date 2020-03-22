package com.xyz.mapper;

import com.xyz.pojo.Post;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
* @author lzs
* @Date 2020/3/22
*/

@Repository
public interface CommentMapper {
    public void createComment();
    Collection<Post> getPostComment(long postId);
    public void deletePostComment(long postId);
}
