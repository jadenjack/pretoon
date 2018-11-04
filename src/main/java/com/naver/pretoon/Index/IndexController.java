package com.naver.pretoon.Index;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.LinkedList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class IndexController {

	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/pretoon?useSSL=false";
	private static final String USER="root";
	private static final String PW = "dydwns2";
	
	@RequestMapping("/")
	public String index(Model model) throws Exception{
		
		Class.forName(DRIVER);
		Connection conn = null ;
		try {
			conn = DriverManager.getConnection(URL, USER, PW);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		DatabaseMetaData metaData = conn.getMetaData();
		ResultSet rs = metaData.getTables(null, null, "%", null);
		LinkedList<String> webtoonList = new LinkedList<String>();
		while (rs.next()) {
		  webtoonList.add(rs.getString(3));
		}
		
		model.addAttribute("webtoon_list",webtoonList);
		return "index";
	}
	
}
