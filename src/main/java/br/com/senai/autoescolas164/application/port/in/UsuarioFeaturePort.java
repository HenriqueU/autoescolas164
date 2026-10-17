package br.com.senai.autoescolas164.application.port.in;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface UsuarioFeaturePort<C, U, D, DET, URI, ID, PG> {
    ResponseEntity<DET> cadastrar(C dados, URI uriBuilder);
    /*ResponseEntity<Page<UD>> listar(PG paginacao);*/
    ResponseEntity<DET> detalhar(ID id);
    ResponseEntity<DET> atualizar(U dados);
    ResponseEntity<D> excluir(ID id);
}
