package com.spring.ex02;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

//@Controller("loginController4")
public class LoginController4 {

	@RequestMapping(value = "/test/loginForm.do", method = RequestMethod.GET)
	ModelAndView loginForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("loginForm");
		return mav;
	}

	// Annotation that binds a method parameter or method return value to a named
	// model attribute, exposed to a web view.
	
	@RequestMapping(value = "/test/login4.do", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView login4(@ModelAttribute("loginVO") LoginVO loginVO, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("result4");
		String userID = loginVO.getUserID();
		System.out.println(userID);
		/* String userName = loginVO.getUserName(); */
		/* mav.addObject("loginVO", loginVO); */
		//@RequestParam에서 설정한 map 이름으로 바인딩 
		return mav;
	}

}
