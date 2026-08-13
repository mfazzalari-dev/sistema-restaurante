package com.restaurante.api;

import com.restaurante.api.model.Prato;
import com.restaurante.api.repository.PratoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

	@Bean
	public CommandLineRunner carregarDados(PratoRepository repository) {
		return args -> {
			Prato prato1 = new Prato();
			prato1.setNome("Hambúrguer Artesanal");
			prato1.setDescricao("Pão brioche, blend de 150g, queijo cheddar e molho especial.");
			prato1.setPreco(34.90);
			prato1.setCategoria("Prato Principal");

			Prato prato2 = new Prato();
			prato2.setNome("Batata Frita Rústica");
			prato2.setDescricao("Porção de batatas fritas com páprica e alecrim.");
			prato2.setPreco(19.90);
			prato2.setCategoria("Acompanhamento");

			Prato prato3 = new Prato();
			prato3.setNome("Suco Natural de Laranja");
			prato3.setDescricao("Suco de laranja natural, gelado e sem açúcar.");
			prato3.setPreco(9.00);
			prato3.setCategoria("Bebida");

			repository.save(prato1);
			repository.save(prato2);
			repository.save(prato3);
		};
	}
}