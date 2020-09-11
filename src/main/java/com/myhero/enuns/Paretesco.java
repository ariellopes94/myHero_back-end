package com.myhero.enuns;

public enum Paretesco {

	MAE(1, "Mãe"),
	PAI(2, "Pai"),
	CONJUGE(3, "Cônjuge"),
	ESPOSA(4, "Esposa"),
	ESPOSO(5, "Esposo"),
	FILHO(6, "Filho"),
	FILHA(7, "Filha"),
	IRMAO(8, "Irmão"),
	IRMA(9, "irmã"),
	TIA(10, "Tia"),
	TIO(11, "Tio"),
	AVO_H(12, "Avô"),
	AVO_M(13, "Avó"),
	AMIGO(14, "Amigo"),
	AMIGA(15, "Amiga"),
	PRIMO(16, "Primo"),
	PRIMA(17,"Prima"),
	OUTROS(18, "Outros");	
	
	private int cod;
	private String descricao;
	
	
	private Paretesco(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}
	public static Paretesco toEnum(Integer cod) {

		if (cod == null) {
			return null;
		}
		for (Paretesco x : Paretesco.values()) {

			if (cod.equals(x.getCod())) {
				return x;
			}
		}
		throw new IllegalAccessError("ID ivalido" + cod);
	}
}
