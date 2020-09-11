package com.myhero.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.myhero.security.JWTAuthenticationFilter;
import com.myhero.security.JWTAuthorizationFilter;
import com.myhero.security.JWTUtil;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private Environment env;
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private JWTUtil jwtUtil;
	
	
	private static final String[] PUBLIC_MATCHERS = {
			
			"/h2-console/**"
			
	};
	
	private static final String[] PUBLIC_MATCHERS_GET = {
			"/pacientes/fichaDoPaciente/{codigoGeradoPeloSistema}",
			"/pacientes/gerarCodigoQrCode/{cpf}",
		     "/myheroappweb/pacientes/fichaDoPaciente/{codigoGeradoPeloSistema}" //Deleta, apenas para testar api no hiroku
	};
	
	private static final String[] PUBLIC_MATCHERS_POST  = {
			"/pacientes/**",
			"/auth/esqueci_minha_senha/**",
			"/auth/forgot/**"
	};
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		if(Arrays.asList(env.getActiveProfiles()).contains("test")) {
			http.headers().frameOptions().disable();
		}
		
		
		http.cors().and().csrf().disable();
		http.authorizeRequests()
			.antMatchers(HttpMethod.POST, PUBLIC_MATCHERS_POST).permitAll() //Permitir somente os metodos POST publico
		    .antMatchers(HttpMethod.GET, PUBLIC_MATCHERS_GET).permitAll() //Permitir somente os metodos GET
		    .antMatchers(PUBLIC_MATCHERS).permitAll()   
			.anyRequest().authenticated();
		http.addFilter(new JWTAuthenticationFilter(authenticationManager(), jwtUtil)); //SEGURANÇA
		http.addFilter(new JWTAuthorizationFilter(authenticationManager(), jwtUtil, userDetailsService)); //SEGURANÇA
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
	}
	
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration().applyPermitDefaultValues();
		configuration.setAllowedMethods(Arrays.asList("POST", "GET", "PUT", "DELETE", "OPTIONS"));
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
