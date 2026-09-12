package br.com.senai.autoescolas164.application.core.domain;

import br.com.senai.autoescolas164.shared.vo.endereco.Endereco;
import jakarta.persistence.*;

public class Aluno {
    private Long id;
    private String nome;
    private String cpf;
    private String email;
    private String telefone;
    private boolean ativo = true;
    private Endereco endereco;

    public Aluno() {
    }

    public Aluno(String nome, String cpf, String email, String telefone, Endereco endereco) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
        this.endereco = endereco;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void atualizar(String nome, String email, String telefone, Endereco endereco) {
        if (nome != null && !nome.isBlank()) {
            this.nome = nome;
        }
        if (email != null && !email.isBlank()) {
            this.email = email;
        }
        if (telefone != null && !telefone.isBlank()) {
            this.telefone = telefone;
        }
        if (endereco != null) {
            this.endereco.atualizar(
                    endereco.getCep(),
                    endereco.getLogradouro(),
                    endereco.getNumero(),
                    endereco.getComplemento(),
                    endereco.getBairro(),
                    endereco.getCidade(),
                    endereco.getUf()
            );
        }
    }

    public void excluir() {
        this.ativo = false;
    }
}
