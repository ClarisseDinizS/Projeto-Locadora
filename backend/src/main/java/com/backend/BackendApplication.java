package com.backend;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.backend.model.Ator;
import com.backend.model.Classe;
import com.backend.model.Cliente;
import com.backend.model.Diretor;
import com.backend.model.Item;
import com.backend.model.Titulo;
import com.backend.repository.AtorRepository;
import com.backend.repository.ClasseRepository;
import com.backend.repository.ClienteRepository;
import com.backend.repository.DiretorRepository;
import com.backend.repository.ItemRepository;
import com.backend.repository.TituloRepository;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(ClasseRepository classeRepository, DiretorRepository diretorRepository,
			AtorRepository atorRepository, TituloRepository tituloRepository, 
			ItemRepository itemRepository, ClienteRepository clienteRepository) {
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
			List<Ator> atorList = new ArrayList<>();
			Ator ator = new Ator();
			ator.setNome("Clarisse Diniz");
			atorRepository.save(ator);
			atorList.add(ator);

			tituloRepository.deleteAll();
			List<Titulo> tituloList = new ArrayList<>();
			Titulo titulo = new Titulo();
			titulo.setNome("Harry Potter");
			titulo.setAno(LocalDate.now().minusYears(9));
			titulo.setSinopse("blablabla");
			titulo.setCategoria("Fantasia");
			titulo.setDiretor(diretor);
			titulo.setClasse(classe);
			titulo.setAtores(atorList);

			tituloRepository.save(titulo);
			tituloList.add(titulo);

			itemRepository.deleteAll();
			Item item = new Item();
			item.setNumserie(1);
			item.setDtaquisicao(LocalDate.now().minusYears(9));
			item.setTipoItem("Dvd");
			item.setTitulo(titulo);

			itemRepository.save(item);

			clienteRepository.deleteAll();
			Cliente cliente = new Cliente();
			cliente.setNome("Isabella");
			cliente.setDataNascimento(LocalDate.now());
			cliente.setNumeroInscricao(12);
			cliente.setSexo("Feminino");
			cliente.setEstahAtivo("Ativo");

			clienteRepository.save(cliente);

		};
	}
}
