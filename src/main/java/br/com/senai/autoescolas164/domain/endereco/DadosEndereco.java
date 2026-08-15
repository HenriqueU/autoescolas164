package br.com.senai.autoescolas164.domain.endereco;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DadosEndereco(

        @NotBlank
        @Pattern(regexp = "[0-9]{5}-[0-9]{3}") //Informa como o CEP deve ser informado: 5 números de 0 a 9 + - + 3 números de 0 a 9 (Validação de dados)
        String cep,

        @NotBlank
        String logradouro,

        String numero,

        String complemento,

        @NotBlank
        String bairro,

        @NotBlank
        String cidade,

        @NotBlank
        @Pattern(regexp = "[A-Z]{2}") // Informa como a UF deve ser informada: Duas letras de A a Z (Validação de dados)
        String uf
) {


    public DadosEndereco(String cep, String logradouro, String numero, String complemento, String bairro, String cidade, String uf) {
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


    @Override
    public String toString() {
        return "DadosEndereco{" +
                "cep='" + cep + '\'' +
                ", logradouro='" + logradouro + '\'' +
                ", numero='" + numero + '\'' +
                ", complemento='" + complemento + '\'' +
                ", bairro='" + bairro + '\'' +
                ", cidade='" + cidade + '\'' +
                ", uf='" + uf + '\'' +
                '}';
    }
}
