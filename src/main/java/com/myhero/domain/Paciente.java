package com.myhero.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.validator.constraints.br.CPF;

import com.myhero.enuns.Estado;
import com.myhero.enuns.Perfil;
import com.myhero.enuns.Sexo;
import com.myhero.enuns.TipoSanguinio;

@Entity
public class Paciente implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Date cadastro;
	private String codigoGeradoPeloSistema;
	
	@NotEmpty(message = "Imagem é Obrigatorio")
	private String imageAvatarUrl;
	
	@CPF(message = "CPF Inválido")
	@Column(unique = true)
	private String cpf;
	
	@NotEmpty(message = "Nome é obrigatório")
	private String nome;

	@NotEmpty(message = "Email é obrigatório")
	//@Column(unique = true)
	private String email;
	
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
	
	@ManyToMany
	@JoinTable(name = "PACIENTE_MEDICAMENTO",
    joinColumns = @JoinColumn(name = "paciente_id"),
    inverseJoinColumns = @JoinColumn(name = "medicamento_id"))
	private List<Medicamento> medicamentos = new ArrayList<>();
	
	@ManyToMany
	private List<Doenca> doencas = new ArrayList<>();
	
	@ManyToMany
	private List<Alergia> alergias = new ArrayList<>();
	

	
	
	@OneToMany
	@Cascade(CascadeType.ALL)
	private List<ContatoDeEmergencia> contatosDeEmergencias = new ArrayList<>();
	
	@ElementCollection(fetch=FetchType.EAGER)
	@CollectionTable(name = "PERFIS")
	private Set<Integer> perfis = new HashSet<>();
	

	// ESQUECI MINHA SENHA
	private Boolean codigoDeSegurancaAtivado;  
	private String codigoDeSeguranca;
	
	private String senhaGeradaPorEmail;
	
	public Paciente() {
		addPerfil(Perfil.PACIENTE);
	}

	public Paciente(Long id , String cpf, String nome, String email, String imageAvatarUrl,
			Boolean doadorDeOrgao, String telefone, Double peso, Double altura, Date nascimento, Sexo sexo, TipoSanguinio  tipoSanguinio, Estado estadoMoradia,
			String observacao, String senha) {
		super();
		this.id = id;
		//this.codigoGeradoPeloSistema = codigoGeradoPeloSistema;
		this.cpf = cpf;
		this.nome = nome;
		this.email = email;
		this.doadorDeOrgao = doadorDeOrgao;
		this.telefone = telefone;
		this.peso = peso;
		this.altura = altura;
		this.nascimento = nascimento;
		this.sexo = sexo.getCod();
		this.tipoSanguinio = tipoSanguinio.getCod();
		this.estadoMoradia = estadoMoradia.getCod();
		this.observacao = observacao;
		this.senha = senha;
		this.imageAvatarUrl = imageAvatarUrl;
		addPerfil(Perfil.PACIENTE);
	}

	public Long getId() {
		return id;
	}

	public Date getCadastro() {
		return cadastro;
	}

	public void setCadastro(Date cadastro) {
		this.cadastro = cadastro;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigoGeradoPeloSistema() {
		return codigoGeradoPeloSistema;
	}

	public void setCodigoGeradoPeloSistema(String codigoGeradoPeloSistema) {
		this.codigoGeradoPeloSistema = codigoGeradoPeloSistema;
	}

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
	
	public List<ContatoDeEmergencia> getContatosDeEmergencias() {
		return contatosDeEmergencias;
	}

	public void setContatosDeEmergencias(List<ContatoDeEmergencia> contatosDeEmergencias) {
		this.contatosDeEmergencias = contatosDeEmergencias;
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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}


	public Boolean getCodigoDeSegurancaAtivado() {
		return codigoDeSegurancaAtivado;
	}

	public void setCodigoDeSegurancaAtivado(Boolean codigoDeSegurancaAtivado) {
		this.codigoDeSegurancaAtivado = codigoDeSegurancaAtivado;
	}

	public String getCodigoDeSeguranca() {
		return codigoDeSeguranca;
	}

	public void setCodigoDeSeguranca(String codigoDeSeguranca) {
		this.codigoDeSeguranca = codigoDeSeguranca;
	}
	
	public String getSenhaGeradaPorEmail() {
		return senhaGeradaPorEmail;
	}

	public void setSenhaGeradaPorEmail(String senhaGeradaPorEmail) {
		this.senhaGeradaPorEmail = senhaGeradaPorEmail;
	}


	public Set<Perfil> getPerfis() {
		return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
	}
	
	public void addPerfil(Perfil perfil) {
		perfis.add(perfil.getCod());
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Paciente other = (Paciente) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
