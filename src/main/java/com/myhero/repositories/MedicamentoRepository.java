package com.myhero.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myhero.domain.Medicamento;

@Repository
public interface MedicamentoRepository extends JpaRepository<Medicamento, Long>{
}
