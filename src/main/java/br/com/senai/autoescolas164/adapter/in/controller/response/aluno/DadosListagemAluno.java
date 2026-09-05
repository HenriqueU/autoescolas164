package br.com.senai.autoescolas164.adapter.in.controller.response.aluno;

import br.com.senai.autoescolas164.application.core.domain.Aluno;

public record DadosListagemAluno(Long id, String nome, String email) {

    public DadosListagemAluno(Aluno aluno) {
        this(aluno.getId(), aluno.getNome(), aluno.getEmail());
    }
}
