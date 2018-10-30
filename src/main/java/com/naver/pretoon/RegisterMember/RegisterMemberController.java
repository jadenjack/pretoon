package com.naver.pretoon.RegisterMember;

import java.security.SecureRandom;
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

@Controller
@RequestMapping("/{webtoon}")
public class RegisterMemberController {
	
	@Autowired
	private RegisterMemberMapper mapper;
	@Autowired
	private final StorageService storageService;
	
	static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	static SecureRandom rnd = new SecureRandom();
	
	@Autowired
    public RegisterMemberController(StorageService storageService) {
        this.storageService = storageService;
    }
	
	@RequestMapping("/members")
	public String main(ModelMap model, @PathVariable("webtoon") String webtoon) throws Exception{
		model.addAttribute("webtoon_name",webtoon);
		List<RegisterMemberVO> list = mapper.selectAll(webtoon);
		model.put("list",list);
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
		List<RegisterMemberVO> list = mapper.selectAll(webtoon);
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
		String randomString = getRandomString(10) + "_";
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
		List<RegisterMemberVO> list = mapper.selectAll(webtoon);
		model.put("list",list);
		return "vote";
	}
	@RequestMapping(value="/voteprocessing", method=RequestMethod.POST)
	public String votePerson(@PathVariable("webtoon") String webtoon, HttpServletRequest request) {
		String voted = request.getParameter("select");
		mapper.vote(webtoon, voted);
		return "redirect:members";
	}
	
	public String getRandomString(int len) {
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++)
			sb.append(AB.charAt(rnd.nextInt(AB.length())));
		return sb.toString();
	}
	
}
