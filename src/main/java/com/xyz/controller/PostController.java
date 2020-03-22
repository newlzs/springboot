package com.xyz.controller;

import com.xyz.common.Response;
import com.xyz.pojo.Post;
import com.xyz.pojo.User;
import com.xyz.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/post")
public class PostController {
    @Autowired
    private PostService postService;

    @RequestMapping("/{id}")
    public Post getPost(@PathVariable long id) {
        Post post = postService.getPost(id);
        return post;
    }

    @GetMapping("/onesPost")
    public Response getOnesPost(Long userId) {
        return new Response(0, postService.getPostsByCreatorId(userId));
    }
}
