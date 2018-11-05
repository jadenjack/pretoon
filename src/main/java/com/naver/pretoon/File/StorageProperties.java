package com.naver.pretoon.File;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("storage")
public class StorageProperties {
	
	//private String location = "src/main/resources/static/img/upload";
	private String ROOT_PATH =this.getClass().getResource("/").getPath(); 
	private String location = ROOT_PATH + "static/img/upload";
	
	public String getLocation() {
		return location;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
	
}
