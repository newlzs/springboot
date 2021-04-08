package com.xyz.controller;

import com.xyz.common.Response;
import com.xyz.common.Utils;
import com.xyz.dto.request.CreateReviewRequest;
import com.xyz.exception.BaseException;
import com.xyz.exception.general.FormValidatorException;
import com.xyz.pojo.Review;
import com.xyz.pojo.User;
import com.xyz.services.ReviewService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/review")
@Api(value = "评论管理")
public class ReviewControl {
    @Autowired
    ReviewService reviewService;

    @ApiOperation(value = "创建评论")
    @PostMapping("/new")
    public Response CreateComment(@RequestBody @Valid CreateReviewRequest createReviewRequest,
                                  HttpServletRequest request,
                                  BindingResult bindingResult) throws BaseException {
        if(bindingResult.hasErrors()) {
            throw new FormValidatorException(bindingResult);
        }

        User user = (User) request.getAttribute("user");
        Review comment = new Review();
        comment.setPostId(createReviewRequest.getPostId());
        comment.setMessage(createReviewRequest.getMessage());
        comment.setCommentTo(createReviewRequest.getCommentTo());

        long currentTime = Utils.createTimestamp();
        comment.setCreateAt(currentTime);
        comment.setCreatorId(user.getId());

        reviewService.createComment(comment);

        return new Response(0, comment.getCommentId());
    }

    @ApiOperation(value = "根据帖子id获取某一帖子的所有评论")
    @GetMapping("/postId/{postId}")
    public Response getByPostId(@PathVariable long postId) {
        return new Response(0, reviewService.getCommentBypostId(postId));
    }

    @ApiOperation(value = "根据创建者id获取其评论")
    @GetMapping("/creatorId/{creatorId}")
    public Response getByCreatorId(@PathVariable long creatorId) {
        return new Response(0, reviewService.getCommentBycreatorId(creatorId));
    }
}
