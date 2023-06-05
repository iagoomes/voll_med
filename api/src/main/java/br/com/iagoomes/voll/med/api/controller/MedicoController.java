package br.com.iagoomes.voll.med.api.controller;


import br.com.iagoomes.voll.med.api.controller.mapper.MedicoMapper;
import br.com.iagoomes.voll.med.api.medico.Medico;
import br.com.iagoomes.voll.med.api.medico.MedicoRepository;
import br.com.iagoomes.voll.med.api.medico.MedicoRequest;
import br.com.iagoomes.voll.med.api.medico.MedicoResponse;
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
    public Long cadastrar(@RequestBody @Valid MedicoRequest medicoRequest) {
        Medico medico = mapper.MedicoRequestToMedico(medicoRequest);
        repository.save(medico);
        return medico.getId();
    }
    @GetMapping
    public Page<MedicoResponse> listar(Pageable paginacao) {
        return repository.findAll(paginacao).map(mapper::medicoToMedicoResponse);
    }
}
