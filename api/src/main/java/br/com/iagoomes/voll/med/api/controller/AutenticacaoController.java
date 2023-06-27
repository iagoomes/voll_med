package br.com.iagoomes.voll.med.api.controller;

import br.com.iagoomes.voll.med.api.controller.mapper.TokenMapper;
import br.com.iagoomes.voll.med.api.domain.usuario.AutenticacaoRequest;
import br.com.iagoomes.voll.med.api.domain.usuario.Usuario;
import br.com.iagoomes.voll.med.api.infra.security.TokenJwtResponse;
import br.com.iagoomes.voll.med.api.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {
    private final AuthenticationManager manager;
    private final TokenService tokenService;
    private final TokenMapper mapper;

    public AutenticacaoController(AuthenticationManager manager, TokenService tokenService,TokenMapper mapper) {
        this.manager = manager;
        this.tokenService = tokenService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid AutenticacaoRequest autenticacaoRequest) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(autenticacaoRequest.login(), autenticacaoRequest.senha());
        Authentication authenticate = manager.authenticate(authenticationToken);
        String tokenJWT = tokenService.gerartoken((Usuario) authenticate.getPrincipal());

        TokenJwtResponse tokenJwtResponse = mapper.StringTokenToTokenJwtResponse(tokenJWT);
        return ResponseEntity.ok(tokenJwtResponse);
    }
}
