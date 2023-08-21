package br.com.iagoomes.voll.med.api.domain.consulta.validacoes;

import br.com.iagoomes.voll.med.api.domain.consulta.DadosAgendamentoConsulta;
import br.com.iagoomes.voll.med.api.domain.medico.MedicoRepository;
import br.com.iagoomes.voll.med.api.infra.exception.ValidacaoException;

public class ValidadorMedicoAtivo {
    private MedicoRepository medicoRepository;

    public void validar(DadosAgendamentoConsulta dados) {
        if (dados.idMedico() == null) return;
        var medicoEstaAtivo = medicoRepository.findAtivoByid(dados.idMedico());
        if (!medicoEstaAtivo) throw new ValidacaoException("Consulta não pode ser agendada com médico excluído");
    }
}
