package br.com.iagoomes.voll.med.api.domain.consulta.validacoes;

import br.com.iagoomes.voll.med.api.domain.consulta.ConsultaRepository;
import br.com.iagoomes.voll.med.api.domain.consulta.DadosAgendamentoConsulta;
import br.com.iagoomes.voll.med.api.infra.exception.ValidacaoException;

public class ValidadorMedicoComOutraConsultaNoMesmoHorario {
    private ConsultaRepository consultaRepository;
    public void validar(DadosAgendamentoConsulta dados) {
        var medicoPossuiOutraConsultaNoMesmoHorario = consultaRepository.existsByMedicoIdAndData(dados.idMedico(), dados.data());
        if (medicoPossuiOutraConsultaNoMesmoHorario) throw new ValidacaoException("Médico já possui outra consulta agendada nesse mesmo horário");
    }
}
