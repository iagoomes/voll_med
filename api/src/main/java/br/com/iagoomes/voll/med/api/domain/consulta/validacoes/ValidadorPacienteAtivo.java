package br.com.iagoomes.voll.med.api.domain.consulta.validacoes;

import br.com.iagoomes.voll.med.api.domain.consulta.DadosAgendamentoConsulta;
import br.com.iagoomes.voll.med.api.domain.paciente.PacienteRepository;
import br.com.iagoomes.voll.med.api.infra.exception.ValidacaoException;

public class ValidadorPacienteAtivo {
    private PacienteRepository pacienteRepository;

    public void validar(DadosAgendamentoConsulta dados) {
        var pacienteEstaAtivo = pacienteRepository.findAtivoById(dados.idPaciente());
        if (!pacienteEstaAtivo) throw new ValidacaoException("Consulta não pode ser agendada com paciente excluído");
    }
}
