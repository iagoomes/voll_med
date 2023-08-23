package br.com.iagoomes.voll.med.api.domain.consulta.validacoes;

import br.com.iagoomes.voll.med.api.domain.consulta.ConsultaRepository;
import br.com.iagoomes.voll.med.api.domain.consulta.DadosAgendamentoConsulta;
import br.com.iagoomes.voll.med.api.infra.exception.ValidacaoException;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacienteSemOutraConsultaNoDia implements ValidadorAgendamentoDeConsulta{
    private final ConsultaRepository consultaRepository;

    public ValidadorPacienteSemOutraConsultaNoDia(ConsultaRepository consultaRepository) {
        this.consultaRepository = consultaRepository;
    }

    public void validar(DadosAgendamentoConsulta dados) {
        var primeiroHorario = dados.data().withHour(7);
        var ultimoHorario = dados.data().withHour(18);
        var pacientePossuiOutraConsultaNoDia = consultaRepository.existsByPacienteIdAndDataBetween(dados.idPaciente(), primeiroHorario, ultimoHorario);
        if (pacientePossuiOutraConsultaNoDia)
            throw new ValidacaoException("Paciente já possui uma consulta agendada nesse dia");
    }
}