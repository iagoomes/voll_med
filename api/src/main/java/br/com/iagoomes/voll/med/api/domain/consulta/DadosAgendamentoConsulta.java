package br.com.iagoomes.voll.med.api.domain.consulta;

import br.com.iagoomes.voll.med.api.domain.medico.Especialidade;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DadosAgendamentoConsulta(Long idMedico,
                                       Especialidade especialidade,
                                       @NotNull Long idPaciente,
                                       @NotNull @Future LocalDateTime data) {

}
