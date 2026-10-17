package br.com.senai.autoescolas164.adapter.in.controller;

import br.com.senai.autoescolas164.adapter.in.controller.request.instrucao.DadosAgendamento;
import br.com.senai.autoescolas164.adapter.in.controller.response.instrucao.DadosDetalhamentoAgendamento;
import br.com.senai.autoescolas164.application.port.in.InstrucaoFeaturePort;
import br.com.senai.autoescolas164.application.service.AgendaDeInstrucoes;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/instrucoes")
@RequiredArgsConstructor
public class InstrucaoController implements InstrucaoFeaturePort<
        DadosAgendamento,
        Void,
        DadosDetalhamentoAgendamento,
        Long,
        Pageable
        > {

    private final AgendaDeInstrucoes agenda;

    @PostMapping
    public ResponseEntity<DadosDetalhamentoAgendamento> agendar(@RequestBody @Valid DadosAgendamento dados) {
        return ResponseEntity.ok(agenda.agendar(dados));
    }

    @Override
    @GetMapping
    public ResponseEntity<Page<DadosDetalhamentoAgendamento>> listar(@PageableDefault(size=10) Pageable paginacao) {
        return ResponseEntity.ok(agenda.listarAgendamento(paginacao));
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoAgendamento> detalhar(@PathVariable Long id) {
        return ResponseEntity.ok(agenda.detalharAgendamento(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        agenda.excluirInstrucao(id);
        return ResponseEntity.noContent().build();
    }
}
