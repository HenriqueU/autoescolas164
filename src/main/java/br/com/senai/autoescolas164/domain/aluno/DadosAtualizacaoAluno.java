package br.com.senai.autoescolas164.domain.aluno;

import br.com.senai.autoescolas164.domain.endereco.DadosEndereco;

public record DadosAtualizacaoAluno(
        Long id,
        String nome,
        String email,
        String telefone,
        DadosEndereco endereco
) {
}
