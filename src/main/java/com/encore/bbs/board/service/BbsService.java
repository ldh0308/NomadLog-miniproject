package com.encore.bbs.board.service;

import java.util.List;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.encore.bbs.board.dto.BbsDTO;


public interface BbsService {
	
	 List<BbsDTO> selectBbsList() throws Exception ;
	 
	 void insertBbs(BbsDTO bbs, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception;
	 
	 BbsDTO selectBbsDetail(int bbsId) throws Exception;
	 
	 void updateBbs(BbsDTO bbs) throws Exception;
	 
	 void deleteBbs(int bbsId) throws Exception;



}