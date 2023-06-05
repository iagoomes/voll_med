package br.com.iagoomes.voll.med.api.controller.mapper;

import br.com.iagoomes.voll.med.api.medico.Medico;
import br.com.iagoomes.voll.med.api.medico.MedicoRequestPost;
import br.com.iagoomes.voll.med.api.medico.MedicoResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MedicoMapper {
    Medico MedicoRequestToMedico(MedicoRequestPost medicoRequestPost);
    MedicoResponse medicoToMedicoResponse(Medico medico);

}
