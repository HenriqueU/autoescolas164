package br.com.senai.autoescolas164.adapter.in.controller.mapper;

import br.com.senai.autoescolas164.adapter.in.controller.request.aluno.DadosCadastroAluno;
import br.com.senai.autoescolas164.adapter.in.controller.response.aluno.DadosDetalhamentoAluno;
import br.com.senai.autoescolas164.adapter.in.controller.response.aluno.DadosListagemAluno;
import br.com.senai.autoescolas164.application.core.domain.Aluno;
import br.com.senai.autoescolas164.shared.vo.endereco.mapper.EnderecoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AlunoMapper {
    private final EnderecoMapper enderecoMapper;

    public Aluno toDomain(DadosCadastroAluno dados) {
        return new Aluno(
                dados.nome(),
                dados.cpf(),
                dados.email(),
                dados.telefone(),
                enderecoMapper.toEndereco(dados.endereco())
        );
    }

    public DadosDetalhamentoAluno toDetailDto(Aluno aluno) {
        return new DadosDetalhamentoAluno(
                aluno.getId(),
                aluno.getNome(),
                aluno.getCpf(),
                aluno.getEmail(),
                aluno.getTelefone(),
                enderecoMapper.toDto(aluno.getEndereco())
        );
    }

    public DadosListagemAluno toListDto(Aluno aluno) {
        return new DadosListagemAluno(
                aluno.getId(),
                aluno.getNome(),
                aluno.getEmail()
        );
    }
}
