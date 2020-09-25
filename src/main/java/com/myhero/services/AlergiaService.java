package com.myhero.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myhero.domain.Alergia;
import com.myhero.repositories.AlergiaRepository;

@Service
public class AlergiaService {

	@Autowired
	private AlergiaRepository alergiaRepository;
	
	public List<Alergia> buscarTodos(){
		return alergiaRepository.findAll();
	}

}
