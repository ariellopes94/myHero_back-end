package com.myhero.services;


import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.myhero.domain.Paciente;
import com.myhero.repositories.PacienteRepository;
import com.myhero.services.EnvioEmailService.Mensagem;
import com.myhero.services.exceptions.CodigoDeSeguranca;
import com.myhero.services.exceptions.ObjectNotFoundException;


@Service
public class AuthService {

	@Autowired
	private PacienteRepository pacienteRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private EnvioEmailService envioEmail;
	
	//@Autowired
	//private EmailService emailService;
	
	private Random rand = new Random();
	

	public void gerarCodigoDeSeguranca(String cpf) {
		
		Paciente p =VerificarExistenciaCpf(cpf);
		
		if(p.getCodigoDeSegurancaAtivado().equals(false)) {
			p.setCodigoDeSeguranca("ABNT45");
			p.setCodigoDeSegurancaAtivado(true);
			
			//Salvar Paciente
			pacienteRepository.save(p);
			
			//Envio EMAIL
			var mensagem = 	Mensagem.builder().assunto("Esqueci minha Senha")
					                          .corpo("codigo_de_seguranca.html")
					                          .variavel("paciente", p)
					                          .destinatario(p.getEmail())
					                          .build();     
			envioEmail.enviar(mensagem);
		}
		else {
			throw new  CodigoDeSeguranca("Codigo já gerado por Email");
		}
	}
	
	public Paciente VerificarExistenciaCpf(String cpf){
		
		Optional<Paciente> paciente = pacienteRepository.findByCpf(cpf);
		
		return paciente.orElseThrow(()->  new ObjectNotFoundException("Cpf não encontrado"));
	}
	
	
	
	public void sendNewPassword(String codSeguranca) {
		
		Paciente p =  pacienteRepository.findByCodigoDeSeguranca(codSeguranca);
		
		if(p == null) {
			throw new ObjectNotFoundException("Codigo Errado");
		}
		
		 if(p.getCodigoDeSegurancaAtivado() == true) {
		String newPass = newPassword();
		
		p.setSenha(passwordEncoder.encode(newPass));
		//Paciente.setSenha(passwordEncoder.encode(newPass));
		
		p.setCodigoDeSegurancaAtivado(false);
		
		
		pacienteRepository.save(p);
		p.setSenhaGeradaPorEmail(newPass);

		
		/// NOVA MENSAGEM AQUI
		//Envio EMAIL
				var mensagem = 	Mensagem.builder().assunto("Esqueci minha Senha")
						                          .corpo("esqueci-minha-senha.html")
						                          .variavel("paciente", p)
						                          .destinatario(p.getEmail())
						                          .build();     
				envioEmail.enviar(mensagem);
				
//
		}

	}

	private String newPassword() {
		char[] vet = new char[10];
		for (int i=0; i<10; i++) {
			vet[i] = randomChar();
		}
		return new String(vet);
	}

	private char randomChar() {
		int opt = rand.nextInt(3);
		if (opt == 0) { // gera um digito
			return (char) (rand.nextInt(10) + 48);
		}
		else if (opt == 1) { // gera letra maiuscula
			return (char) (rand.nextInt(26) + 65);
		}
		else { // gera letra minuscula
			return (char) (rand.nextInt(26) + 97);
		}
	}
}
