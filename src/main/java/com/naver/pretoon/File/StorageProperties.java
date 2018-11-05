package com.naver.pretoon.File;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("storage")
public class StorageProperties {
	
	//private String location = "src/main/resources/static/img/upload";
//	private String ROOT_PATH =this.getClass().getResource("/").getPath(); 
//	private String location = ROOT_PATH + "static/img/upload";
	private String location;
	
	public Path getLocation() {
		try {
			URI uri = this.getClass().getResource("/").toURI();
			return Paths.get(uri);
		} catch (URISyntaxException e) {
			return null;
		}
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
	
}
