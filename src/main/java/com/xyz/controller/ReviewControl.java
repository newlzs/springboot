package com.xyz.controller;

import com.xyz.common.Response;
import com.xyz.common.Utils;
import com.xyz.dto.request.CreateReviewRequest;
import com.xyz.exception.BaseException;
import com.xyz.exception.general.FormValidatorException;
import com.xyz.pojo.Review;
import com.xyz.pojo.User;
import com.xyz.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/review")
public class ReviewControl {
    @Autowired
    ReviewService reviewService;

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
    @PostMapping("/postId/{postId}")
    public Response getByPostId(@PathVariable long postId) {
        return new Response(0, reviewService.getCommentBypostId(postId));
    }
    @PostMapping("/creatorId/{creatorId}")
    public Response getByCreatorId(@PathVariable long creatorId) {
        return new Response(0, reviewService.getCommentBycreatorId(creatorId));
    }
}
