package com.myhero.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myhero.domain.Doenca;
import com.myhero.repositories.DoencaRepository;

@Service
public class DoencaService {

	@Autowired
	private DoencaRepository doencaRepository;
	
	public List<Doenca> buscarTodos(){
		return doencaRepository.findAll();
	}
}
