package com.myhero.domain.dto.output;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.myhero.domain.Alergia;
import com.myhero.domain.ContatoDeEmergencia;
import com.myhero.domain.Doenca;
import com.myhero.domain.Medicamento;
import com.myhero.enuns.Estado;
import com.myhero.enuns.Perfil;
import com.myhero.enuns.Sexo;
import com.myhero.enuns.TipoSanguinio;

public class FichaPacienteDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private String nome;
	private Integer idade;
	private Boolean doadorDeOrgao;
	private String telefone;
	private Double peso;
	private Double altura;
	private Date nascimento;
	private Integer sexo;
	private Integer tipoSanguinio;
	private Integer estadoMoradia;
	private String observacao; 
	
	private List<Medicamento> medicamentos = new ArrayList<>();
	private List<Doenca> doencas = new ArrayList<>();
	private List<Alergia> alergias = new ArrayList<>();
	private List<ContatoDeEmergencia> contatosDeEmergencias = new ArrayList<>();
	private Set<Perfil> perfis = new HashSet<>();
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Integer getIdade() {
		return calcularIdadePelaDataNascimento(getNascimento());
	}
	public void setIdade(Integer idade) {
		this.idade = idade;
	}
	public String getDoadorDeOrgao() {
		return (doadorDeOrgao == true) ? "Sim" : "Não";
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
	public String getNascimento() {
		return new SimpleDateFormat("dd/MM/yyyy").format(nascimento);
	}
	public void setNascimento(Date nascimento) {
		this.nascimento = nascimento;
	}
	public String getSexo() {
		return Sexo.toEnum(sexo).getDescricao(); //GET TIPO SANGUINIO
	}
	public void setSexo(Integer sexo) {
		this.sexo = sexo;
	}
	public String getTipoSanguinio() {
		return TipoSanguinio.toEnum(tipoSanguinio).getDescricao();
	}
	public void setTipoSanguinio(Integer tipoSanguinio) {
		this.tipoSanguinio = tipoSanguinio;
	}
	public String getEstadoMoradia() {
		return Estado.toEnum(estadoMoradia).getNomeEstado();
	}
	public void setEstadoMoradia(Integer estadoMoradia) {
		this.estadoMoradia = estadoMoradia;
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
	public Set<Perfil> getPerfis() {
		return perfis;
	}
	public void setPerfis(Set<Perfil> perfis) {
		this.perfis = perfis;
	}
	
	public Integer calcularIdadePelaDataNascimento(String dt_nasc) {
		
		 //String dt_nasc;
		  // Data de hoje.
		  GregorianCalendar hoje = new GregorianCalendar();
		  int diah = hoje.get(Calendar.DAY_OF_MONTH);
		  int mesh = hoje.get(Calendar.MONTH) + 1;
		  int anoh = hoje.get(Calendar.YEAR);

		  // Data do nascimento.
		  int dian = Integer.valueOf(dt_nasc.substring(0,2));
		  int mesn = Integer.valueOf(dt_nasc.substring(3,5));
		  int anon = Integer.valueOf(dt_nasc.substring(6,10));

		  // Idade.
		  int idade;

		  if (mesn < mesh || (mesn == mesh && dian <= diah))
		        idade = anoh - anon;
		   else
		    idade = (anoh - anon)-1;

		   return idade;
		}

	
}