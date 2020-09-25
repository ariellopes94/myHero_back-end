package com.myhero.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myhero.domain.Medicamento;
import com.myhero.services.MedicamentoService;

@RestController
@RequestMapping(value = "medicamentos")
public class MedicamentoResource {

	@Autowired
	private MedicamentoService medicamentoService;
	
	@GetMapping
	public List<Medicamento> list() {
		return medicamentoService.buscarTodos();
	}
}
