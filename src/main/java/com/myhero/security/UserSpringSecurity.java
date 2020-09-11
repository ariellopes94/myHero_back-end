package com.myhero.security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.myhero.enuns.Perfil;

public class UserSpringSecurity implements UserDetails {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String cpf;
	private String senha;
	private Collection<? extends GrantedAuthority> authorities;
	
	
	public UserSpringSecurity() {
	}
	
	
	
	public UserSpringSecurity(Long id, String cpf, String senha,
		Set<Perfil> perfis) {
		super();
		this.id = id;
		this.cpf = cpf;
		this.senha = senha;
		this.authorities = perfis.stream().map(x -> new SimpleGrantedAuthority(x.getDescricao())).collect(Collectors.toList());
	}



	public Long getId() {
		return id;
	}

	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return senha;
	}

	@Override
	public String getUsername() {
		return cpf;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	public boolean hasRole(Perfil perfil) {
		return getAuthorities().contains(new SimpleGrantedAuthority(perfil.getDescricao()));
	}
}
