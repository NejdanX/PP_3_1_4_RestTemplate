package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static com.example.demo.Communication.*;

@SpringBootApplication
public class Main {

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);

		User user = new User(3, "James", "Brown", (byte) 22);
		System.out.println(getUsers().getBody());
		String postCode = postUser(user).getBody();
		user.setName("Thomas");
		user.setLastName("Shelby");
		String updateCode = updateUser(user).getBody();
		String deleteCode = deleteUser(user).getBody();
		System.out.println("Code = " + (postCode + updateCode + deleteCode));
	}
}
