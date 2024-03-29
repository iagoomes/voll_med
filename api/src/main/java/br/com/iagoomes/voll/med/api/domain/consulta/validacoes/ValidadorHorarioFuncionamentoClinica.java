package br.com.iagoomes.voll.med.api.domain.consulta.validacoes;

import br.com.iagoomes.voll.med.api.domain.consulta.DadosAgendamentoConsulta;
import br.com.iagoomes.voll.med.api.infra.exception.ValidacaoException;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
@Component
public class ValidadorHorarioFuncionamentoClinica implements ValidadorAgendamentoDeConsulta{
    public void validar(DadosAgendamentoConsulta dados){
        var dataConsulta = dados.data();
        var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var antesDaAberturaDaClinica = dataConsulta.getHour() < 7;
        var depoisDaAberturaDaClinica = dataConsulta.getHour() > 18;

        if (domingo || antesDaAberturaDaClinica || depoisDaAberturaDaClinica){
            throw new ValidacaoException("Consulta fora do horário de funcionamento da clínica");
        }
    }
}
