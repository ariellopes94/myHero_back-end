package com.myhero.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myhero.domain.Doenca;
import com.myhero.services.DoencaService;

@RestController
@RequestMapping(value = "doencas")
public class DoencaResource {
	
	@Autowired
	private DoencaService doencaService;
	
	@GetMapping
	public List<Doenca> list() {
		return doencaService.buscarTodos();
	}

}
