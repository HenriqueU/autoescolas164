package br.com.senai.autoescolas164.endereco;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Endereco {
    private String cep;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String uf;

    public Endereco(DadosEndereco dados) {
        this.cep = dados.getCep();
        this.logradouro = dados.getLogradouro();
        this.numero = dados.getNumero();
        this.complemento = dados.getComplemento();
        this.bairro = dados.getBairro();
        this.cidade = dados.getCidade();
        this.uf = dados.getUf();
    }
}
