package br.com.iagoomes.voll.med.api.controller.mapper;

import br.com.iagoomes.voll.med.api.infra.security.TokenJwtResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "Spring")
public interface TokenMapper {
    TokenJwtResponse StringTokenToTokenJwtResponse(String token);
}
