package br.com.senai.autoescolas164.adapter.out.repository.persistence;


import br.com.senai.autoescolas164.adapter.out.repository.entity.InstrucaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstrucaoJpaRepository extends JpaRepository<InstrucaoEntity, Long> {
}
