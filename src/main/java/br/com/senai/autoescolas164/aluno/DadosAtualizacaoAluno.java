package br.com.senai.autoescolas164.aluno;

import br.com.senai.autoescolas164.endereco.DadosEndereco;

public record DadosAtualizacaoAluno(
        Long id,
        String nome,
        String email,
        String telefone,
        DadosEndereco endereco
) {
}
