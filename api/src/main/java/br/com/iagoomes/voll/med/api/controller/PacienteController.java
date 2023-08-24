package br.com.iagoomes.voll.med.api.controller;

import br.com.iagoomes.voll.med.api.controller.mapper.PacienteMapper;
import br.com.iagoomes.voll.med.api.domain.paciente.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
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
@SecurityRequirement(name = "bearer-key")
public class PacienteController {
    private final PacienteRepository repository;
    private final PacienteMapper mapper;

    public PacienteController(PacienteRepository repository, PacienteMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

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
    public ResponseEntity<Page<PacienteResponse>> listar(@PageableDefault(sort = {"nome"}, direction = Sort.Direction.DESC) Pageable paginacao) {
        Page<PacienteResponse> pacienteResponsePage = repository.findAllByAtivoTrue(paginacao).map(mapper::pacienteToPacienteResponse);
        return ResponseEntity.ok(pacienteResponsePage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteDetalhamentoResponse> detalhar(@PathVariable Long id) {
        Paciente paciente = repository.getReferenceById(id);
        PacienteDetalhamentoResponse pacienteDetalhamentoResponse = mapper.pacienteToPacienteDetalhamentoResponse(paciente);
        return ResponseEntity.ok(pacienteDetalhamentoResponse);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<PacienteDetalhamentoResponse> atualizar(@RequestBody @Valid PacienteRequestPut pacienteRequestPut) {
        Paciente paciente = repository.getReferenceById(pacienteRequestPut.id());
        paciente.atualizarInformacoes(pacienteRequestPut);
        PacienteDetalhamentoResponse pacienteDetalhamentoResponse = mapper.pacienteToPacienteDetalhamentoResponse(paciente);
        return ResponseEntity.ok(pacienteDetalhamentoResponse);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity desabilitar(@PathVariable Long id) {
        Paciente paciente = repository.getReferenceById(id);
        paciente.desabilitar();
        return ResponseEntity.noContent().build();
    }
}
