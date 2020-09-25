package com.myhero.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myhero.domain.Alergia;
import com.myhero.services.AlergiaService;

@RestController
@RequestMapping(value = "alergias")
public class AlergiaResource {

	@Autowired
	private AlergiaService alergiaService;
	
	@GetMapping
	public List<Alergia> list() {
		return alergiaService.buscarTodos();
	}
}
