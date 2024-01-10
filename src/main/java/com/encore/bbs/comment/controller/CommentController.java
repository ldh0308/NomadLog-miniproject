package com.encore.bbs.comment.controller;
import com.encore.bbs.comment.dto.CommentDTO;
import com.encore.bbs.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/comment")

public class CommentController {
    private final CommentService commentService;

    @GetMapping("/save/{bbsId}")
    public String saveComment(@PathVariable("bbsId") Long bbsId, CommentDTO commentDTO, Model model) {

        List<CommentDTO> commentDTOList = commentService.getCommentsByBbsId(bbsId);
        model.addAttribute("commentList", commentDTOList);

        commentService.addComment(commentDTO);
        // 댓글 추가 후, 해당 댓글이 속한 게시글의 상세 페이지로 이동
        return "redirect:/bbs/" + bbsId;
    }

    @PostMapping(value = "/bbs/{bbsId}", consumes = "application/json")
    public String save(@RequestParam Long bbsId, CommentDTO commentDTO) {
        Long id = commentService.addComment(commentDTO);
        //commentService.addComment(commentDTO);

        // 댓글 작성 성공하면 댓글 목록을 가져와서 리턴
       // List<CommentDTO> commentDTOList = commentService.getCommentsByBbsId(commentDTO.getBbsId());

        return "redirect:/bbs";
    }

    // 조회 - 상세페이지/목록보이기
    @GetMapping("/bbs/{bbsId}")
    public List<CommentDTO> getCommentsByBbsId(@PathVariable Long bbsId) {
        return commentService.getCommentsByBbsId(bbsId);
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
    @PostMapping("/delete/{commentId}")
    public String deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        // 댓글 삭제 후, 해당 게시글의 상세 페이지로 이동
        return "redirect:/bbs/";
    }
}
