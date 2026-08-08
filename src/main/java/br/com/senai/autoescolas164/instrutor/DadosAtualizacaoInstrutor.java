package br.com.senai.autoescolas164.instrutor;

import br.com.senai.autoescolas164.endereco.DadosEndereco;

public record DadosAtualizacaoInstrutor(
        Long id,
        String nome,
        String email,
        String telefone,
        Especialidade especialidade,
        DadosEndereco endereco
) {
}
