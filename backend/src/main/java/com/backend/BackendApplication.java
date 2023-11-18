package com.backend;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.backend.enums.SimNao;
import com.backend.enums.Tipo;
import com.backend.model.Ator;
import com.backend.model.Classe;
import com.backend.model.Cliente;
import com.backend.model.Diretor;
import com.backend.model.Item;
import com.backend.model.Socio;
import com.backend.model.Titulo;
import com.backend.repository.AtorRepository;
import com.backend.repository.ClasseRepository;
import com.backend.repository.ClienteRepository;
import com.backend.repository.DiretorRepository;
import com.backend.repository.ItemRepository;
import com.backend.repository.SocioRepository;
import com.backend.repository.TituloRepository;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(ClasseRepository classeRepository, DiretorRepository diretorRepository,
			AtorRepository atorRepository, TituloRepository tituloRepository, 
			ItemRepository itemRepository, ClienteRepository clienteRepository,
			SocioRepository	socioRepository) {
		return args -> {
			
			// Criação das Classes
			classeRepository.deleteAll();
			Classe classe = new Classe();
			classe.setNome("Premium");
			classe.setValor(Double.parseDouble("30"));
			classe.setData(LocalDate.now());
			classeRepository.save(classe);

			Classe classe2 = new Classe();
			classe2.setNome("Infantil");
			classe2.setValor(Double.parseDouble("15"));
			classe2.setData(LocalDate.now());
			classeRepository.save(classe2);

			Classe classe3 = new Classe();
			classe3.setNome("Normais");
			classe3.setValor(Double.parseDouble("20"));
			classe3.setData(LocalDate.now());
			classeRepository.save(classe3);

			Classe classe4 = new Classe();
			classe4.setNome("Novidades");
			classe4.setValor(Double.parseDouble("25"));
			classe4.setData(LocalDate.now());
			classeRepository.save(classe4);

			// Criação dos Diretores
			diretorRepository.deleteAll();
			Diretor diretor = new Diretor();
			diretor.setNome("Stanley Kubrick");
			diretorRepository.save(diretor);

			Diretor diretor2 = new Diretor();
			diretor2.setNome("Steven Spielberg");
			diretorRepository.save(diretor2);

			Diretor diretor3 = new Diretor();
			diretor3.setNome("Quentin Tarantino");
			diretorRepository.save(diretor3);

			Diretor diretor4 = new Diretor();
			diretor4.setNome("Tim Burton");
			diretorRepository.save(diretor4);

			// Criação dos Atores
			atorRepository.deleteAll();
			List<Ator> atorList = new ArrayList<>();
			Ator ator = new Ator();
			ator.setNome("Tom Cruise");
			atorRepository.save(ator);
			atorList.add(ator);

			Ator ator2 = new Ator();
			ator2.setNome("Will Smith");
			atorRepository.save(ator2);
			atorList.add(ator2);

			Ator ator3 = new Ator();
			ator3.setNome("Leonardo DiCaprio");
			atorRepository.save(ator3);
			atorList.add(ator3);

			Ator ator4 = new Ator();
			ator4.setNome("Jackie Chan");
			atorRepository.save(ator4);
			atorList.add(ator4);

			Ator ator5 = new Ator();
			ator5.setNome("Keanu Reeves");
			atorRepository.save(ator5);
			atorList.add(ator5);

			// Criação dos Títulos
			tituloRepository.deleteAll();
			List<Titulo> tituloList = new ArrayList<>();
			Titulo titulo = new Titulo();
			titulo.setNome("A Queda");
			titulo.setAno(LocalDate.now().minusYears(3));
			titulo.setSinopse("Para as melhores amigas Becky e Hunter, a vida é sobre vencer medos e ultrapassar limites.");
			titulo.setCategoria("Suspense");
			titulo.setDiretor(diretor);
			titulo.setClasse(classe);
			titulo.setAtores(atorList);
			tituloRepository.save(titulo);
			tituloList.add(titulo);

			Titulo titulo2 = new Titulo();
			titulo2.setNome("Besouro Azul");
			titulo2.setAno(LocalDate.now().minusYears(2));
			titulo2.setSinopse("O adolescente Jaime Reyes ganha superpoderes quando um misterioso escaravelho se prende à sua coluna e lhe fornece uma poderosa armadura alienígena azul.");
			titulo2.setCategoria("Aventura/Ação");
			titulo2.setDiretor(diretor);
			titulo2.setClasse(classe);
			titulo2.setAtores(atorList);
			tituloRepository.save(titulo2);
			tituloList.add(titulo2);

			Titulo titulo3 = new Titulo();
			titulo3.setNome("A Freira 2");
			titulo3.setAno(LocalDate.now().minusYears(1));
			titulo3.setSinopse("Em 1956, na França, um padre é assassinado e parece que um mal está se espalhando. Determinada a deter o maligno, irmã Irene mais uma vez fica cara a cara com uma força demoníaca.");
			titulo3.setCategoria("Terror");
			titulo3.setDiretor(diretor2);
			titulo3.setClasse(classe);
			titulo3.setAtores(atorList);
			tituloRepository.save(titulo3);
			tituloList.add(titulo3);


			// Criação dos Itens
			itemRepository.deleteAll();
			Item item = new Item();
			item.setNumSerie(1);
			item.setDtaAquisicao(LocalDate.now().minusYears(9));
			item.setTipoItem(Tipo.DVD);
			item.setTitulo(titulo);
			itemRepository.save(item);

			Item item2 = new Item();
			item2.setNumSerie(2);
			item2.setDtaAquisicao(LocalDate.now().minusYears(4));
			item2.setTipoItem(Tipo.BLURAY);
			item2.setTitulo(titulo3);
			itemRepository.save(item2);

			Item item3 = new Item();
			item3.setNumSerie(3);
			item3.setDtaAquisicao(LocalDate.now().minusYears(7));
			item3.setTipoItem(Tipo.DVD);
			item3.setTitulo(titulo2);
			itemRepository.save(item3);

			Item item4 = new Item();
			item4.setNumSerie(4);
			item4.setDtaAquisicao(LocalDate.now().minusYears(3));
			item4.setTipoItem(Tipo.FITA);
			item4.setTitulo(titulo3);
			itemRepository.save(item4);

			// Criação dos Clientes
			clienteRepository.deleteAll();
			Cliente dependente = new Cliente();
			dependente.setNome("Isabella Ferreira");
			dependente.setNumeroInscricao(12);
			dependente.setDataNascimento(LocalDate.now());
			dependente.setSexo("Feminino");
			dependente.setEstahAtivo(SimNao.SIM);
			clienteRepository.save(dependente);

			Cliente dependente2 = new Cliente();
			dependente2.setNome("João Pedro Assis");
			dependente2.setNumeroInscricao(45);
			dependente2.setDataNascimento(LocalDate.now());
			dependente2.setSexo("Masculino");
			dependente2.setEstahAtivo(SimNao.SIM);
			clienteRepository.save(dependente2);

			// Criação dos Sócios
			socioRepository.deleteAll();
			Socio socio = new Socio();
			socio.setNome("Marcos Veltri");
			socio.setNumeroInscricao(89);
			socio.setDataNascimento(LocalDate.now());
			socio.setSexo("Masculino");
			socio.setCpf("12345678910");
			socio.setEndereco("Rua dos Bobos, 0");
			socio.setTelefone("12345678910");

			List<Cliente> dependentes = new ArrayList<>();
			dependentes.add(dependente);
			dependentes.add(dependente2);

			socio.setDependentes(dependentes);
			socioRepository.save(socio);

		};
	}
}
