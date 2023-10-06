package Monkeys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MonkeysApplication {

	public static void main(String[] args) {
		DatabaseController db = new DatabaseController();
		db.connect();
		
		
		SpringApplication.run(MonkeysApplication.class, args);
	}

}
