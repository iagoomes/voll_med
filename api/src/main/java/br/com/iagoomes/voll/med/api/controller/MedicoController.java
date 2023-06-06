package br.com.iagoomes.voll.med.api.controller;


import br.com.iagoomes.voll.med.api.controller.mapper.MedicoMapper;
import br.com.iagoomes.voll.med.api.medico.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("medicos")
public class MedicoController {
    @Autowired
    private MedicoRepository repository;
    @Autowired
    private MedicoMapper mapper;

    @PostMapping
    @Transactional
    public Long cadastrar(@RequestBody @Valid MedicoRequestPost medicoRequestPost) {
        Medico medico = mapper.MedicoRequestToMedico(medicoRequestPost);
        repository.save(medico);
        return medico.getId();
    }

    @GetMapping
    public Page<MedicoResponse> listar(Pageable paginacao) {
        return repository.findAllByAtivoTrue(paginacao).map(mapper::medicoToMedicoResponse);
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid MedicoRequestPut medicoRequestPut) {
        Medico medico = repository.getReferenceById(medicoRequestPut.id());
        medico.atualizarInformacoes(medicoRequestPut);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void excluir(@PathVariable Long id) {
        Medico medico = repository.getReferenceById(id);
        medico.desativar();
    }
}
