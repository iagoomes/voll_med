package br.com.iagoomes.voll.med.api.controller.mapper;

import br.com.iagoomes.voll.med.api.domain.medico.Medico;
import br.com.iagoomes.voll.med.api.domain.medico.MedicoDetalhamentoResponse;
import br.com.iagoomes.voll.med.api.domain.medico.MedicoRequestPost;
import br.com.iagoomes.voll.med.api.domain.medico.MedicoResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MedicoMapper {
    Medico MedicoRequestPostToMedico(MedicoRequestPost medicoRequestPost);
    MedicoResponse medicoToMedicoResponse(Medico medico);
    MedicoDetalhamentoResponse medicoToMedicoDetalhamentoResponse(Medico medico);
}
