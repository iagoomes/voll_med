package br.com.iagoomes.voll.med.api.domain.consulta;

import br.com.iagoomes.voll.med.api.domain.consulta.validacoes.ValidadorAgendamentoDeConsulta;
import br.com.iagoomes.voll.med.api.domain.medico.Medico;
import br.com.iagoomes.voll.med.api.infra.exception.ValidacaoException;
import br.com.iagoomes.voll.med.api.domain.medico.MedicoRepository;
import br.com.iagoomes.voll.med.api.domain.paciente.PacienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendaDeConsultas {
    private final ConsultaRepository consultaRepository;
    private final MedicoRepository medicoRepository;
    private final PacienteRepository pacienteRepository;
    private final List<ValidadorAgendamentoDeConsulta> validadores;

    public AgendaDeConsultas(ConsultaRepository consultaRepository, MedicoRepository medicoRepository, PacienteRepository pacienteRepository, List<ValidadorAgendamentoDeConsulta> validadores) {
        this.consultaRepository = consultaRepository;
        this.medicoRepository = medicoRepository;
        this.pacienteRepository = pacienteRepository;
        this.validadores = validadores;
    }

    public DadosDetalhamentoConsulta agendar(DadosAgendamentoConsulta dados) {
        if (dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico()))
            throw new ValidacaoException("Id do médico informado não existe");
        if (!pacienteRepository.existsById(dados.idPaciente()))
            throw new ValidacaoException("Id do paciente informado não existe");

        validadores.forEach(v -> v.validar(dados));

        var medico = escolherMedico(dados);
        var paciente = pacienteRepository.getReferenceById(dados.idPaciente());
        var consulta = new Consulta(null, medico, paciente, dados.data());
        consultaRepository.save(consulta);
        return new DadosDetalhamentoConsulta(consulta);
    }

    //TODO adicionar método de cancelamento de consulta

    private Medico escolherMedico(DadosAgendamentoConsulta dados) {
        if (dados.idMedico() != null)
            return medicoRepository.getReferenceById(dados.idMedico());
        if (dados.especialidade() == null)
            throw new ValidacaoException("Especialidade é obrigatoria quando médico não for escolhido");

        Medico medico = medicoRepository.escolherMedicoAleatorioLivreNaData(dados.especialidade(), dados.data());
        if (medico == null) {
            throw new ValidacaoException("Nenhum médico disponivel nessa data");
        }
        return medico;
    }
}
