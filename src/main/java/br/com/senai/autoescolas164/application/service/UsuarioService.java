package br.com.senai.autoescolas164.application.service;

import br.com.senai.autoescolas164.adapter.in.controller.mapper.UsuarioMapper;
import br.com.senai.autoescolas164.adapter.in.controller.request.usuario.DadosAtualizacaoUsuario;
import br.com.senai.autoescolas164.adapter.in.controller.request.usuario.DadosCadastroUsuario;
import br.com.senai.autoescolas164.adapter.in.controller.response.usuario.DadosDetalhamentoUsuario;
import br.com.senai.autoescolas164.adapter.out.repository.UsuarioRepository;
import br.com.senai.autoescolas164.application.core.domain.Usuario;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository repository;
    private final UsuarioMapper mapper;

    //Post
    @Transactional
    public DadosDetalhamentoUsuario cadastrarUsuario(DadosCadastroUsuario dados) {
        Usuario usuario = mapper.toDomain(dados);
        Usuario salvo = repository.save(usuario);
        return mapper.toDetailDto(salvo);
    }

    //Get
    /*@Transactional(readOnly = true)
    public @Nullable Page<DadosDetalhamentoUsuario> listarUsuarios(Pageable paginacao) {
        return repository.findAllLogin(paginacao).map(mapper::toDetailDto);
    }*/

    //Get by ID
    @Cacheable(value = "usuarios", key = "#id")
    @Transactional(readOnly = true)
    public @Nullable DadosDetalhamentoUsuario detalharUsuario(Long id) {
        Usuario usuario = repository.findById(id).orElseThrow(() -> new RuntimeException("ID do usuário não encontrado!"));
        return mapper.toDetailDto(usuario);
    }

    //Put
    @CachePut(value = "usuarios", key = "dados.id()")
    @Transactional
    public DadosDetalhamentoUsuario atualizarUsuario(DadosAtualizacaoUsuario dados) {
        Usuario usuario = repository.findById(dados.id()).orElseThrow(() -> new RuntimeException("ID do usuário não encontrado!"));
        usuario.atualizar(
                dados.login(),
                dados.senha(),
                dados.perfil()
        );
        Usuario salvo = repository.save(usuario);
        return mapper.toDetailDto(salvo);
    }

    //Delete
    @CacheEvict(value = "usuarios", key = "#id")
    @Transactional
    public void excluirUsuario(Long id) {
        Usuario usuario = repository.findById(id).orElseThrow(() -> new RuntimeException("ID do usuário não encontrado!"));
        usuario.excluir();
        repository.save(usuario);
    }
}
