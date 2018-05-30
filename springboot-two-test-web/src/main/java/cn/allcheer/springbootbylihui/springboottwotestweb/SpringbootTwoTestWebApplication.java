package cn.allcheer.springbootbylihui.springboottwotestweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "cn.allcheer.springbootbylihui")
public class SpringbootTwoTestWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootTwoTestWebApplication.class, args);
	}
}
