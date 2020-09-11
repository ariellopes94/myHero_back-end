package com.myhero.enuns;

public enum TipoSanguinio {
	
	A_POSITIVO(1, "A+"), 
	A_NEGATIVO(2, "A-"), 
	B_POSITIVO(3, "B+"), 
	B_NEGATIVO(4, "B-"), 
	AB_POSITIVO(5, "AB+"),
	AB_NEGATIVO(6, "AB-"), 
	O_POSITIVO(7, "O+"), 
	O_NEGATIVO(8, "O-");

	private int cod;
	private String descricao;

	private TipoSanguinio(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}
	public static TipoSanguinio toEnum(Integer cod) {

		if (cod == null) {
			return null;
		}
		for (TipoSanguinio x : TipoSanguinio.values()) {

			if (cod.equals(x.getCod())) {
				return x;
			}
		}
		throw new IllegalAccessError("ID ivalido" + cod);
	}
}