package br.com.generation.pharmagen.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.generation.pharmagen.model.Produto;
import br.com.generation.pharmagen.repository.ProdutoRepository;

//Definir como uma classe do tipo Controller
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/produtos")

public class ProdutoController {
	//Trazer as funções que foram criadas no Repository (para manipular o BD)

	@Autowired
	private ProdutoRepository ProdutoRepository;

	//Funções do CRUD
	@GetMapping
	/*
 	Colocando o @GetMapping, estou fazendo o programa executar o método getProduto
 	quando eu clico em GET no Postman.
	 */
	public ResponseEntity<List<Produto>> getProduto(){
		return ResponseEntity.ok(ProdutoRepository.findAll());
	}
	
	//Sub-rota do Get: Pegar pelo Id
	@GetMapping ("/{id_produto}")
	/*
	 	O @PathVariable, pega o valor diretamente pela URL com o GET do Postman.
	*/
	
	public ResponseEntity<Produto> getProdutoById(@PathVariable Long id_produto){
		return ProdutoRepository.findById(id_produto)
				//Método Option: Map e Função Lambda: resposta
				//Retorna um objeto do tipo Produto
				.map(resposta -> ResponseEntity.ok(resposta))
				//Retorna um "Not Found" no código do Postman se não houver nada
				.orElse(ResponseEntity.notFound().build());
	}
	@GetMapping("/marca_produto/{marca_produto}")
	
	//Aqui em cima, usamos o "/marca_produto" para que não corra risco de confundir a API
	
	public ResponseEntity<List<Produto>> getProdutoByMarca(@PathVariable String marca_produto){
		return ResponseEntity.ok(ProdutoRepository.findAllByMarcaContainingIgnoreCase(marca_produto));
	}
	
	@PostMapping
	public ResponseEntity<Produto> postProduto(@Valid @RequestBody Produto produto){
	/*
	 	Quando se fala de Post, temos, obrigatoriamente, que enviar
	 	informações para o meu banco de dados.A annotation @RequestBody
	 	pega a Body (o que vem no corpo da requisição)
	*/
		return ResponseEntity.status(HttpStatus.CREATED).body(ProdutoRepository.save(produto));
	}
	
	@PutMapping
	public ResponseEntity<Produto> updateProduto(@Valid @RequestBody Produto produto){
	/*
	 	Quando se fala de Post, temos, obrigatoriamente, que enviar
	 	informações para o meu banco de dados.A annotation @RequestBody
	 	pega a Body (o que vem no corpo da requisição)
	*/
		return ResponseEntity.status(HttpStatus.OK).body(ProdutoRepository.save(produto));
	}
	
	@DeleteMapping("/{id_produto}")
	public void deleteProduto(@PathVariable long id_produto) {
		ProdutoRepository.deleteById(id_produto);
	}
	
}
