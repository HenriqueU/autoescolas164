package br.com.senai.autoescolas164.service;

import br.com.senai.autoescolas164.domain.instrutor.*;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InstrutorService {
    private final InstrutorRepository repository;

    //Post
    @Transactional
    public DadosDetalhamentoInstrutor cadastrarInstrutor(DadosCadastroInstrutor dados) {
        Instrutor instrutor = new Instrutor(dados);
        Instrutor salvo = repository.save(instrutor);
        return new DadosDetalhamentoInstrutor(salvo);
    }

    //Get
    @Transactional(readOnly = true)
    public @Nullable Page<DadosListagemInstrutor> listarInstrutores(Pageable paginacao) {
        return repository
                .findAllByAtivoTrue(paginacao)
                .map(DadosListagemInstrutor::new);
    }

    //Get by ID
    @Transactional(readOnly = true)
    public @Nullable DadosDetalhamentoInstrutor detalharInstrutor(Long id) {
        Instrutor instrutor = repository.findById(id).orElseThrow(() -> new RuntimeException("ID do instrutor informado não existe"));
        return new DadosDetalhamentoInstrutor(instrutor);
    }

    //Put
    @Transactional
    public DadosDetalhamentoInstrutor atualizarInstrutor(DadosAtualizacaoInstrutor dados) {
        Instrutor instrutor = repository.findById(dados.id()).orElseThrow(() -> new RuntimeException("ID do instrutor informado não existe!"));
        instrutor.atualizar(dados);
        Instrutor salvo = repository.save(instrutor);
        return new DadosDetalhamentoInstrutor(salvo);
    }

    //Delete
    @Transactional
    public void excluirInstrutor(Long id) {
        Instrutor instrutor = repository.findById(id).orElseThrow(() -> new RuntimeException("ID do instrutor informado não existe!"));
        instrutor.excluir();
        repository.save(instrutor);
    }
}
