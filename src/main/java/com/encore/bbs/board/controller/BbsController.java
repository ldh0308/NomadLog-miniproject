package com.encore.bbs.board.controller;  

import java.util.List;

import com.encore.bbs.board.dto.CountryDto;
import com.encore.bbs.board.dto.HashTag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.encore.bbs.board.dto.BbsDTO;
import com.encore.bbs.board.service.BbsService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class BbsController { 
	
	private final BbsService bbsService;

	@GetMapping("bbs")  //http://localhost:8080/bbs
	public ModelAndView openBbsList() throws Exception{
		ModelAndView mv = new ModelAndView("bbsList");

		List<BbsDTO> list = bbsService.selectBbsList();
		mv.addObject("list", list);
		return mv;
	}

	
	@GetMapping("bbs/write")
	public String openBbsWrite(HttpSession session,Model model) throws Exception {
		List<CountryDto> countryDtoList = bbsService.getCountryList();
		model.addAttribute("countryDtoList", countryDtoList);

		Integer loginId = (Integer) session.getAttribute("loginId");
		if(loginId == null) {
			return "login";
		}else {
			return "bbsWrite";
		}
	}

	@RequestMapping(value= "/bbs/write", method=RequestMethod.POST)
	public String insertBbs(BbsDTO bbs, @RequestParam String content) throws Exception {

		bbsService.insertBbs(bbs, content); //게시글하고 태그 저장 메서드


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
