package br.com.senai.autoescolas164.adapter.in.controller.response.instrutor;

import br.com.senai.autoescolas164.application.core.domain.Instrutor;
import br.com.senai.autoescolas164.shared.vo.endereco.Endereco;
import br.com.senai.autoescolas164.shared.vo.endereco.dto.DadosEndereco;
import br.com.senai.autoescolas164.shared.vo.enums.Especialidade;

public record DadosDetalhamentoInstrutor(
        Long id,
        String nome,
        String email,
        String telefone,
        String cnh,
        Especialidade especialidade,
        DadosEndereco endereco,
        boolean ativo) {

}
