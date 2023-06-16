package br.com.iagoomes.voll.med.api.controller;

import br.com.iagoomes.voll.med.api.domain.usuario.AutenticacaoRequest;
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

    public AutenticacaoController(AuthenticationManager manager) {
        this.manager = manager;
    }

    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid AutenticacaoRequest autenticacaoRequest) {
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(autenticacaoRequest.login(), autenticacaoRequest.senha());
        Authentication authenticate = manager.authenticate(token);

        return ResponseEntity.ok().build();
    }
}
