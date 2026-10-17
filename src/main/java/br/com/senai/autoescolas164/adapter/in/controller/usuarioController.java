package br.com.senai.autoescolas164.adapter.in.controller;

import br.com.senai.autoescolas164.adapter.in.controller.request.usuario.DadosAtualizacaoUsuario;
import br.com.senai.autoescolas164.adapter.in.controller.request.usuario.DadosCadastroUsuario;
import br.com.senai.autoescolas164.adapter.in.controller.response.usuario.DadosDetalhamentoUsuario;
import br.com.senai.autoescolas164.application.port.in.StdFeaturePort;
import br.com.senai.autoescolas164.application.port.in.UsuarioFeaturePort;
import br.com.senai.autoescolas164.application.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class usuarioController implements UsuarioFeaturePort<
        DadosCadastroUsuario,
        DadosAtualizacaoUsuario,
        Void,
        DadosDetalhamentoUsuario,
        UriComponentsBuilder,
        Long,
        Pageable
        > {

    private final UsuarioService service;

    @PostMapping
    public ResponseEntity<DadosDetalhamentoUsuario> cadastrar(@RequestBody @Valid DadosCadastroUsuario dados, UriComponentsBuilder uriBuilder) {
        DadosDetalhamentoUsuario dto = service.cadastrarUsuario(dados);
        URI uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(dto).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    /*@GetMapping
    public ResponseEntity<Page<DadosDetalhamentoUsuario>> listar(@PageableDefault(size=10, sort="nome") Pageable paginacao) {
        return ResponseEntity.ok(service.listarUsuarios(paginacao));
    }*/

    @GetMapping("/{id}")
    @Transactional(readOnly = true)
    public ResponseEntity<DadosDetalhamentoUsuario> detalhar(@PathVariable Long id) {
        return ResponseEntity.ok(service.detalharUsuario(id));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoUsuario> atualizar(@RequestBody @Valid DadosAtualizacaoUsuario dados) {
        return ResponseEntity.ok(service.atualizarUsuario(dados));
    }

    @DeleteMapping("/id")
    @Transactional
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluirUsuario(id);
        return ResponseEntity.noContent().build();
    }
}
