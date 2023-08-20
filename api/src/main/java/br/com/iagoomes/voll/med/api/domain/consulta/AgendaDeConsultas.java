package br.com.iagoomes.voll.med.api.domain.consulta;

import br.com.iagoomes.voll.med.api.domain.medico.MedicoRepository;
import br.com.iagoomes.voll.med.api.domain.paciente.PacienteRepository;
import org.springframework.stereotype.Service;

@Service
public class AgendaDeConsultas {
    private final ConsultaRepository consultaRepository;
    private final MedicoRepository medicoRepository;
    private final PacienteRepository pacienteRepository;

    public AgendaDeConsultas(ConsultaRepository consultaRepository, MedicoRepository medicoRepository, PacienteRepository pacienteRepository) {
        this.consultaRepository = consultaRepository;
        this.medicoRepository = medicoRepository;
        this.pacienteRepository = pacienteRepository;
    }

    public void agendar(DadosAgendamentoConsulta dados){

        var medico = medicoRepository.findById(dados.idMedico()).get();
        var paciente = pacienteRepository.findById(dados.idPaciente()).get();
        var consulta = new Consulta(null, medico, paciente,dados.data());
        consultaRepository.save(consulta);
    }
}
