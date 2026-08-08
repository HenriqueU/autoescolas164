package br.com.senai.autoescolas164.controller;


import br.com.senai.autoescolas164.aluno.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/alunos")
public class alunoController {
    @Autowired
    private AlunoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoAluno> cadastrarAluno(@RequestBody @Valid DadosCadastroAluno dados, UriComponentsBuilder uriBuilder) {
        Aluno aluno = new Aluno(dados);
        Aluno salvo = repository.save(aluno);
        DadosDetalhamentoAluno dto = new DadosDetalhamentoAluno(salvo);
        URI uri = uriBuilder.path("/alunos/{id}").buildAndExpand(salvo.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @GetMapping
    @Transactional(readOnly = true)
    public ResponseEntity<Page<DadosListagemAluno>> listarAlunos(@PageableDefault(size=10, sort="nome") Pageable paginacao) {
        Page page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemAluno::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    @Transactional(readOnly = true)
    public ResponseEntity<DadosDetalhamentoAluno> detalharAluno(@PathVariable Long id) {
        Aluno aluno = repository.getReferenceById(id);
        DadosDetalhamentoAluno dto = new DadosDetalhamentoAluno(aluno);
        return ResponseEntity.ok(dto);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoAluno> atualizarAluno(@RequestBody @Valid DadosAtualizacaoAluno dados) {
        Aluno aluno = repository.getReferenceById(dados.id());
        aluno.atualizar(dados);
        repository.save(aluno);
        return ResponseEntity.ok(new DadosDetalhamentoAluno(aluno));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> excluirAluno(@PathVariable Long id) {
        Aluno aluno = repository.getReferenceById(id);
        aluno.excluir();
        repository.save(aluno);
        return ResponseEntity.noContent().build();
    }
}
