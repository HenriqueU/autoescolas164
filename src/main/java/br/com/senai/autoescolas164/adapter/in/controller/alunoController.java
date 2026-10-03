package br.com.senai.autoescolas164.adapter.in.controller;

import br.com.senai.autoescolas164.adapter.in.controller.assembler.alunoAssembler;
import br.com.senai.autoescolas164.adapter.in.controller.request.aluno.DadosAtualizacaoAluno;
import br.com.senai.autoescolas164.adapter.in.controller.request.aluno.DadosCadastroAluno;
import br.com.senai.autoescolas164.adapter.in.controller.response.aluno.DadosDetalhamentoAluno;
import br.com.senai.autoescolas164.adapter.in.controller.response.aluno.DadosListagemAluno;
import br.com.senai.autoescolas164.application.port.in.StdFeaturePort;
import br.com.senai.autoescolas164.application.service.AlunoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hibernate.sql.ast.tree.expression.Over;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/alunos")
@SecurityRequirement(name = "bearer-key")
@RequiredArgsConstructor
public class alunoController implements StdFeaturePort<
        DadosCadastroAluno,
        DadosListagemAluno,
        DadosAtualizacaoAluno,
        Void,
        DadosDetalhamentoAluno,
        UriComponentsBuilder,
        Long,
        Pageable
        > {

    private final AlunoService service;
    private final alunoAssembler assembler;

    @Override
    @PostMapping
    public ResponseEntity<EntityModel<DadosDetalhamentoAluno>> cadastrar(@RequestBody @Valid DadosCadastroAluno dados, UriComponentsBuilder uriBuilder) {
        DadosDetalhamentoAluno dto = service.cadastrarAluno(dados);
        URI uri = uriBuilder.path("/alunos/{id}").buildAndExpand(dto).toUri();
        return ResponseEntity.created(uri).body(assembler.toCreate(dto));
    }

    @Override
    @GetMapping
    public ResponseEntity<PagedModel<Page<DadosListagemAluno>>> listar(@PageableDefault(size=10, sort="nome") Pageable paginacao) {
        Page page = service.listarAlunos(paginacao);
        return ResponseEntity.ok(assembler.toList(page));
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<DadosDetalhamentoAluno>> detalhar(@PathVariable Long id) {
        DadosDetalhamentoAluno dto = service.detalharAluno(id);
        return ResponseEntity.ok(assembler.toDetail(dto));
    }

    @Override
    @PutMapping
    public ResponseEntity<DadosDetalhamentoAluno> atualizar(@RequestBody @Valid DadosAtualizacaoAluno dados) {
        return ResponseEntity.ok(service.atualizarAluno(dados));
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluirAluno(id);
        return ResponseEntity.noContent().build();
    }
}
