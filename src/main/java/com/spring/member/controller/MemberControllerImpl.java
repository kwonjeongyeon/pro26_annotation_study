package com.spring.member.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.spring.member.service.MemberService;
import com.spring.member.service.MemberServiceImpl;
import com.spring.member.vo.MemberVO;

@Controller(value = "memberController")
public class MemberControllerImpl implements MemberController {

	/*
	 * Marks a constructor, field, setter method, or config method as to be
	 * autowired by Spring's dependency injection facilities.
	 */

	@Autowired
	private MemberService memberService;

	@Autowired
	private MemberVO memberVO;

	// memberService빈을 주입하기 위해 setter 구현
	/*
	 * public void setMemberService(MemberServiceImpl memberService) {
	 * this.memberService = memberService; }
	 */

	@Override
	@RequestMapping(value = "/member/listMembers.do", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView listMembers(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = getViewName(request);
		List<MemberVO> membersList = memberService.listMembers();
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("membersList", membersList);
		return mav;
	}

	private String getViewName(HttpServletRequest request) {
		String contextPath = request.getContextPath();
		String uri = (String) request.getAttribute("javax.servlet.include.request_uri");
		if (uri == null || uri.trim().equals("")) {
			uri = request.getRequestURI();
		}

		int begin = 0;
		if (!((contextPath == null) || ("".equals(contextPath)))) {
			begin = contextPath.length();
		}

		int end;
		if (uri.indexOf(";") != -1) {
			end = uri.indexOf(";");
		} else if (uri.indexOf("?") != -1) {
			end = uri.indexOf("?");
		} else {
			end = uri.length();
		}

		String fileName = uri.substring(begin, end);
		if (fileName.indexOf(".") != -1) {
			fileName = fileName.substring(0, fileName.lastIndexOf("."));
		}
		if (fileName.lastIndexOf("/") != -1) {
			fileName = fileName.substring(fileName.lastIndexOf("/"), fileName.length());
		}
		return fileName;
	}

	@Override
	@RequestMapping(value = "/member/addMember.do", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView addMember(@ModelAttribute("member") MemberVO member, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 회원 가입창에서 전송된 회원 정보를 바로 MemberVO 객체에 설정

		request.setCharacterEncoding("UTF-8");

		int result = 0;
		result = memberService.addMember(member); // 설정된 memberVO객체를 sql문으로 전달해 회원등록
		ModelAndView mav = new ModelAndView("redirect:/member/listMembers.do");
		return mav;
	}

	@Override
	@RequestMapping(value = "/member/deleteMember.do", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView deleteMember(@RequestParam("id") String id, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");

		memberService.deleteMember(id);
		ModelAndView mav = new ModelAndView("redirect:/member/listMembers.do");
		return mav;
	}

	// 데이터베이스 연동 작업이 없는 입력창 요청시 뷰이름만 ModelAndView로 반환
	@RequestMapping(value = "/member/*Form.do", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView memberForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = getViewName(request);
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		return mav;
	}

}
