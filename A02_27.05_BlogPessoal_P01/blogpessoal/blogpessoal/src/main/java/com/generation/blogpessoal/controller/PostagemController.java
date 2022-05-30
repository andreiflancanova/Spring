package com.generation.blogpessoal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.blogpessoal.model.Postagem;
import com.generation.blogpessoal.repository.PostagemRepository;

/*
 	@RestController serve para configurar essa classe como classe 
 	controladora da API (Onde ficam os Endpoints)
 */

@RestController

/*
	@CrossOrigin serve para que a classe aceite requisições de qualquer
	origem. Serve para mudar as portas de requisição de Back-End que o
	Front-End pede.
*/

/*
 	Quando o Front-End começar a consumir a nossa API, provavelmente ela
 	virá do Angular ou do React, razão pela qual é preciso que, independen-
 	temente da origem, nossa API aceite, e para isso usamos a anotação
 	@CrossOrigin
 */

@CrossOrigin(origins = "*")

/*
  	Cria um Endpoint (O "/" a gente coloca para fazer a separação da url)
 	Aqui NÃO PODE COLOCAR Letra maiúscula ou espaço
 */
@RequestMapping("/postagens")
public class PostagemController {
/*
 	Endpoints são caminhos que vão levar o meu programa a executar
 	alguma função na minha execução
 	
 	Como o controller é o que executa as funções, ele tem que trazer 
 	as funções que foram criadas em outro lugar. Nesse caso, criamos 
 	no Repository, porque o Repository conecta com o Banco de Dados.
 	
 	Para isso,temos que fazer uma injeção de dependência e fazemos isso
 	com o @Autowired, porque o Spring faz o instanciamento do Repository
 	toda vez sem precisarmos fazer isso manualmente. Dessa forma, ele transfere
 	a responsabilidade de instanciar o PostagemRepository várias vezes, sem
 	que eu tenha que fazer este instanciamento manualmente.
 */
	@Autowired
	private PostagemRepository PostagemRepository;
	//Colocando private só funciona dentro do PostagemController
	
	
	@GetMapping
	public ResponseEntity<List<Postagem>> buscaPostagem(){
		return ResponseEntity.ok(PostagemRepository.findAll());
	/*
	 	O GetMapping indica o verbo para requisição. Quando eu indicar o 
	 	GetMapping quero que ele indique o verbo que quero usar nessa requisição.
	 	Com isso, preparamos a requisição para encontrar um tipo de resposta
	 	"Postagem" e receber um ou vários valores, através do List (Lista de
	 	Postagens).
	 	
	 	Quando coloco o @GetMapping, vinculo o Get da API (PostMan) para fazer
	 	com que ele retorne o método buscaPostagem quando eu der o Get na API.
	 	
	 	Para criar algo:
	 	@PostMapping - Criar
	 	@PutMapping - Atualizar
	 	@DeleteMapping - Deletar
	*/
	}
	
	@GetMapping ("/{id}")
	/*
	 	Quando usamos a anotação @PathVariable, estamos fazendo ela pegar o valor
	 	diretamente pela URL.
	*/
	
	public ResponseEntity<Postagem> buscaPostagemPorId(@PathVariable Long id){
		return PostagemRepository.findById(id)
				//Método Option: Map e Função Lambda: resposta
				//Retorna um objeto do tipo Postagem
				.map(resposta -> ResponseEntity.ok(resposta))
				//Retorna um "Not Found" no código do Postman se não houver nada
				.orElse(ResponseEntity.notFound().build());
				
	/*
	 	Fazendo dessa forma, estamos fazendo uma sub-rota do primeiro Get.
	*/
	}
	
	@PostMapping
	public ResponseEntity<Postagem> adicionaPostagem(@RequestBody Postagem postagem){
	/*
	 	Quando se fala de Post, temos, obrigatoriamente, que enviar
	 	informações para o meu banco de dados.
	*/
		return ResponseEntity.status(HttpStatus.CREATED).body(PostagemRepository.save(postagem));
	}
	
}
