package com.korea.visit;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import dao.VisitDAO;
import util.MyCommon;
import vo.VisitVO;

@Controller
public class VisitController {
	
	
	
	 VisitDAO visit_dao;
	 
	 public void setVisit_dao(VisitDAO visit_dao) {
		this.visit_dao = visit_dao;
	}
	
	
	
	@RequestMapping(value= {"/","visit_list.do"})
	public String list(Model model) {
		List<VisitVO> list = visit_dao.selectList();
		
		model.addAttribute("list",list);
		
		return MyCommon.VIEW_PATH + "visit_list.jsp";
		
	}
	
	
	@RequestMapping("insert_form.do")
	public String insert_from() {
		
		return MyCommon.VIEW_PATH + "visit_insert_form.jsp";
	}
	
	
	// 새 글 작성
	@RequestMapping("insert.do")
	public String insert(VisitVO vo , HttpServletRequest request) {
		String ip = request.getRemoteAddr();
		vo.setIp(ip);
		int res = visit_dao.insert(vo);
		
		return "redirect:visit_list.do";
	}
	
	
	// 게시글 삭제
	
	@RequestMapping("delete.do")
	@ResponseBody //return 값을 view형태로 인식하지 않고 콜백메서드로 전달을 하기위한 어노테이션
	public String delete(int idx,String pwd) {
		
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("idx", idx);
		map.put("pwd", pwd);
		
		
		int res = visit_dao.delete(map);
		
		String result = "no";
		
		if(res == 1) {
			result = "yes";
		}
		
		//JSON 형태로 값을 넘기자 
		String finRes = String.format("[{'res':'%s'}]", result);
		
		//@ResponseBody를 쓰이 않으면 return 값으로 들어간  [{'res':'%s'}].jsp 라는 jsp파일을 찾아간다
		return finRes;
	}
	
	
	
	
	@RequestMapping("modify_form.do")
	public String modify_form(Model model , int idx) {
		
		// 파라미터로 넘어온 idx를 이용해 해당 idx를 가진 게시물 정보를 가져온다.
		VisitVO vo = visit_dao.selectOne(idx);
		
		model.addAttribute("vo",vo);
		
		return MyCommon.VIEW_PATH + "visit_modify_form.jsp";
	
	}
	
	
	
	@RequestMapping("modify.do")
	public String modify(VisitVO vo , HttpServletRequest request) {
		String ip = request.getRemoteAddr();
		vo.setIp(ip);
		
		
		int res = visit_dao.modify(vo);
		
		return "redirect:visit_list.do";
	
	}	
	

}
