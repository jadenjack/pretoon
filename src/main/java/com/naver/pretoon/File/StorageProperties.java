package com.naver.pretoon.File;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("storage")
public class StorageProperties {
	
	public Path getLocation() {
		try {
			URI uri = this.getClass().getResource("/").toURI();
			File classFile = new File(uri);
			File imagePath = new File(classFile,"static/img/upload");
			return imagePath.toPath();
			//return Paths.get(uri);
		} catch (URISyntaxException e) {
			return null;
		}
	}
	
}
