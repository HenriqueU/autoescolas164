package br.com.senai.autoescolas164.adapter.out.repository.mapper;

import br.com.senai.autoescolas164.adapter.out.repository.entity.InstrucaoEntity;
import br.com.senai.autoescolas164.application.core.domain.Instrucao;
import org.springframework.stereotype.Component;

@Component
public class InstrucaoEntityMapper {
    public InstrucaoEntity toEntity(Instrucao domain) {
        return new InstrucaoEntity(
                domain.getId(),
                domain.getAluno(),
                domain.getInstrutor(),
                domain.getDataHora(),
                domain.isAtivo()
        );
    }

    public Instrucao toDomain(InstrucaoEntity entity) {
        return new Instrucao(
                entity.getId(),
                entity.getAluno(),
                entity.getInstrutor(),
                entity.getDataHora(),
                entity.isAtivo()
        );
    }
}
