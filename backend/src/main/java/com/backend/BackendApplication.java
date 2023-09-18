package com.backend;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;

import com.backend.model.Classe;
import com.backend.repository.AtorRepository;
import com.backend.repository.ClasseRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(ClasseRepository classeRepository) {
		return args -> {
			classeRepository.deleteAll();

			Classe classe = new Classe();
			classe.setNome("Edson Lima");
			classe.setValor(Double.parseDouble("30"));
			classe.setData(new Date());

			classeRepository.save(classe);
		};
	}
}
