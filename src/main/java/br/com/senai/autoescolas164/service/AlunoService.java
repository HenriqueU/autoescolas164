package br.com.senai.autoescolas164.service;

import br.com.senai.autoescolas164.domain.aluno.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AlunoService {
    private final AlunoRepository repository;

    //Post
    @Transactional
    public DadosDetalhamentoAluno cadastrarAluno(DadosCadastroAluno dados) {
        Aluno aluno = new Aluno(dados);
        Aluno salvo = repository.save(aluno);
        return new DadosDetalhamentoAluno(salvo);
    }

    //Get
    @Transactional(readOnly = true)
    public @Nullable Page<DadosListagemAluno> listarAlunos(Pageable paginacao) {
        return repository
                .findAllByAtivoTrue(paginacao)
                .map(DadosListagemAluno::new);
    }

    //Get by ID
    @Transactional(readOnly = true)
    public @Nullable DadosDetalhamentoAluno detalharInstrutor(Long id) {
        Aluno aluno = repository.findById(id).orElseThrow(() -> new RuntimeException("ID do aluno informado não existe!"));
        return new DadosDetalhamentoAluno(aluno);
    }

    //Put
    @Transactional
    public @Nullable DadosDetalhamentoAluno atualizarAluno(@Valid DadosAtualizacaoAluno dados) {
        Aluno aluno = repository.findById(dados.id()).orElseThrow(() -> new RuntimeException("ID do aluno informado não existe!"));
        aluno.atualizar(dados);
        repository.save(aluno);
        return new DadosDetalhamentoAluno(aluno);
    }

    @Transactional
    public void excluirAluno(Long id) {
        Aluno aluno = repository.findById(id).orElseThrow(() -> new RuntimeException("ID do aluno informado não existe!"));
        aluno.excluir();
        repository.save(aluno);
    }
}
