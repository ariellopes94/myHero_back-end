package com.myhero.services.gerado_pelo_sistema;

import java.util.Random;
import java.util.UUID;
import org.springframework.stereotype.Component;



@Component
public class CodigoUnico {
	public static String idGeradoParaPaciente() {
	   String idUnico =	idUnico();
	   String letraAliatorio = letraAliatorio();
	   
	   idUnico = idUnico.replaceAll("-", letraAliatorio+" ");
	   
	   return idUnico.replaceAll(" ", "My-HERO" + "-");
	}
	
	 private static  String idUnico(){
		    String uniqueID = UUID.randomUUID().toString();
		    System.out.print(uniqueID);
		    return uniqueID;
		  }
	 
	 
		private static String  letraAliatorio() { 
			   Random r = new Random();

			    String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
			    String conjutoDeLetras = "";
			    for (int i = 0; i < 2; i++) {
			         
			        char letraSeleciona = alphabet.charAt(r.nextInt(alphabet.length()));
			        String convertendoCharEmString =  Character.toString(letraSeleciona);
			        conjutoDeLetras +=  convertendoCharEmString;

			    }
				return conjutoDeLetras;
			}
}
