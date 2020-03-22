package com.xyz.mapper;

import com.xyz.pojo.Post;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
* @author lzs
* @Date 2020/3/17
*/

@Repository
public interface PostMapper {
    Post getPost(long Id);
    Collection<Post> getAllPost();
    void createPost(Post post);
    int updatePost(Post post);
    int deletePost(long Id);
    int checkOwnerShip(@Param("userId") long userId, @Param("postId") long postId);
    Collection<Post> getPostByCreatorId(@Param("creatorId") long creatorId);
}
