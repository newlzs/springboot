package com.xyz.controller;

import com.xyz.common.Response;
import com.xyz.common.Utils;
import com.xyz.dto.request.CreatePostRequest;
import com.xyz.exception.BaseException;
import com.xyz.exception.UnknownException;
import com.xyz.exception.general.FormValidatorException;
import com.xyz.exception.general.PermissionDeniedException;
import com.xyz.exception.general.ResourceExistedException;
import com.xyz.exception.general.ResourceNotExistException;
import com.xyz.pojo.Post;
import com.xyz.pojo.User;
import com.xyz.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/custom")
public class CustomController {
    @Autowired
    private PostService postService;

    @RequestMapping("/new")
    public Response createNewPost(@RequestBody @Valid CreatePostRequest request,
                                  HttpServletRequest httpServletRequest,
                                  BindingResult bindingResult) throws BaseException {
        if(bindingResult.hasErrors()) {
            throw new FormValidatorException(bindingResult);
        }
        Post post = new Post();
        User user = (User)httpServletRequest.getAttribute("user");

        Long currentTime = Utils.createTimestamp();
        post.setTheme(request.getTheme());
        post.setContent(request.getContent());
        post.setCreateAt(currentTime);
        post.setUpdateAt(currentTime);
//        获取用的Id, 使用 User
        post.setCreatorId(user.getId());
        long newId = postService.createPost(post);

        return new Response(0, newId);
    }

    @PutMapping("/{postId}")
    public Object updatePost(@RequestBody @Valid CreatePostRequest request,
                               @PathVariable long postId, BindingResult bindingResult,
                             HttpServletRequest httpServletRequest) throws BaseException {
        if(bindingResult.hasErrors()) {
            throw new FormValidatorException(bindingResult);
        }
        User user = (User)httpServletRequest.getAttribute("user");
        Post post = postService.getPost(postId);
        Long currentTime = Utils.createTimestamp();

        if(post == null) {
            throw new ResourceNotExistException("帖子");
        }
        if(!postService.checkOwnership(user.getId(), postId)) {
            throw new PermissionDeniedException();
        }

        post.setTheme(request.getTheme());
        post.setContent(request.getContent());
        post.setUpdateAt(currentTime);
        if(!postService.updatePost(post)) {
            throw new UnknownException("无法更改帖子");
        }

        return new Response(0, null);
    }

    @DeleteMapping("/{postId}")
    public Response deletePost(@PathVariable long postId,
                               HttpServletRequest httpServletRequest) throws BaseException{
        User user = (User) httpServletRequest.getAttribute("user");
        Post post = postService.getPost(postId);

        if(post == null) {
            throw new ResourceNotExistException("帖子");
        }
        if(!postService.checkOwnership(user.getId(), postId)) {
            throw new PermissionDeniedException();
        }

        if(!postService.deletePost(postId)) {
            throw new UnknownException("无法删除帖子");
        }
        return new Response(0, null);
    }
}
