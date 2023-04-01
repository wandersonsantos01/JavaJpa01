package br.com.alura.loja.modelo;

import javax.persistence.*;

@Entity
@Table(name = "categorias")
public class Categoria {

	@EmbeddedId
	private CategoriaId id;

	public Categoria() {
	}
	
	public Categoria(String nome) {
		this.id = new CategoriaId(nome, "abc");
	}

	public String getNome(){
		return this.id.getNome();
	}

}
