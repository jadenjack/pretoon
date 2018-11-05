package com.naver.pretoon.File;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("storage")
public class StorageProperties {
	
	private String location = "src/main/resources/static/img/upload";
	//private String location = "img/upload";
	
	@Autowired
    private ServletContext servletContext;
	
	public String getLocation() {
		return servletContext.getContextPath() + "/img/upload";
		//return location;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
	
}
