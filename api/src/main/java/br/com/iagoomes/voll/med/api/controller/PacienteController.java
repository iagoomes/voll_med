package br.com.iagoomes.voll.med.api.controller;

import br.com.iagoomes.voll.med.api.controller.mapper.PacienteMapper;
import br.com.iagoomes.voll.med.api.paciente.Paciente;
import br.com.iagoomes.voll.med.api.paciente.PacienteDetalhamentoResponse;
import br.com.iagoomes.voll.med.api.paciente.PacienteRepository;
import br.com.iagoomes.voll.med.api.paciente.PacienteRequestPost;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("pacientes")
public class PacienteController {
    @Autowired
    PacienteRepository repository;
    @Autowired
    PacienteMapper mapper;

    @PostMapping
    @Transactional
    public ResponseEntity<PacienteDetalhamentoResponse> listar(@RequestBody @Valid PacienteRequestPost pacienteRequestPost, UriComponentsBuilder uriBuilder) {
        Paciente paciente = mapper.pacienteRequestPostToPaciente(pacienteRequestPost);
        repository.save(paciente);
        URI uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();
        PacienteDetalhamentoResponse pacienteDetalhamentoResponse = mapper.pacienteToPacienteDetalhamentoResponse(paciente);
        return ResponseEntity.created(uri).body(pacienteDetalhamentoResponse);
    }
}
