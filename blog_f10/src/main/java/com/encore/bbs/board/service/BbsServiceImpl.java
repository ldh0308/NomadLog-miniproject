package com.encore.bbs.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.encore.bbs.board.dto.BbsDTO;
import com.encore.bbs.board.mapper.BbsMapper;

@Service
public class BbsServiceImpl implements BbsService {
	
	@Autowired
	private BbsMapper bbsmapper;
	
	@Override
	public List<BbsDTO> selectBbsList() throws Exception {
		return bbsmapper.selectBbsList();
	}

	@Override
	public void insertBbs(BbsDTO bbs, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception {
		bbsmapper.insertBbs(bbs);

//		//게시글 저장 아이디 반환(getLatestBbsId 추가 service)
//		Long savedBbsId = bbsmapper.getLatestBbsId();
//
//		// 해시태그 저장을 위한 객체 생성
//		HashTag hashTag = new HashTag();
//		hashTag.setBbsId(savedBbsId); // 해당 객체에 저장아이디 반환
//		hashTag.setContent(content); // 해당 객체에 작성한 내용 반환
//
//		bbsmapper.insertHashtag(hashTag); // 해시태그 테이블 저장 서비스
	}

	@Override
	public BbsDTO selectBbsDetail(int bbsId) throws Exception {
		BbsDTO bbs = bbsmapper.selectBbsDetail(bbsId);
		bbsmapper.updateViewCount(bbsId);
		return bbs;
	}
	
	@Override
	public void updateBbs(BbsDTO bbs) throws Exception {
	  bbsmapper.updateBbs(bbs);
	}
	
	@Override
	public void deleteBbs(int bbsId) throws Exception {
		bbsmapper.deleteBbs(bbsId);

	}

}
