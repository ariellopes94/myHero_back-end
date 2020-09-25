package com.myhero.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myhero.domain.Medicamento;
import com.myhero.repositories.MedicamentoRepository;

@Service
public class MedicamentoService {

	@Autowired
	private MedicamentoRepository medicamentoRepository;
	
	public List<Medicamento> buscarTodos(){
		return medicamentoRepository.findAll();
	}
}
