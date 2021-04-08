package com.xyz.controller;

import com.xyz.common.Response;
import com.xyz.common.Utils;
import com.xyz.dto.request.CreatePostRequest;
import com.xyz.exception.BaseException;
import com.xyz.exception.UnknownException;
import com.xyz.exception.general.FormValidatorException;
import com.xyz.exception.general.PermissionDeniedException;
import com.xyz.exception.general.ResourceNotExistException;
import com.xyz.pojo.Post;
import com.xyz.pojo.User;
import com.xyz.services.PostService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/posts")
@Api(value = "帖子管理")
public class PostController {
    @Autowired
    private PostService postService;

    @ApiOperation(value = "根据id获取帖子内容")
    @GetMapping("/{id}")
    public Post getPost(@PathVariable long id) {
        Post post = postService.getPost(id);
        return post;
    }

    @ApiOperation(value = "根据用户id获取其所有帖子")
    @GetMapping("/onesPost/{creatorId}")
    public Response getOnesPost(@PathVariable Long creatorId) {
        return new Response(0, postService.getPostsByCreatorId(creatorId));
    }

    @ApiOperation(value = "分页查询帖子")
    @GetMapping("")
    public Response getPostsPage(Long offset, Long limit){
        return new Response(0, postService.getPostsPage(offset, limit));
    }
}
