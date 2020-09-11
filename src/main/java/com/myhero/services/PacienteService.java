package com.myhero.services;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myhero.domain.Paciente;
import com.myhero.domain.dto.input.PacienteDto;
import com.myhero.domain.dto.output.FichaPacienteDTO;
import com.myhero.enuns.Perfil;
import com.myhero.enuns.TipoSanguinio;
import com.myhero.repositories.PacienteRepository;
import com.myhero.security.UserSpringSecurity;
import com.myhero.services.EnvioEmailService.Mensagem;
import com.myhero.services.exceptions.AuthotizationException;
import com.myhero.services.exceptions.ObjectNotFoundException;
import com.myhero.services.gerado_pelo_sistema.CodigoUnico;

@Service
public class PacienteService {

	@Autowired
	private PacienteRepository pacienteRepository;
	
	@Autowired
	private EnvioEmailService envioEmail;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private CodigoUnico codigoUnico;

	
	//private CodigoUnico codigoUnico; //GERAR CODIGO NO SISTEMA
	public void verificarExistencia(Paciente paciente) {
		findById(paciente.getId());
	
		
	}
	
	//DELETAR UM POUCO
	@Transactional
	public Paciente findByCpf2(String cpf) {
		 Optional<Paciente> obj = pacienteRepository.findByCpf(cpf);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Cpf não encontrado"));
	}
	
	///
	@Transactional
	public Paciente findByCpf(String cpf) {

	       UserSpringSecurity user = UserService.authenticated();
	       
	       if (user==null || !cpf.equals(user.getUsername())) {
				throw new AuthotizationException("Acesso negado");
			}
		 Optional<Paciente> obj = pacienteRepository.findByCpf(cpf);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Cpf não encontrado"));
	}
	
	
	@Transactional
    public Paciente findByCodigoGeradoPeloSistema(String codigoGeradoPeloSistema) {
             Optional<Paciente> obj = pacienteRepository.findByCodigoGeradoPeloSistema(codigoGeradoPeloSistema);
             return obj.orElseThrow(() -> new ObjectNotFoundException("Paciente não encontrado"));
		
		        
		                    // obj.get().setNome("YELLO"); //RETORNO AQUI
		                     
		                     
		                     
		                     //	obj.get().setNascimento(formato.parse(obj.get().getNascimento()));
		                    // obj.get().setTipoSanguinio();
		                     
		                   //  obj.get().setTipoSanguinio(obj.get().getTipoSanguinio().getDescricao());
		                     
		                    
		                    
		                    // System.out.println( obj.get().getTipoSanguinio().getDescricao());
		
	}
	
	@Transactional
	public Paciente findById(Long id) {
		
       UserSpringSecurity user = UserService.authenticated();
		
		if (user==null || !id.equals(user.getId())) {
			throw new AuthotizationException("Acesso negado");
		}
		Optional<Paciente> obj = pacienteRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Paciente não encontrado"));
		
		
	}
	

	public Paciente insert(Paciente p) {
		
		//return p;
		// paciente.setId(null);
		p.setId(null);

		Optional<Paciente> paciente;
		
		do {
			p.setCodigoGeradoPeloSistema(codigoUnico.idGeradoParaPaciente());	
			paciente = pacienteRepository.findByCodigoGeradoPeloSistema(p.getCodigoGeradoPeloSistema());
			}
			while (paciente.isPresent());
		
		enviarEmail(p);
		 try {
			 pacienteRepository.save(p);
			  // ENVIAR EMAIL MOVER PARA OUTRO LUGAR DPS
			 return p;
			} catch (Exception e) {
				return null;
			} 
		
		/*
		p.setCadastro(new Date());
		p.setCodigoDeSegurancaAtivado(false);
		
		p.setSenha(passwordEncoder.encode(p.getSenha()));
		
		//enviarEmail(paciente); // ENVIAR EMAIL MOVER PARA OUTRO LUGAR DPS
		
		try {
		 pacienteRepository.save(p);
		// enviarEmail(p); // ENVIAR EMAIL MOVER PARA OUTRO LUGAR DPS
		 return p;
		} catch (Exception e) {
			return null;
		} 
		*/
	}
		
	
	
	public void enviarEmail(Paciente paciente) {
		
		//Envio EMAIL
		var mensagem = 	Mensagem.builder().assunto("Olá , chegou seu cartão My Hero")
				                          .corpo("cartao-qrcode-enviado.html")
				                          .variavel("paciente", paciente)
				                          .destinatario("ariel-edit@hotmail.com")
				                          .build();     
		envioEmail.enviar(mensagem);
		
		
	}
	
	public Paciente update(Paciente paciente) {
		verificarExistencia(paciente);
		return pacienteRepository.save(paciente);
	}
	
	public void delete(Long id) {
		findById(id);
		pacienteRepository.deleteById(id);
		
	}
}
