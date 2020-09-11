package com.myhero.enuns;

public enum Estado {

    ACRE(1 , "AC" , "Acre"),
    ALAGOAS(2 , "AL" , "Alagoas"),
    AMAPA(3 , "AP" , "Amapá"),
    AMAZONAS(4 , "AM" , "Amazonas"),
    BAHIA(5 , "BA" , "Bahia"),
    CEARA(6 , "CE" , "Ceará"),
    ESPIRITO_SANTOS(7 , "ES" , "Espírito Santo"),
    GOIAS(8 , "GO" , "Goiás"),
    MARANHAO(9 , "MA" , "Maranhão"),
    MATO_GROSSO(10 , "MT" , "Mato Grosso"),
    MATO_GROSSO_DO_SUL(11 , "MS" , "Mato Grosso do Sul"),
    MINAS_GERAIS(12 , "MG" , "Minas Gerais"),
    PARA(13 , "PA" , "Pará"),
    PARAIBA(14 , "PB" , "Paraíba"),
    PARANA(15 , "PR" , "Paraná"),
    PERNAMBUCO(16 , "PE" , "Pernambuco"),
    PIAUI(17 , "PI" , "Piauí"),
    RIO_DE_JANEIRO(18 , "RJ" , "Rio de Janeiro"),
    RIO_GRANDE_DO_NORTE(19 , "RN" , "Rio Grande do Norte"),
    RIO_GRANDE_DO_SUL(20 , "RS" , "Rio Grande do Sul"),
    RONDÔNIA(21 , "RO" , "Rondônia"),
    RORAIMA(22 , "RR" , "Roraima"),
    SANTA_CATARINA(23 , "SC" , "Santa Catarina"),
    SAO_PAULO(24 , "SP" , "São Paulo"),
    SERGIPE(25 , "SE" , "Sergipe"),
    TOCANTINS(26 , "TO" , "Tocantins"),
    DISTRITO_FEDERAL(27 , "DF" , "Distrito Federal");
    

	private int cod;
	private String sigla;
	private String nomeEstado;

	private Estado(int cod,String sigla, String nomeEstado) {
		this.cod = cod;
		this.sigla =sigla;
		this.nomeEstado = nomeEstado;
		
	}

	public int getCod() {
		return cod;
	}
	public String getSigla() {
		return this.sigla;
	}

	public String getNomeEstado() {
		return nomeEstado;
	}
	public static Estado toEnum(Integer cod) {

		if (cod == null) {
			return null;
		}
		for (Estado x : Estado.values()) {

			if (cod.equals(x.getCod())) {
				return x;
			}
		}
		throw new IllegalAccessError("ID ivalido" + cod);
	}
}
