package br.com.senai.autoescolas164.application.service;

import br.com.senai.autoescolas164.adapter.in.controller.mapper.AlunoMapper;
import br.com.senai.autoescolas164.adapter.in.controller.request.aluno.DadosAtualizacaoAluno;
import br.com.senai.autoescolas164.adapter.in.controller.request.aluno.DadosCadastroAluno;
import br.com.senai.autoescolas164.adapter.in.controller.response.aluno.DadosDetalhamentoAluno;
import br.com.senai.autoescolas164.adapter.in.controller.response.aluno.DadosListagemAluno;
import br.com.senai.autoescolas164.adapter.out.repository.AlunoRepository;
import br.com.senai.autoescolas164.application.core.domain.Aluno;
import br.com.senai.autoescolas164.shared.vo.endereco.mapper.EnderecoMapper;
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
    private final AlunoMapper mapper;
    private final EnderecoMapper enderecoMapper;

    //Post
    @Transactional
    public DadosDetalhamentoAluno cadastrarAluno(DadosCadastroAluno dados) {
        Aluno aluno = mapper.toDomain(dados);
        Aluno salvo = repository.save(aluno);
        return mapper.toDetailDto(salvo);
    }

    //Get
    @Transactional(readOnly = true)
    public @Nullable Page<DadosListagemAluno> listarAlunos(Pageable paginacao) {
        return repository
                .findAllByAtivoTrue(paginacao)
                .map(mapper::toListDto);
    }

    //Get by ID
    @Transactional(readOnly = true)
    public @Nullable DadosDetalhamentoAluno detalharAluno(Long id) {
        Aluno aluno = repository.findById(id).orElseThrow(() -> new RuntimeException("ID do aluno informado não existe!"));
        return mapper.toDetailDto(aluno);
    }

    //Put
    @Transactional
    public @Nullable DadosDetalhamentoAluno atualizarAluno(@Valid DadosAtualizacaoAluno dados) {
        Aluno aluno = repository.findById(dados.id()).orElseThrow(() -> new RuntimeException("ID do aluno informado não existe!"));
        aluno.atualizar(
                dados.nome(),
                dados.email(),
                dados.telefone(),
                enderecoMapper.toEndereco(dados.endereco())
        );
        Aluno salvo = repository.save(aluno);
        return mapper.toDetailDto(salvo);
    }

    //Delete
    @Transactional
    public void excluirAluno(Long id) {
        Aluno aluno = repository.findById(id).orElseThrow(() -> new RuntimeException("ID do aluno informado não existe!"));
        aluno.excluir();
        repository.save(aluno);
    }
}
