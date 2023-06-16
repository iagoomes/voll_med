package br.com.iagoomes.voll.med.api.controller;


import br.com.iagoomes.voll.med.api.controller.mapper.MedicoMapper;
import br.com.iagoomes.voll.med.api.domain.medico.*;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("medicos")
public class MedicoController {
    private final MedicoRepository repository;
    private final MedicoMapper mapper;

    public MedicoController(MedicoRepository repository, MedicoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<MedicoDetalhamentoResponse> cadastrar(@RequestBody @Valid MedicoRequestPost medicoRequestPost, UriComponentsBuilder uriBuilder) {
        Medico medico = mapper.MedicoRequestPostToMedico(medicoRequestPost);
        repository.save(medico);
        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
        MedicoDetalhamentoResponse medicoDetalhamentoResponse = mapper.medicoToMedicoDetalhamentoResponse(medico);
        return ResponseEntity.created(uri).body(medicoDetalhamentoResponse);

    }

    @GetMapping
    public ResponseEntity<Page<MedicoResponse>> listar(Pageable paginacao) {
        Page<MedicoResponse> medicosPage = repository.findAllByAtivoTrue(paginacao).map(mapper::medicoToMedicoResponse);
        return ResponseEntity.ok(medicosPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicoDetalhamentoResponse> detalhar(@PathVariable Long id) {
        Medico medico = repository.getReferenceById(id);
        MedicoDetalhamentoResponse medicoDetalhamentoResponse = mapper.medicoToMedicoDetalhamentoResponse(medico);
        return ResponseEntity.ok(medicoDetalhamentoResponse);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<MedicoDetalhamentoResponse> atualizar(@RequestBody @Valid MedicoRequestPut medicoRequestPut) {
        Medico medico = repository.getReferenceById(medicoRequestPut.id());
        medico.atualizarInformacoes(medicoRequestPut);
        MedicoDetalhamentoResponse medicoDetalhamentoResponse = mapper.medicoToMedicoDetalhamentoResponse(medico);
        return ResponseEntity.ok(medicoDetalhamentoResponse);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
        Medico medico = repository.getReferenceById(id);
        medico.desativar();
        return ResponseEntity.noContent().build();
    }
}
