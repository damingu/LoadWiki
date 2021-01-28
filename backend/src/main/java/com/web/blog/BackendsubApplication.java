package com.web.blog;

import javax.annotation.Resource;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.web.blog.model.service.FilesStorageService;

@SpringBootApplication
@MapperScan(value = "com.web.blog.model.repo")
public class BackendsubApplication implements CommandLineRunner{
	@Resource
	FilesStorageService storageService;

	public static void main(String[] args) {
		SpringApplication.run(BackendsubApplication.class, args);

	}

	 @Override
	  public void run(String... arg) throws Exception {
	    storageService.deleteAll();
	    storageService.init();
	  }
}
