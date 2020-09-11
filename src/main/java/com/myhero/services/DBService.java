package com.myhero.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.myhero.domain.Alergia;
import com.myhero.domain.ContatoDeEmergencia;
import com.myhero.domain.Doenca;
import com.myhero.domain.Medicamento;
import com.myhero.domain.Paciente;
import com.myhero.enuns.Estado;
import com.myhero.enuns.Paretesco;
import com.myhero.enuns.Perfil;
import com.myhero.enuns.Sexo;
import com.myhero.enuns.TipoSanguinio;
import com.myhero.repositories.AlergiaRepository;
import com.myhero.repositories.ContatoDeEmergenciaRepository;
import com.myhero.repositories.DoencaRepository;
import com.myhero.repositories.MedicamentoRepository;
import com.myhero.repositories.PacienteRepository;

@Service
public class DBService {

	@Autowired
	private PacienteRepository pacienteRepository;
	@Autowired 
	private MedicamentoRepository medicamentoRepository;
	@Autowired
	private ContatoDeEmergenciaRepository contatoDeEmergenciaRepository;
	@Autowired
	private DoencaRepository doencaRepository;
	@Autowired
	private AlergiaRepository alergiaRepository;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public void instantiateTestDatabase() throws ParseException {
		

		//PACIENTE
	    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy"); 
		
		Paciente paciente1 = new Paciente(null , "82526067090", "Simone Lima Lopes", "simone_2019@gmail.com", false, "(67) 99548-4298", 72.5, 1.69, 
			                          formato.parse("09/11/1990"), Sexo.FEMININO, TipoSanguinio.A_POSITIVO, Estado.SAO_PAULO , "Plano de saude Unimed,numero: 4557-4458",passwordEncoder.encode("ariel123"));
		paciente1.setCadastro(new Date());
		paciente1.setCodigoGeradoPeloSistema("XTR300");
		
		Paciente paciente2 = new Paciente(null, "73038016004", "Ariel Lopes De Souza", "ariel-edit@hotmail.com", true, "(67) 8811-0088", 85.5, 1.79, 
                                          formato.parse("09/10/1994"),  Sexo.MASCULINO ,TipoSanguinio.O_NEGATIVO, Estado.MATO_GROSSO_DO_SUL, "Nenhuma",passwordEncoder.encode("teste123"));
		paciente2.setCodigoGeradoPeloSistema("XTR700");
		paciente2.setCadastro(new Date());
		
		//paciente2.setPerfil(Perfil.ADMIN); //Perfil de ADMIN
		paciente2.addPerfil(Perfil.ADMIN);
		//CONTATO DE EMERGENCIA
		ContatoDeEmergencia contato1 = new ContatoDeEmergencia(null, "Ana Maria Beatriz", Paretesco.MAE, "(67) 9954-8992");
				
		ContatoDeEmergencia contato2 = new ContatoDeEmergencia(null, "Camila Lopes", Paretesco.IRMA, "(67) 9988-7455");
		ContatoDeEmergencia contato3 = new ContatoDeEmergencia(null, "Gustavo", Paretesco.FILHO, "(67) 9988-7455");
		ContatoDeEmergencia contato4 = new ContatoDeEmergencia(null, "Milena Aquirno", Paretesco.PRIMA, "(67) 3351-7455");
		ContatoDeEmergencia contato5 = new ContatoDeEmergencia(null, "Patrik Lopes", Paretesco.PRIMO, "(67) 9955-5599");
				
		paciente1.getContatosDeEmergencias().add(contato1);
		paciente2.getContatosDeEmergencias().addAll(Arrays.asList(contato2, contato3, contato4, contato5));
				
		//contatoDeEmergenciaRepository.saveAll(Arrays.asList(contato1, contato2, contato3, contato4, contato5));
		
		
		//MEDICAMENTO
		Medicamento medicamento1 =  new Medicamento(null, "IBUPROFENO");
		Medicamento medicamento2 = new Medicamento(null, "RIVASTIGMINA");
		
		Medicamento medicamento3 =  new Medicamento(null, "DORFLEX");
		Medicamento medicamento4 = new Medicamento(null, "TORSILAX");
		
		paciente1.getMedicamentos().add(medicamento1);
		paciente2.getMedicamentos().addAll(Arrays.asList(medicamento2, medicamento3, medicamento4));
		
		//Doença
		Doenca doenca1 = new Doenca(1, "Dipriona");
		Doenca doenca2 = new Doenca(2, "Alzheimer");
		
		paciente1.getDoencas().add(doenca1);
		paciente2.getDoencas().add(doenca2);
		
		//Alergia
		Alergia alergia1 = new Alergia(1, "Frutos do Mar");
		
		Alergia alergia2 = new Alergia(2, "Amendoim e nozes");
		Alergia alergia3 = new Alergia(3, "Poeira");
		Alergia alergia4 = new Alergia(4, "Animais de estimação");
		
		paciente1.getAlergias().add(alergia1);
		paciente2.getAlergias().addAll(Arrays.asList(alergia2, alergia3, alergia4));
		
		medicamentoRepository.saveAll(Arrays.asList(medicamento1, medicamento2, medicamento3, medicamento4));
		doencaRepository.saveAll(Arrays.asList(doenca1, doenca2));
		alergiaRepository.saveAll(Arrays.asList(alergia1, alergia2, alergia3, alergia4));
		pacienteRepository.saveAll(Arrays.asList(paciente1, paciente2));
		
	}
}
