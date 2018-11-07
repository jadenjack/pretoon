package com.naver.pretoon.Member;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.naver.pretoon.File.FileUploadController;
import com.naver.pretoon.File.StorageService;
import com.naver.pretoon.Util.CONSTANT_STRINGS;
import com.naver.pretoon.Util.RandomStringGenerator;

@Controller
@RequestMapping("/{webtoon}")
public class MemberController {
	
	@Autowired
	private MemberMapper mapper;
	@Autowired
	private final StorageService storageService;
	
	
	@Autowired
    public MemberController(StorageService storageService) {
        this.storageService = storageService;
    }
	
	@RequestMapping("/members")
	public String main(ModelMap model, @PathVariable("webtoon") String webtoon, HttpServletRequest request) throws Exception{
		List<MemberVO> list = mapper.selectAll(webtoon);
		String requestIP = request.getRemoteAddr();
		
		boolean alreadyVote = mapper.voteCheck(CONSTANT_STRINGS.VOTECHECK_TABLE, requestIP, webtoon);
		model.addAttribute("webtoon_name",webtoon);
		model.put("list",list);
		model.addAttribute("alreadyVote",alreadyVote);
		return "members";
	}
	
	@RequestMapping("/register")
	public String register(Model model,@PathVariable("webtoon") String webtoon) {
		model.addAttribute("webtoon_name",webtoon);
		
        model.addAttribute("files", storageService.loadAll().map(
                path -> MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
                        "serveFile", path.getFileName().toString()).build().toString())
                .collect(Collectors.toList()));
		
		return "registerperson";
	}
	@RequestMapping(value="/selectAll")
	public String selectAll(@PathVariable("webtoon") String webtoon) throws Exception {
		System.out.println("webtoon : " + webtoon);
		List<MemberVO> list = mapper.selectAll(webtoon);
//		for(int i=0;i<list.size();i++) {
//			RegisterMemberVO next = list.get(i);
//			System.out.println("name : " + next.getName() + "/Description : " + next.getDescription());
//		}
		return "index";
	}
	
	@RequestMapping(value="/registerprocessing", method=RequestMethod.POST)
	public String registerPerson(@RequestParam("person_image") MultipartFile imageFile,
			HttpServletRequest request,
			RedirectAttributes redirectAttributes) throws Exception {
		String webtoonName = request.getParameter("webtoon_name");
		String personName = request.getParameter("person_name");
		String personDescription = request.getParameter("person_description");
		String personImageName = imageFile.getOriginalFilename();
		String randomString = RandomStringGenerator.getRandomString(10) + "_";
		String randomFileName = randomString+personImageName;
		int vote = 0;
		mapper.insert(webtoonName,personName,personDescription,vote,randomFileName);
		
		storageService.store(imageFile,randomFileName);
        redirectAttributes.addFlashAttribute("message", "You successfully uploaded " + imageFile.getOriginalFilename() + "!");
        
		return "redirect:members";
	}
	
	@RequestMapping(value="/vote")
	public String votePerson(ModelMap model, @PathVariable("webtoon") String webtoon) throws Exception {
		model.addAttribute("webtoon_name",webtoon);
		List<MemberVO> list = mapper.selectAll(webtoon);
		model.put("list",list);
		return "vote";
	}
	// /vote에서 선택(select)된 인물의 정보를 받아서 투표수+1 한다.
	// (IP,웹툰이름)을 쌍으로 votecheck 테이블에 기록한다.
	@RequestMapping(value="/voteprocessing", method=RequestMethod.POST)
	public String votePerson(@PathVariable("webtoon") String webtoon, HttpServletRequest request) {
		String votedPerson = request.getParameter("select");
		String ip = request.getRemoteAddr();
		
		mapper.vote(webtoon, votedPerson);
		mapper.voteInsert(CONSTANT_STRINGS.VOTECHECK_TABLE, ip, webtoon);
		return "redirect:members";
	}
	
	
}
