package br.com.senai.autoescolas164.adapter.out.repository.persistence;


import br.com.senai.autoescolas164.adapter.out.repository.entity.InstrucaoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface InstrucaoJpaRepository extends JpaRepository<InstrucaoEntity, Long> {
    Page<InstrucaoEntity> findAllByAtivoTrue(Pageable paginacao);
}
