package com.encore.bbs.board.controller;  

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.encore.bbs.board.dto.BbsDTO;
import com.encore.bbs.board.service.BbsService;

import jakarta.servlet.http.HttpSession;

@Controller
public class BbsController { 
	
	@Autowired
	private BbsService bbsService; 
	
	@GetMapping("bbs")  //http://localhost:8080/bbs
	public ModelAndView openBbsList() throws Exception{
		ModelAndView mv = new ModelAndView("bbsList");
		
		List<BbsDTO> list = bbsService.selectBbsList();
		mv.addObject("list", list);
		
		return mv;
	}

	
	@GetMapping("bbs/write")
	public String openBbsWrite(HttpSession session) throws Exception {
		Integer loginId = (Integer) session.getAttribute("loginId");
		if(loginId == null) {
			return "login";
		}else {
			return "bbsWrite";
		}
		
		
	}

	@RequestMapping(value= "/bbs/write", method=RequestMethod.POST)
	public String insertBbs(BbsDTO bbs, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception {
		bbsService.insertBbs(bbs, multipartHttpServletRequest);


		return "redirect:/bbs";
	}

	
	@GetMapping("bbs/{bbsId}")
	public ModelAndView openBbsDetail(@PathVariable("bbsId") int bbsId, HttpSession session) throws Exception {
		ModelAndView mv = new ModelAndView("bbsDetail");
		Integer loginId = (Integer)session.getAttribute("loginId");
		BbsDTO bbs = bbsService.selectBbsDetail(bbsId);
		mv.addObject("bbs", bbs);
		 mv.addObject("loginId", loginId); 
		return mv;

	}

//	@RequestMapping(value= "/bbs/{bbsId}", method=RequestMethod.PUT)  // 수정요청
	@PostMapping("bbs/{bbsId}")
	public String updateBbs(BbsDTO bbs) throws Exception {
	  bbsService.updateBbs(bbs);         //게시글 수정
	  return "redirect:/bbs";  //수정완료 후 게시판 목록으로
	}

	@RequestMapping(value= "/bbs/{bbsId}", method=RequestMethod.DELETE)  //삭제요청
	public String deleteBbs(@PathVariable("bbsId") int bbsId) throws Exception {
	  bbsService.deleteBbs(bbsId);      //게시글 삭제
	  return "redirect:/bbs";  //삭제완료 후 게시판 목록으로

	     }
    }
