package br.com.senai.autoescolas164.service;

import br.com.senai.autoescolas164.domain.usuario.*;
import jakarta.annotation.Nullable;
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

    //Get
    @Transactional(readOnly = true)
    public @Nullable Page<DadosListagemUsuario> listarUsuarios(Pageable paginacao) {
        return repository
                .findByLogin(paginacao)
                .map(DadosListagemUsuario::new);
    }
}
