package br.com.senai.autoescolas164.adapter.out.repository.mapper;

import br.com.senai.autoescolas164.adapter.out.repository.entity.AlunoEntity;
import br.com.senai.autoescolas164.application.core.domain.Aluno;
import org.springframework.stereotype.Component;

@Component
public class AlunoEntityMapper {
    public AlunoEntity toEntity(Aluno domain) {
        return new AlunoEntity(
                domain.getId(),
                domain.getNome(),
                domain.getCpf(),
                domain.getEmail(),
                domain.getTelefone(),
                domain.isAtivo(),
                domain.getEndereco()
        );
    }

    public Aluno toDomain(AlunoEntity entity) {
        return new Aluno(
                entity.getNome(),
                entity.getCpf(),
                entity.getEmail(),
                entity.getTelefone(),
                entity.getEndereco()
        );
    }
}
