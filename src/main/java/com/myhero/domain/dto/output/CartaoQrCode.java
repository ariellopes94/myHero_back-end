package com.myhero.domain.dto.output;

import com.myhero.enuns.Sexo;

public class CartaoQrCode {

	private String codigoGeradoPeloSistema;
	private String nome;
	private Integer sexo;
	
	public String getCodigoGeradoPeloSistema() {
		return codigoGeradoPeloSistema;
	}
	public void setCodigoGeradoPeloSistema(String codigoGeradoPeloSistema) {
		this.codigoGeradoPeloSistema = codigoGeradoPeloSistema;
	}
	public String getNome() {
		return abreviarNome(nome);
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSexo() {
		return Sexo.toEnum(sexo).getDescricao();
	}
	public void setSexo(Integer sexo) {
		this.sexo = sexo;
	}
	
	public String abreviarNome(String nomeInteiro) {

		 // String  = ("Ariel Lopes de souza");
		   nomeInteiro = nomeInteiro.replace(' ',';');
		   String nomePedacos[] = nomeInteiro.split(";");
		   int contador =0;
		   String saida="";
		   do {
		         if  (contador == 0) {
		                saida = saida + nomePedacos[contador];
		          }
		          else {
		                saida = saida + " " + nomePedacos[contador].charAt(0) + ".";  
		            System.out.println(saida);
		           }

		           contador++;
		      } while(contador != nomePedacos.length);
		   
		   return saida;
	}
	
}
