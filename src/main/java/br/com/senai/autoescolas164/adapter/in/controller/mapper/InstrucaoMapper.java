package br.com.senai.autoescolas164.adapter.in.controller.mapper;

import br.com.senai.autoescolas164.adapter.in.controller.request.instrucao.DadosAgendamento;
import br.com.senai.autoescolas164.adapter.in.controller.response.instrucao.DadosDetalhamentoAgendamento;
import br.com.senai.autoescolas164.adapter.out.repository.AlunoRepositoryImplements;
import br.com.senai.autoescolas164.adapter.out.repository.InstrutorRepositoryImplements;
import br.com.senai.autoescolas164.adapter.out.repository.entity.AlunoEntity;
import br.com.senai.autoescolas164.adapter.out.repository.entity.InstrutorEntity;
import br.com.senai.autoescolas164.adapter.out.repository.mapper.AlunoEntityMapper;
import br.com.senai.autoescolas164.adapter.out.repository.mapper.InstrutorEntityMapper;
import br.com.senai.autoescolas164.application.core.domain.Aluno;
import br.com.senai.autoescolas164.application.core.domain.Instrucao;
import br.com.senai.autoescolas164.application.core.domain.Instrutor;
import br.com.senai.autoescolas164.shared.vo.endereco.mapper.EnderecoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InstrucaoMapper {
    private final AlunoRepositoryImplements alunoRepositoryImplements;
    private final InstrutorRepositoryImplements instrutorRepositoryImplements;
    private final AlunoEntityMapper alunoEntityMapper;
    private final InstrutorEntityMapper instrutorEntityMapper;

    public Instrucao toDomain(DadosAgendamento dados) {
        Aluno aluno = alunoRepositoryImplements.getReferenceById(dados.idAluno());
        AlunoEntity alunoEntity = alunoEntityMapper.toEntity(aluno);

        Instrutor instrutor = instrutorRepositoryImplements.getReferenceById(dados.idInstrutor());
        InstrutorEntity instrutorEntity = instrutorEntityMapper.toEntity(instrutor);

        return new Instrucao(
                null,
                alunoEntity,
                instrutorEntity,
                dados.dataHora(),
                true
        );
    }

    public DadosDetalhamentoAgendamento toDetailDto(Instrucao instrucao) {
        return new DadosDetalhamentoAgendamento(
                instrucao.getId(),
                instrucao.getAluno().getNome(),
                instrucao.getInstrutor().getNome(),
                instrucao.getInstrutor().getEspecialidade(),
                instrucao.getDataHora()
        );
    }
}
