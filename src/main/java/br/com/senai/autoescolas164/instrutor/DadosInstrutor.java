package br.com.senai.autoescolas164.instrutor;

import br.com.senai.autoescolas164.endereco.DadosEndereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DadosInstrutor(
        @NotBlank
        String nome,

        @NotBlank
        @Email
        String email,

        @NotBlank
        String telefone,

        @NotBlank
        @Pattern(regexp = "\\d{11}")
        String cnh,

        @NotNull
        Especialidade especialidade,

        @Valid
        DadosEndereco endereco
) {

    public DadosInstrutor(String nome, String email, String telefone, String cnh, Especialidade especialidade, DadosEndereco endereco) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.cnh = cnh;
        this.especialidade = especialidade;
        this.endereco = endereco;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }

    public Especialidade getEspecialidade() {
        return especialidade;
    }

    public String getCnh() {
        return cnh;
    }

    public DadosEndereco getEndereco() {
        return endereco;
    }

    @Override
    public String toString() {
        return "DadosInstrutor{" +
                "nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", telefone='" + telefone + '\'' +
                ", cnh='" + cnh + '\'' +
                ", especialidade='" + especialidade + '\'' +
                ", endereco=" + endereco +
                '}';
    }
}
