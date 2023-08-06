package com.backend;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.backend.model.Ator;
import com.backend.repository.AtorRepository;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(AtorRepository atorRepository) {
		return args -> {
			atorRepository.deleteAll();

			Ator ator = new Ator();
			ator.setNome("Edson Lima");

			atorRepository.save(ator);
		};
	}
}
