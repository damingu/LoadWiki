package com.web.blog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.web.blog.controller.UserController;

@SpringBootApplication
@MapperScan(value="com.web.blog.model.repo")
public class BackendsubApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendsubApplication.class, args);
		
	}

}
