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

    // 주어진 commentId로 댓글을 업데이트합니다.
    @PatchMapping("/{commentId}") //댓글넘버
    @ResponseBody
    public ResponseEntity<String> updateComment(@RequestParam("commentId") long commentId, @RequestBody CommentDTO commentDTO) {
        // 댓글 작성자를 지정
        String writer = "rahee2"; // http 세션값 매개변수를 담아줘야하는데
        commentDTO.setWriter(writer);
        commentDTO.setCommentId(commentId);
        System.out.println("dto = " + commentDTO);

        try {
            int result =  commentService.updateComment(commentId, commentDTO);

            if(result != 1) {
                throw new Exception("수정실패");
            }
            // 업데이트 성공 시 OK 응답 반환
            return new ResponseEntity<>("수정완료", HttpStatus.OK);
        } catch (Exception e) {
            // 예외가 발생한 경우 에러 메시지와 함께 BAD_REQUEST 응답 반환
            e.printStackTrace();
            return new ResponseEntity<>("수정에러", HttpStatus.BAD_REQUEST);
        }
    }

    // 삭제
    @PostMapping("delete")
    public String deleteComment(@RequestParam("commentId") long commentId, @RequestParam("bbsId") String bbsId) {
        commentService.deleteComment(commentId, bbsId);
        // 댓글 삭제 후, 해당 게시글의 상세 페이지로 이동
        return "redirect:/bbs/" + bbsId;
    }



    // 지정한 댓글을 삭제하는 메소드
    @DeleteMapping("/{commentId}")
    public ResponseEntity<String> remove(@PathVariable("commentId") long commentId, @RequestParam("bbsId") String bbsId) {
        try {
            int result = commentService.deleteComment(commentId, bbsId);

            if (result != 1) {
                // 댓글 삭제 실패
                // 로그나 메시지 출력 등 추가 필요
                System.out.println("Delete failed for commentId: " + commentId);
                return new ResponseEntity<>("실패", HttpStatus.BAD_REQUEST);
            }

            // 댓글 삭제 성공
            return new ResponseEntity<>("삭제완료", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("삭제실패", HttpStatus.BAD_REQUEST);
        }
    }
}
