package br.com.senai.autoescolas164.domain.aluno;

import br.com.senai.autoescolas164.domain.endereco.Endereco;

public record DadosDetalhamentoAluno(Long id, String nome, String cpf, String email, String telefone, Endereco endereco) {

    public DadosDetalhamentoAluno(Aluno aluno) {
        this(
                aluno.getId(),
                aluno.getNome(),
                aluno.getCpf(),
                aluno.getEmail(),
                aluno.getTelefone(),
                aluno.getEndereco()
        );
    }
}
