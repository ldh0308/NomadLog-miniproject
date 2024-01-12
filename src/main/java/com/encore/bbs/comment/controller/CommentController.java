package com.encore.bbs.comment.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.encore.bbs.comment.dto.CommentDTO;
import com.encore.bbs.comment.service.CommentService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/save")
    public String saveComment(@ModelAttribute CommentDTO commentDTO) {
    	commentService.addComment(commentDTO);
        
        // 댓글 추가 후, 해당 댓글이 속한 게시글의 상세 페이지로 이동
        return "redirect:/bbs/" + commentDTO.getBbsId();
    }


    // 수정
    @PostMapping("/update/{commentId}")
    @ResponseBody // http
    public ResponseEntity<String> updateComment(@PathVariable Long commentId, @RequestBody CommentDTO commentDTO) {
        try {
            commentService.updateComment(commentId, commentDTO);
            return new ResponseEntity<>("수정완료", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("수정에러", HttpStatus.BAD_REQUEST);
        }
    }

    // 삭제
    @PostMapping("/delete/{bbsId}")
    public String deleteComment(@RequestParam("commentId") Long commentId) {
        commentService.deleteComment(commentId);
        // 댓글 삭제 후, 해당 게시글의 상세 페이지로 이동
        return "redirect:/bbs/{bbsId}";
    }
}
