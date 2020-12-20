package com.zou.demo;

import com.zou.demo.config.ConditionConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Conditional(ConditionConfig.class)
	@Bean

	public A a() {
		return new A();
	}

	public class A {
		@PostConstruct
		public void start() {
			System.out.println("hello condition");
		}
	}

}
