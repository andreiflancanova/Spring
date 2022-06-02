package br.com.generation.pharmagen.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity

/*
 * @Entity indica para o SpringTools que o objeto criado vai se tornar uma
 * tabela no banco de dados. Uma classe do JPA Hibernate
 */

@Table(name = "tb_produto")

public class Produto {
		
		// Primary Key - Tabela Produto
		@Id

		// Auto-increment do MySQL no Java:
		@GeneratedValue(strategy = GenerationType.IDENTITY)

		private Long id;

		@NotNull
		@Size(min = 5, max = 100)
		private String item;

		@NotNull
		@Size(min = 3, max = 15)
		private int preco;
		
		@NotNull
		@Size(min = 1, max = 10)
		private int quant;

		@NotNull
		@Size(min = 5, max = 100)
		private String marca;

		// Criando a tabela Categoria e relacionando como uma ManyToOne com a Produto
		@ManyToOne
		@JsonIgnoreProperties("produto")
		private Categoria categoria;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getItem() {
			return item;
		}

		public void setItem(String item) {
			this.item = item;
		}

		public int getPreco() {
			return preco;
		}

		public void setPreco(int preco) {
			this.preco = preco;
		}

		public int getQuant() {
			return quant;
		}

		public void setQuant(int quant) {
			this.quant = quant;
		}

		public String getMarca() {
			return marca;
		}

		public void setMarca(String marca) {
			this.marca = marca;
		}

		public Categoria getCategoria() {
			return categoria;
		}

		public void setCategoria(Categoria categoria) {
			this.categoria = categoria;
		}
}
