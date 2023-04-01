package br.com.alura.loja.modelo;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class DadosPessoais implements Serializable {
    private String nome;
    private String cpf;

    public DadosPessoais(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }

    public DadosPessoais() {
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }
}
