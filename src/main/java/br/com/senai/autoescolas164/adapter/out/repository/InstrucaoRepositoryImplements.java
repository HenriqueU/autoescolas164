package br.com.senai.autoescolas164.adapter.out.repository;

import br.com.senai.autoescolas164.adapter.out.repository.entity.InstrucaoEntity;
import br.com.senai.autoescolas164.adapter.out.repository.mapper.InstrucaoEntityMapper;
import br.com.senai.autoescolas164.adapter.out.repository.persistence.InstrucaoJpaRepository;
import br.com.senai.autoescolas164.application.core.domain.Instrucao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

//Adaptadores
@Component
@RequiredArgsConstructor
public class InstrucaoRepositoryImplements implements InstrucaoRepository {
    private final InstrucaoJpaRepository jpaRepository;
    private final InstrucaoEntityMapper entityMapper;

    @Override
    public boolean existsByInstrutorIdAndDataHora(Long idInstrutor, LocalDateTime DataHora) {
        return false;
    }

    @Override
    public boolean existsByAlunoIdAndDataHoraBetween(Long id, LocalDateTime inicio, LocalDateTime fim) {
        return false;
    }

    @Override
    public Instrucao save(Instrucao instrucao) {
        InstrucaoEntity entity = entityMapper.toEntity(instrucao);
        InstrucaoEntity salvo = jpaRepository.save(entity);
        return entityMapper.toDomain(salvo);
    }

    @Override
    public Optional<Instrucao> findById(Long id) {
        return jpaRepository
                .findById(id)
                .map(entityMapper::toDomain);
    }
}
