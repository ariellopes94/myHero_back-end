package com.myhero.resources;


import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myhero.domain.dto.input.CodigoDeSegurancaDTO;
import com.myhero.domain.dto.input.CpfDTO;
import com.myhero.security.JWTUtil;
import com.myhero.security.UserSpringSecurity;
import com.myhero.services.AuthService;
import com.myhero.services.UserService;


@RestController
@RequestMapping(value = "/auth")
public class AuthResource {
	
	@Autowired
	private JWTUtil jwtUtil;
	
	@Autowired
	private AuthService service;
	
	@PostMapping(value = "/refresh_token")
	public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
		UserSpringSecurity user = UserService.authenticated();
		String token = jwtUtil.generateToken(user.getUsername());
		response.addHeader("Authorization", "Bearer " + token);
		response.addHeader("access-control-expose-headers", "Authorization");
		return ResponseEntity.noContent().build();
	}
	
	
	@PostMapping(value = "/forgot")
	public ResponseEntity<Void> forgot(@Valid @RequestBody CodigoDeSegurancaDTO objDto) {
		service.sendNewPassword(objDto.getCodigoDeSeguranca());
		return ResponseEntity.noContent().build();
 }
	
	@PostMapping(value = "/esqueci_minha_senha")
	public ResponseEntity<Void> esqueciMinhaSenha(@Valid @RequestBody CpfDTO objDto) {
		service.gerarCodigoDeSeguranca(objDto.getCpf());
		return ResponseEntity.noContent().build();
 }
}