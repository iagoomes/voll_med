package br.com.iagoomes.voll.med.api.controller;


import br.com.iagoomes.voll.med.api.controller.mapper.MedicoMapper;
import br.com.iagoomes.voll.med.api.medico.Medico;
import br.com.iagoomes.voll.med.api.medico.MedicoRepository;
import br.com.iagoomes.voll.med.api.medico.MedicoRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
