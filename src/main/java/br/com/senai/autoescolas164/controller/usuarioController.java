package br.com.senai.autoescolas164.controller;

import br.com.senai.autoescolas164.domain.usuario.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class usuarioController {

    private final UsuarioRepository service;

    @PostMapping
    public ResponseEntity<DadosDetalhamentoUsuario> cadastrarUsuario(@RequestBody @Valid DadosCadastroUsuario dados, UriComponentsBuilder uriBuilder) {
        DadosDetalhamentoUsuario dto = service.cadastrarUsuario(dados);
        URI uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(dto).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemUsuario>> listarUsuarios(@PageableDefault(size=10, sort="login") Pageable paginacao) {
        return ResponseEntity.ok(service.listarUsuarios(paginacao));
    }

    @GetMapping("/{id}")
    @Transactional(readOnly = true)
    public ResponseEntity<DadosDetalhamentoUsuario> detalharUsuario(@PathVariable Long id) {
        Usuario usuario = repository.findById(id).orElseThrow(() -> new RuntimeException("ID do usuário informado não existe!"));
        DadosDetalhamentoUsuario dto = new DadosDetalhamentoUsuario(usuario);
        return ResponseEntity.ok(dto);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoUsuario> atualizarUsuario(@RequestBody @Valid DadosAtualizacaoUsuario dados) {
        Usuario usuario = repository.findById(dados.id()).orElseThrow(() -> new RuntimeException("ID do usuário informado não existe!"));
        usuario.atualizar(dados);
        repository.save(usuario);
        return ResponseEntity.ok(new DadosDetalhamentoUsuario(usuario));
    }

    @DeleteMapping("/id")
    @Transactional
    public ResponseEntity<Void> excluirUsuario(@PathVariable Long id) {
        Usuario usuario = repository.findById(id).orElseThrow(() -> new RuntimeException("ID do usuário informado não existe!"));
        usuario.excluir();
        repository.save(usuario);
        return ResponseEntity.noContent().build();
    }
}
