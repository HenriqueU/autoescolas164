package br.com.senai.autoescolas164.shared.vo.endereco;

import br.com.senai.autoescolas164.shared.vo.endereco.dto.DadosEndereco;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
public class Endereco {
    private String cep;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String uf;

    public Endereco() {
    }

    public Endereco(String cep, String logradouro, String numero, String complemento, String bairro, String cidade, String uf) {
        this.cep = cep;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
    }

    public String getCep() {
        return cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public String getUf() {
        return uf;
    }

    public void atualizar(
            String cep,
            String logradouro,
            String numero,
            String complemento,
            String bairro,
            String cidade,
            String uf
    ) {
        if (cep != null && !cep.isBlank()) {
            this.cep = cep;
        }
        if (logradouro != null && !logradouro.isBlank()) {
            this.logradouro = logradouro;
        }
        if (numero != null) {
            this.numero = numero;
        }
        if (complemento != null) {
            this.complemento = complemento;
        }
        if (bairro != null && !bairro.isBlank()) {
            this.bairro = bairro;
        }
        if (cidade != null && !cidade.isBlank()) {
            this.cidade = cidade;
        }
        if (uf != null && !uf.isBlank()) {
            this.uf = uf;
        }
    }
}
