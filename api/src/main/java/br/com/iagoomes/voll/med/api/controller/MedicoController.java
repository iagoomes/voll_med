package br.com.iagoomes.voll.med.api.controller;


import br.com.iagoomes.voll.med.api.controller.mapper.MedicoMapper;
import br.com.iagoomes.voll.med.api.medico.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("medicos")
public class MedicoController {
    @Autowired
    private MedicoRepository repository;
    @Autowired
    private MedicoMapper mapper;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid MedicoRequestPost medicoRequestPost, UriComponentsBuilder uriBuilder) {
        Medico medico = mapper.MedicoRequestToMedico(medicoRequestPost);
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

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid MedicoRequestPut medicoRequestPut) {
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
