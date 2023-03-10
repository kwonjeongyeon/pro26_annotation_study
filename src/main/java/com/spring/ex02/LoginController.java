package com.spring.ex02;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

//@Controller("loginController")
public class LoginController {

	@RequestMapping(value = { "/test/loginForm.do", "/test/loginForm2.do" })
	ModelAndView loginForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("loginForm");
		return mav;
	}

	@RequestMapping(value = "/test/login.do", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response) throws Exception {
		/*
		 * request.setCharacterEncoding("UTF-8");
		 * response.setContentType("text/html; charset=utf-8");
		 */
		String userID = request.getParameter("userID");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("result");
		mav.addObject("userID", userID);
		return mav;
	}

	@RequestMapping(value = "/test/login2.do", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView login2(@RequestParam(value = "userID") String userID,
			@RequestParam(value = "userName") String userName, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("result");
		mav.addObject("userID", userID);
		mav.addObject("userName", userName);
		return mav;
	}
	
	/*
	 * Interface that defines a holder for model attributes. Primarily designed for
	 * adding attributes to the model.
	 * 
	 * Allows for accessing the overall model as a java.util.Map.
	 * 
	 * Model 클래스는 ModelAndView의 addObject()메서드와 같은 기능을 합니다.
	 * Model 클래스는 따로 뷰 정보를 전달할 필요가 업을 때 사용하면 편리합니다.
	 * 
	 */
	
	
	@RequestMapping(value = "/test/login5.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String login5(Model model, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		model.addAttribute("asdf", "nice");
//		model.addAttribute("userName", userName);
		return "result5";
	}


}
