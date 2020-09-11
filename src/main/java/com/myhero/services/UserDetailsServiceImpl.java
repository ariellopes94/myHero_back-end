package com.myhero.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.myhero.domain.Paciente;
import com.myhero.repositories.PacienteRepository;
import com.myhero.security.UserSpringSecurity;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	PacienteRepository repo;
	
	@Override
	public UserDetails loadUserByUsername(String cpf) throws UsernameNotFoundException {
		Optional<Paciente> paciente = repo.findByCpf(cpf);
		if(paciente == null) {
			throw new UsernameNotFoundException(cpf);
		}
		
		return new UserSpringSecurity(paciente.get().getId(), paciente.get().getCpf(),paciente.get().getSenha(), paciente.get().getPerfis());
	}

}
