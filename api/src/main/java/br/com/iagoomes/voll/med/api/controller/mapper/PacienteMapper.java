package br.com.iagoomes.voll.med.api.controller.mapper;

import br.com.iagoomes.voll.med.api.domain.paciente.PacienteResponse;
import br.com.iagoomes.voll.med.api.domain.paciente.Paciente;
import br.com.iagoomes.voll.med.api.domain.paciente.PacienteDetalhamentoResponse;
import br.com.iagoomes.voll.med.api.domain.paciente.PacienteRequestPost;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PacienteMapper {
    Paciente pacienteRequestPostToPaciente(PacienteRequestPost pacienteRequestPost);
    PacienteDetalhamentoResponse pacienteToPacienteDetalhamentoResponse(Paciente paciente);
    PacienteResponse pacienteToPacienteResponse(Paciente paciente);

}
