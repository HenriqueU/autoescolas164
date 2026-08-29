package br.com.senai.autoescolas164.infra.exception;

import br.com.senai.autoescolas164.domain.agenda.InstrucaoNotFound;
import br.com.senai.autoescolas164.domain.agenda.ValidacaoException;
import br.com.senai.autoescolas164.domain.aluno.AlunoNotFoundException;
import br.com.senai.autoescolas164.domain.instrutor.InstrutorNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class TratadorGlobalErros {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Void> tratarNotFound() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(AlunoNotFoundException.class)
    public ResponseEntity<DadosException> tratarAlunoNotFound(AlunoNotFoundException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new DadosException(e.getMessage()));
    }

    @ExceptionHandler(InstrutorNotFoundException.class)
    public ResponseEntity<DadosException> tratarInstrutorNotFound(InstrutorNotFoundException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new DadosException(e.getMessage()));
    }

    @ExceptionHandler(ValidacaoException.class)
    public ResponseEntity<DadosException> tratarErrosValidacao(ValidacaoException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_ACCEPTABLE)
                .body(new DadosException(e.getMessage()));
    }

    @ExceptionHandler(InstrucaoNotFound.class)
    public ResponseEntity<DadosException> tratarInstrucaoNotFound(ValidacaoException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new DadosException(e.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<DadosException> tratarErroGenerico(Exception e) {
        return ResponseEntity.internalServerError().body(new DadosException(e.getMessage()));
    }

    private record DadosException(String erro) { }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<DadosBadRequest>> tratarBadRequest(MethodArgumentNotValidException e) {
        List<FieldError> erros = e.getFieldErrors();
        return ResponseEntity.badRequest().body(erros.stream().map(DadosBadRequest::new).toList());
    }

    private record DadosBadRequest(String campo, String mensagem) {
        public DadosBadRequest(FieldError erro) {
            this(erro.getField(), erro.getDefaultMessage());
        }
    }
}
