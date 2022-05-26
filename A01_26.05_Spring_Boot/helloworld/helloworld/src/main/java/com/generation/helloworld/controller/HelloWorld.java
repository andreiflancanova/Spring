package com.generation.helloworld.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// Classe controladora tem que ter o RestController e o RequestMapping
//Indica que a classe abaixo é a classe controladora
@RestController

//Request Mapping serve para a construção dos Endpoints da API
	//Endpoints necessitam de que tudo seja minúsculo e sem espaço
@RequestMapping("/hello")
public class HelloWorld {
	/*
	@GetMapping
	@PostMapping
	@PutMapping
	@DeleteMapping
	 */
	
	@GetMapping
	public String hello() {
		return "Hello World! Welcome to Spring!"
				+ "<br>MENTALIDADES"
				+ "<br>Orientação ao Futuro"
				+ "<br>Responsabilidade Pessoal"
				+ "<br>Mentalidade de Crescimento"
				+ "<br>Persistência"
				+ "<br>HABILIDADES"
				+ "<br>Trabalho em Equipe"
				+ "<br>Atenção aos Detalhes"
				+ "<br>Proatividade"
				+ "<br>Comunicação";
	}
	//É um verbo que não precisa de corpo de requisição
}
