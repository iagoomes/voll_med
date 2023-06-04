package br.com.iagoomes.voll.med.api.controller;


import br.com.iagoomes.voll.med.api.controller.mapper.MedicoMapper;
import br.com.iagoomes.voll.med.api.medico.Medico;
import br.com.iagoomes.voll.med.api.medico.MedicoRepository;
import br.com.iagoomes.voll.med.api.medico.MedicoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("medicos")
public class MedicoController {
    @Autowired
    private MedicoRepository repository;

    @Autowired
    private MedicoMapper mapper;

    @PostMapping
    public Long cadastrar(@RequestBody MedicoRequest medicoRequest) {
        Medico medico = mapper.MedicoRequestToMedico(medicoRequest);
        repository.save(medico);
        return medico.getId();
    }
}
