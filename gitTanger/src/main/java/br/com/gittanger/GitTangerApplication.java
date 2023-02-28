package br.com.gittanger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GitTangerApplication {

	public static void main(String[] args) {
		System.setProperty("server.port", "7777");
		SpringApplication.run(GitTangerApplication.class, args);
	}

}
