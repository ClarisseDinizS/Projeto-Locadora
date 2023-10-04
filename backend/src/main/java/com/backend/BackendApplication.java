package com.backend;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.backend.model.Ator;
import com.backend.model.Diretor;
import com.backend.model.Titulo;
import com.backend.repository.DiretorRepository;
import com.backend.repository.TituloRepository;
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
	CommandLineRunner initDatabase(ClasseRepository classeRepository, DiretorRepository diretorRepository, AtorRepository atorRepository,
								   TituloRepository tituloRepository) {
		return args -> {
			classeRepository.deleteAll();
			Classe classe = new Classe();
			classe.setNome("Dev Web");
			classe.setValor(Double.parseDouble("30"));
			classe.setData(LocalDate.now());
			classeRepository.save(classe);

			diretorRepository.deleteAll();
			Diretor diretor = new Diretor();
			diretor.setNome("Edson Lima");
			diretorRepository.save(diretor);

			atorRepository.deleteAll();
			List<Ator> atorList = new ArrayList<Ator>();
			Ator ator = new Ator();
			ator.setNome("Clarisse Diniz");
			atorRepository.save(ator);
			atorList.add(ator);

			tituloRepository.deleteAll();
			Titulo titulo = new Titulo();
			titulo.setNome("Harry Potter");
			titulo.setAno(LocalDate.now().minusYears(9));
			titulo.setSinopse("blablabla");
			titulo.setCategoria("Fantasia");
			titulo.setDiretor(diretor);
			titulo.setClasse(classe);
			titulo.setAtores(atorList);

			tituloRepository.save(titulo);




		};
	}
}
