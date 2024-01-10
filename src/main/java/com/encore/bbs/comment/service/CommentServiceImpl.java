package com.encore.bbs.comment.service;

import com.encore.bbs.board.dto.BbsDTO;
import com.encore.bbs.board.dto.HashTag;
import com.encore.bbs.board.mapper.BbsMapper;
import com.encore.bbs.comment.dto.CommentDTO;
import com.encore.bbs.comment.mapper.CommentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentMapper commentMapper;
    private final BbsMapper bbsMapper;
    @Override
    public List<CommentDTO> getCommentsByBbsId(Long bbsId) {

        return commentMapper.getCommentsByBbsId(bbsId);
    }

    //@Override
    //@Transactional
    //public Long addComment(BbsDTO bbsDTO, String content) {
        //CommentDTO bbsId = new CommentDTO();
       //commentMapper.getCommentsByBbsId(bbsId);
       //commentMapper.addComment(commentDTO);
       // Long savedBbsId = CommentMapper.getLatestBbsId();

       // HashTag hashTag = new HashTag();
      //  hashTag.setBbsId(savedBbsId); // 해당 객체에 저장아이디 반환
       // hashTag.setContent(content); // 해당 객체에 작성한 내용 반환

        //해시태그 인서트
       // bbsmapper.insertHashtag(hashTag);

    //}

    @Override
    @Transactional
    public void updateComment(Long commentId, CommentDTO commentDTO) {
        CommentDTO existingComment = commentMapper.findCommentById(commentId);

        if (existingComment != null) {  // 기존댓글이 존재한다면 , dto와 db update
            commentDTO.setCommentId(commentId);
            commentMapper.updateComment(commentDTO);
        }
    }

    @Override
    @Transactional
    public void deleteComment(Long commentId) {
        commentMapper.deleteComment(commentId);
    }

    @Override
    public int countAllComments() {
        return commentMapper.countAllComments();
    }

    @Override
    public CommentDTO findCommentById(Long commentId) {
        return commentMapper.findCommentById(commentId);
    }

    @Override
    public Long addComment(CommentDTO commentDTO) {
        return commentMapper.addComment(commentDTO);
    }
}