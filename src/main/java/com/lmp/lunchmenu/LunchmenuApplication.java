package com.lmp.lunchmenu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
@SpringBootApplication
public class LunchmenuApplication {

	public static void main(String[] args) {
		SpringApplication.run(LunchmenuApplication.class, args);
	}

}
