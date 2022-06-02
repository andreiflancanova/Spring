package br.com.generation.pharmagen.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.generation.pharmagen.model.Produto;

	@Repository
	//Vamos passar a Model e o tipo de ID no argumento do JpaRepository
	public interface ProdutoRepository extends JpaRepository <Produto, Long>{

	/*
		O Jpa converte através do FindAll tudo que está no Java 
		para a linguagem MySQL
	 */
		//Buscar todos pelo Nome do Item
		//Containing é o Like do MySQL
		//IgnoreCase não considera maiúsculo e minúsculo
		public List<Produto> findAllByMarcaContainingIgnoreCase(String marca_produto);
	}
