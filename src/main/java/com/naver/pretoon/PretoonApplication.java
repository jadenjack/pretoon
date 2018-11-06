package com.naver.pretoon;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.naver.pretoon.File.StorageProperties;
import com.naver.pretoon.File.StorageService;

@Configuration
//@EnableAutoConfiguration
@SpringBootApplication
@MapperScan(value= {"com.naver.pretoon.RegisterMember"})
@EnableConfigurationProperties(StorageProperties.class)
public class PretoonApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(PretoonApplication.class, args);
	}
	
	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception{
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);
		
		Resource[] res = new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*Mapper.xml");
        sessionFactory.setMapperLocations(res);
		
		return sessionFactory.getObject();
	}
	
	@Bean
	CommandLineRunner init(StorageService storageService) {
		return (args)->{
			//storageService.deleteAll();
			storageService.init();
		};
	}
	
	@Override
	  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	    return application.sources(PretoonApplication.class);
	  }
}
