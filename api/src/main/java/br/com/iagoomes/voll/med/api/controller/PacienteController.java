package br.com.iagoomes.voll.med.api.controller;

import br.com.iagoomes.voll.med.api.controller.mapper.PacienteMapper;
import br.com.iagoomes.voll.med.api.medico.PacienteResponse;
import br.com.iagoomes.voll.med.api.paciente.Paciente;
import br.com.iagoomes.voll.med.api.paciente.PacienteDetalhamentoResponse;
import br.com.iagoomes.voll.med.api.paciente.PacienteRepository;
import br.com.iagoomes.voll.med.api.paciente.PacienteRequestPost;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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

    @GetMapping
    public ResponseEntity<Page<PacienteResponse>> listar(@PageableDefault(size = 10, sort = {"nome"}, direction = Sort.Direction.DESC) Pageable paginacao) {
        Page<PacienteResponse> pacienteResponsePage = repository.findAll(paginacao).map(mapper::pacienteToPacienteResponse);
        return ResponseEntity.ok(pacienteResponsePage);
    }
}
