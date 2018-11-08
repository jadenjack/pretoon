package com.naver.pretoon.Graph;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.naver.pretoon.Member.MemberMapper;
import com.naver.pretoon.Member.VoteVO;
import com.naver.pretoon.Util.JsonConvertor;

@Controller
@RequestMapping("/{webtoon}")
public class GraphController {
	@Autowired
	private MemberMapper mapper;

	private final JsonConvertor<VoteVO> convertor = new JsonConvertor<VoteVO>();
	
	@RequestMapping(value="/graph")
	public String barChart(Model model, @PathVariable("webtoon") String webtoon) throws Exception {
		List<VoteVO> list = mapper.selectVoteData(webtoon);

		String jsonList = convertor.convert(list);
		model.addAttribute("list", jsonList);
		return "graph" ;
	}
}
