package com.myhero.domain.assembler;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.myhero.domain.Paciente;
import com.myhero.domain.dto.input.PacienteDto;
import com.myhero.domain.dto.output.CartaoQrCode;
import com.myhero.domain.dto.output.FichaPacienteDTO;
import com.myhero.enuns.Perfil;
import com.myhero.services.gerado_pelo_sistema.CodigoUnico;

@Component
public class PacienteModelAssembler {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public FichaPacienteDTO modelPacienteToFichaPacienteDTO (Paciente obj) {
		
		FichaPacienteDTO fichaPaciente = new FichaPacienteDTO();
		fichaPaciente.setNome(obj.getNome());
		fichaPaciente.setDoadorDeOrgao(obj.getDoadorDeOrgao());
		fichaPaciente.setTelefone(obj.getTelefone());
		fichaPaciente.setPeso(obj.getPeso());
		fichaPaciente.setAltura(obj.getAltura());
		fichaPaciente.setNascimento(obj.getNascimento());
		fichaPaciente.setSexo(obj.getSexo().getCod());
		fichaPaciente.setTipoSanguinio(obj.getTipoSanguinio().getCod());
		fichaPaciente.setEstadoMoradia(obj.getEstadoMoradia().getCod());
		fichaPaciente.setObservacao(obj.getObservacao());
		
		
		fichaPaciente.setAlergias(obj.getAlergias());
		fichaPaciente.setContatosDeEmergencias(obj.getContatosDeEmergencias());
		fichaPaciente.setDoencas(obj.getDoencas());
		fichaPaciente.setMedicamentos(obj.getMedicamentos());
		
		return fichaPaciente;
	}
	
	public Paciente modelPacienteDtoToPaciente (PacienteDto pacienteDto) {
		
		Paciente paciente = new Paciente();
		
		paciente.setCadastro(new Date());
		paciente.setCodigoGeradoPeloSistema(CodigoUnico.idGeradoParaPaciente());
		paciente.setCpf(pacienteDto.getCpf());
		paciente.setNome(pacienteDto.getNome());
		paciente.setEmail(pacienteDto.getEmail());
		paciente.setSenha(passwordEncoder.encode(pacienteDto.getSenha()));
		paciente.setDoadorDeOrgao(pacienteDto.getDoadorDeOrgao());
		paciente.setTelefone(pacienteDto.getTelefone());
		paciente.setPeso(pacienteDto.getPeso());
		paciente.setAltura(pacienteDto.getAltura());
		paciente.setNascimento(pacienteDto.getNascimento());
		paciente.setSexo(pacienteDto.getSexo());
		paciente.setTipoSanguinio(pacienteDto.getTipoSanguinio());
		paciente.setEstadoMoradia(pacienteDto.getEstadoMoradia());
		paciente.addPerfil(Perfil.PACIENTE);
		
		paciente.setMedicamentos(pacienteDto.getMedicamentos());
		paciente.setDoencas(pacienteDto.getDoencas());
		paciente.setAlergias(pacienteDto.getAlergias());
		paciente.setContatosDeEmergencias(pacienteDto.getContatosDeEmergencias());
		
		return paciente;
	}
	
	public CartaoQrCode modelPacienteToCartaoQrCode(Paciente obj) {
		
		CartaoQrCode cartao = new CartaoQrCode();
		cartao.setCodigoGeradoPeloSistema(obj.getCodigoGeradoPeloSistema());
		cartao.setNome(obj.getNome());
		cartao.setSexo(obj.getSexo().getCod());
		
		return cartao;
	}
}
