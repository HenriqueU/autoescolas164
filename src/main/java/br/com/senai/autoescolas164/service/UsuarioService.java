package br.com.senai.autoescolas164.service;

import br.com.senai.autoescolas164.domain.instrutor.DadosDetalhamentoInstrutor;
import br.com.senai.autoescolas164.domain.usuario.*;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Null;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository repository;

    //Post
    @Transactional
    public DadosDetalhamentoUsuario cadastrarUsuario(DadosCadastroUsuario dados) {
        Usuario usuario = new Usuario(dados);
        Usuario salvo = repository.save(usuario);
        return new DadosDetalhamentoUsuario(salvo);
    }

    /*Get
    @Transactional(readOnly = true)
    public @Nullable Page<DadosListagemUsuario> listarUsuarios(Pageable paginacao) {
        return repository
               .findByLogin(paginacao)
                .map(DadosListagemUsuario::new);
    }*/

    //Get by ID
    @Transactional(readOnly = true)
    public @Nullable DadosDetalhamentoUsuario detalharUsuario(Long id) {
        Usuario usuario = repository.findById(id).orElseThrow(() -> new RuntimeException("ID do usuário não encontrado!"));
        return new DadosDetalhamentoUsuario(usuario);
    }

    //Put
    @Transactional
    public @Nullable DadosDetalhamentoUsuario atualizarUsuario(@Valid DadosAtualizacaoUsuario dados) {
        Usuario usuario = repository.findById(dados.id()).orElseThrow(() -> new RuntimeException("ID do usuário não encontrado!"));
        usuario.atualizar(dados);
        repository.save(usuario);
        return new DadosDetalhamentoUsuario(usuario);
    }

    //Delete
    @Transactional
    public void excluirUsuario(Long id) {
        Usuario usuario = repository.findById(id).orElseThrow(() -> new RuntimeException("ID do usuário não encontrado!"));
        usuario.excluir();
        repository.save(usuario);
    }
}
