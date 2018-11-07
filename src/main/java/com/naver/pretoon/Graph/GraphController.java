package com.naver.pretoon.Graph;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.naver.pretoon.Member.MemberMapper;
import com.naver.pretoon.Member.MemberVO;

@Controller
@RequestMapping("/{webtoon}")
public class GraphController {
	@Autowired
	private MemberMapper mapper;
	
	@RequestMapping("/graph")
	public String barChart(ModelMap model, @PathVariable("webtoon") String webtoon, HttpServletRequest request) throws Exception {
		List<MemberVO> memberList = mapper.selectAll(webtoon);
		model.addAttribute("list", memberList);
		return "graph";
	}
}
