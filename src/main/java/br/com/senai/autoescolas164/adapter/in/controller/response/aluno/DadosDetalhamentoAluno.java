package br.com.senai.autoescolas164.adapter.in.controller.response.aluno;

import br.com.senai.autoescolas164.application.core.domain.Aluno;
import br.com.senai.autoescolas164.shared.vo.endereco.Endereco;
import br.com.senai.autoescolas164.shared.vo.endereco.dto.DadosEndereco;

public record DadosDetalhamentoAluno(
        Long id,
        String nome,
        String cpf,
        String email,
        String telefone,
        DadosEndereco endereco
) {
}
