package com.myhero.repositories;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.myhero.domain.Paciente;
import com.myhero.domain.dto.output.FichaPacienteDTO;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
     
	@Transactional(readOnly =true)
	Optional<Paciente> findByCpf(String cpf);
     
	//@Transactional(readOnly =true)
	//Paciente findByCpf(String cpf);
	
     //PacienteDTO
	 @Transactional(readOnly =true)
	 Optional<Paciente> findByCodigoGeradoPeloSistema(String codigoGeradoPeloSistema);
	 // Paciente findByCodigoGeradoPeloSistema(String codigoGeradoPeloSistema);
	 
	@Transactional(readOnly =true)
	 Paciente findByEmail(String email);
	

	@Transactional(readOnly = true)
	Paciente findByCodigoDeSeguranca(String codigoDeSeguranca);
	
	

}
