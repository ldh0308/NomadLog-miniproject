package com.encore.bbs.board.controller;

import java.util.List;

import com.encore.bbs.board.dto.CountryDto;
import com.encore.bbs.comment.dto.CommentDTO;
import com.encore.bbs.comment.service.CommentService;
import com.encore.bbs.member.dto.MemberDTO;
import com.encore.bbs.member.service.MemberService;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.encore.bbs.board.dto.BbsDTO;
import com.encore.bbs.board.service.BbsService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class BbsController {

	@Autowired
	private final BbsService bbsService;
	private final CommentService commentService;
	private final MemberService memberService;

	@GetMapping("bbs")  //http://localhost:8080/bbs
	public ModelAndView openBbsList() throws Exception{
		ModelAndView mv = new ModelAndView("/bbs/bbsList");

		List<BbsDTO> list = bbsService.selectBbsList();
		mv.addObject("list", list);
		return mv;
	}


	@GetMapping("bbs/write")
	public String openBbsWrite(HttpSession session,Model model) throws Exception {
		Integer loginId = (Integer) session.getAttribute("loginId");

		List<CountryDto> countryDtoList = bbsService.getCountryList();

		model.addAttribute("countryDtoList", countryDtoList);

		model.addAttribute("memberDTO", memberService.findAllCode(loginId));

		if(loginId == null) {
			return "/member/login";
		}else {
			return "/bbs/bbsWrite";
		}
	}

	@RequestMapping(value= "/bbs/write", method=RequestMethod.POST)
	public String insertBbs(BbsDTO bbs, @RequestParam String content) throws Exception {

		bbsService.insertBbs(bbs, content);


		return "redirect:/bbs";
	}


	@GetMapping("bbs/{bbsId}")
	public ModelAndView openBbsDetail(@PathVariable("bbsId") int bbsId, HttpSession session) throws Exception {
		ModelAndView mv = new ModelAndView("/bbs/bbsDetail");
		Integer loginId = (Integer)session.getAttribute("loginId");
		BbsDTO bbs = bbsService.selectBbsDetail(bbsId);

		List<CommentDTO> commentList = commentService.getCommentsByBbsId(bbsId);
		List<CountryDto> countryList = bbsService.findCountryById(bbsId);
		
		// 추가 사항
		MemberDTO memberDTO = memberService.findAllCode(bbs.getMemberCode());
		MemberDTO memberDTO2 = memberService.findAllCode(loginId);
		
		if(memberDTO2 != null) {
			String member = memberDTO2.getMemberId();
			mv.addObject("member", member);
		}
		mv.addObject("memberId", memberDTO2);
		
		
		mv.addObject("bbs", bbs);
		mv.addObject("loginId", loginId);
		mv.addObject("comments", commentList);
		mv.addObject("country", countryList);
		
		mv.addObject("memberDTO", memberDTO); 
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