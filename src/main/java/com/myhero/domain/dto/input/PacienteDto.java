package com.myhero.domain.dto.input;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

import com.myhero.domain.Alergia;
import com.myhero.domain.ContatoDeEmergencia;
import com.myhero.domain.Doenca;
import com.myhero.domain.Medicamento;
import com.myhero.enuns.Estado;
import com.myhero.enuns.Sexo;
import com.myhero.enuns.TipoSanguinio;

public class PacienteDto {

	
	//private Long id;
	//private Date cadastro;
	//private String codigoGeradoPeloSistema;
	
	@CPF(message = "CPF Inválido")
	@Column(unique = true)
	private String cpf;
	
	@NotEmpty(message = "Nome é obrigatório")
	private String nome;

	@NotEmpty(message = "Email é obrigatório")
	private String email;
	
	@NotEmpty(message = "Imagem é Obrigatorio")
	private String imageAvatarUrl;
	
	@NotEmpty(message = "Senha é obrigatório")
	//@JsonIgnore
	private String senha;
	
	//@NotEmpty(message = "Doador de orgão é obrigatório")
	@NotNull(message = "Doador de orgão é obrigatório")
	private Boolean doadorDeOrgao; 
	
	@NotEmpty(message = "Telefone é obrigatório")
	private String telefone;
	
	@NotNull(message = "Peso obrigatório")
	private Double peso;
	
	@NotNull(message = "Altura é obrigatório")
	private Double altura;
	
	@NotNull(message = "Data de Nascimento é obrigatório")
	private Date nascimento;
	
	@NotNull(message = "Sexo é obrigatório")
	private Integer sexo;
	
	@NotNull(message = "Tipo Sanguinio é obrigatório")
	private Integer tipoSanguinio;
	
	@NotNull(message = "Estado de Moradia é obrigatório")
	private Integer estadoMoradia;
	
	private String observacao; 
	
	 
	private List<Medicamento> medicamentos = new ArrayList<>();
	private List<Doenca> doencas = new ArrayList<>();
	private List<Alergia> alergias = new ArrayList<>();
	private List<ContatoDeEmergencia> contatosDeEmergencias = new ArrayList<>(); 
	private Set<Integer> perfis = new HashSet<>();
	
	
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getImageAvatarUrl() {
		return imageAvatarUrl;
	}
	
	public void setImageAvatarUrl(String imageAvatarUrl) {
		this.imageAvatarUrl = imageAvatarUrl;
	}
	
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public Boolean getDoadorDeOrgao() {
		return doadorDeOrgao;
	}
	public void setDoadorDeOrgao(Boolean doadorDeOrgao) {
		this.doadorDeOrgao = doadorDeOrgao;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public Double getPeso() {
		return peso;
	}
	public void setPeso(Double peso) {
		this.peso = peso;
	}
	public Double getAltura() {
		return altura;
	}
	public void setAltura(Double altura) {
		this.altura = altura;
	}
	public Date getNascimento() {
		return nascimento;
	}
	public void setNascimento(Date nascimento) {
		this.nascimento = nascimento;
	}
	public Sexo getSexo() {
		return Sexo.toEnum(sexo);
	}
	public void setSexo(Sexo sexo) {
		this.sexo = sexo.getCod();
	}
	public TipoSanguinio getTipoSanguinio() {
		return TipoSanguinio.toEnum(tipoSanguinio);
	}
	public void setTipoSanguinio(TipoSanguinio tipoSanguinio) {
		this.tipoSanguinio = tipoSanguinio.getCod();
	}
	public Estado getEstadoMoradia() {
		return Estado.toEnum(estadoMoradia);
	}
	public void setEstadoMoradia(Estado estadoMoradia) {
		this.estadoMoradia = estadoMoradia.getCod();
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	public List<Medicamento> getMedicamentos() {
		return medicamentos;
	}
	public void setMedicamentos(List<Medicamento> medicamentos) {
		this.medicamentos = medicamentos;
	}
	public List<Doenca> getDoencas() {
		return doencas;
	}
	public void setDoencas(List<Doenca> doencas) {
		this.doencas = doencas;
	}
	public List<Alergia> getAlergias() {
		return alergias;
	}
	public void setAlergias(List<Alergia> alergias) {
		this.alergias = alergias;
	}
	public List<ContatoDeEmergencia> getContatosDeEmergencias() {
		return contatosDeEmergencias;
	}
	public void setContatosDeEmergencias(List<ContatoDeEmergencia> contatosDeEmergencias) {
		this.contatosDeEmergencias = contatosDeEmergencias;
	}
	public Set<Integer> getPerfis() {
		return perfis;
	}
	public void setPerfis(Set<Integer> perfis) {
		this.perfis = perfis;
	}
	
	
	
}
