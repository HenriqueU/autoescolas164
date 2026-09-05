package br.com.senai.autoescolas164.application.service;

import br.com.senai.autoescolas164.adapter.in.controller.request.usuario.DadosAtualizacaoUsuario;
import br.com.senai.autoescolas164.adapter.in.controller.request.usuario.DadosCadastroUsuario;
import br.com.senai.autoescolas164.adapter.in.controller.response.usuario.DadosDetalhamentoUsuario;
import br.com.senai.autoescolas164.adapter.out.repository.UsuarioRepository;
import br.com.senai.autoescolas164.application.core.domain.Usuario;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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

    //Get
    /*@Transactional(readOnly = true)
    public @Nullable Page<UserDetails> listarUsuarios(Pageable paginacao) {
        return repository
                .findByLogin(paginacao);
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
