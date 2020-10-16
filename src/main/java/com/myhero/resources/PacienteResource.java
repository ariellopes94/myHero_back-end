package com.myhero.resources;

import java.io.IOException;
import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.myhero.domain.Paciente;
import com.myhero.domain.assembler.PacienteModelAssembler;
import com.myhero.domain.dto.input.PacienteDto;
import com.myhero.domain.dto.output.CartaoQrCode;
import com.myhero.domain.dto.output.CpfDTOprofile;
import com.myhero.domain.dto.output.FichaPacienteDTO;
import com.myhero.services.PacienteService;
import com.myhero.services.QrCodeService;

@RestController
@RequestMapping(value = "pacientes")
public class PacienteResource {
	
	@Autowired
	private PacienteService pacienteService;
	
	 @Autowired
	 private QrCodeService qrCodeService;
	 
	 @Autowired
	 private PacienteModelAssembler pacienteModelAssembler;

	@GetMapping(value = "/{id}")
	public ResponseEntity<Paciente> findById(@PathVariable Long id){
		 Paciente obj = pacienteService.findById(id);
		 return ResponseEntity.ok().body(obj);
		
	} 
	
		
	//GERAR QR CODE
	
	@GetMapping(value="/gerarCodigoQrCode/{cpf}")
    public ResponseEntity<CartaoQrCode> gerarCartaoQrCode(@PathVariable String cpf){
		Paciente obj = pacienteService.findByCpf2(cpf); //Alterar para o findByCpf  -> para Autenciar o CPF
		
		return ResponseEntity.ok().body(pacienteModelAssembler.modelPacienteToCartaoQrCode(obj));
        //return qrCodeService.crateQRCode(obj.getCodigoGeradoPeloSistema(),200,200);
    }
	
	//BuscarPorCpf
	
	/*
	@GetMapping(value="/cpf")
	public ResponseEntity<Paciente> buscarPorCpf(@RequestParam(value = "value") String cpf){
		Paciente obj = pacienteService.findByCpf(cpf);
		return ResponseEntity.ok().body(obj);
	}
	
	*/
	
	@GetMapping(value="/cpf")
	public ResponseEntity<CpfDTOprofile> buscarPorCpf(@RequestParam(value = "value") String cpf){
		Paciente obj = pacienteService.findByCpf(cpf);
		return ResponseEntity.ok().body(pacienteModelAssembler.modelPacienteToCpfDTOprofile(obj));
	}
	
	//CLIENTE DTO
	
	@GetMapping(value="/fichaDoPaciente/{codigoGeradoPeloSistema}")
	public ResponseEntity<FichaPacienteDTO> codigoGeradoPeloSistema(@PathVariable String codigoGeradoPeloSistema){
		
		Paciente obj = pacienteService.findByCodigoGeradoPeloSistema(codigoGeradoPeloSistema);
		
		//RETORNO PACIENTEDTO
		return ResponseEntity.ok().body(pacienteModelAssembler.modelPacienteToFichaPacienteDTO(obj));
		/*
		FichaPacienteDTO obj = pacienteService.findByCodigoGeradoPeloSistema(codigoGeradoPeloSistema);
		return ResponseEntity.ok().body(obj);
		
		*/
	}
	
	@PostMapping
	public ResponseEntity<PacienteDto> insert( @RequestBody PacienteDto pacienteDto) {
	
		Paciente obj = pacienteService.insert(pacienteModelAssembler.modelPacienteDtoToPaciente(pacienteDto));

		//Paciente obj = pacienteService.insert(paciente);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").
			      buildAndExpand(obj.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Paciente> update(@Valid @RequestBody Paciente paciente , @PathVariable Long id){
		paciente.setId(id);
		pacienteService.update(paciente);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		pacienteService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
