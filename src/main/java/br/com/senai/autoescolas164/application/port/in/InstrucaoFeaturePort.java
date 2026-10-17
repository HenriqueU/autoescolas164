package br.com.senai.autoescolas164.application.port.in;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface InstrucaoFeaturePort<C, D, DET, ID, PG> {
    ResponseEntity<DET> agendar(C dados);
    ResponseEntity<Page<DET>> listar(PG paginacao);
    ResponseEntity<DET> detalhar(ID id);
    ResponseEntity<D> deletar(ID id);
}
