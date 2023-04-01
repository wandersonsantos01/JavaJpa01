package br.com.alura.loja.modelo;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
public class Livro extends Produto{
    private String autor;
    private Integer numeroPaginas;

    public Livro(String autor, Integer numeroPaginas) {
        this.autor = autor;
        this.numeroPaginas = numeroPaginas;
    }

    public Livro(String nome, String descricao, BigDecimal preco, Categoria categoria, String autor, Integer numeroPaginas) {
        super(nome, descricao, preco, categoria);
        this.autor = autor;
        this.numeroPaginas = numeroPaginas;
    }

    public Livro() {
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Integer getNumeroPaginas() {
        return numeroPaginas;
    }

    public void setNumeroPaginas(Integer numeroPaginas) {
        this.numeroPaginas = numeroPaginas;
    }
}
