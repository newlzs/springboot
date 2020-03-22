package com.xyz.services;

import com.xyz.mapper.PostMapper;
import com.xyz.pojo.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
* @author lzs
* @Date 2020/3/18
*/
@Repository
public class PostService {
    @Autowired
    private PostMapper postMapper;

    public boolean checkOwnership(long userId, long postId) {
        return postMapper.checkOwnerShip(userId, postId) == 1;
    }

    public Post getPost(long id) {
        return postMapper.getPost(id);
    }
    public long createPost(Post post) {
        postMapper.createPost(post);
        return post.getId();
    }
    public boolean updatePost(Post post) {
        return postMapper.updatePost(post) == 1;
    }
    public boolean deletePost(long Id) {
        return postMapper.deletePost(Id) == 1;
    }
    public List<Post> getPostsByCreatorId(Long userId) {
        return new ArrayList<>(postMapper.getPostByCreatorId(userId));
    }
}
