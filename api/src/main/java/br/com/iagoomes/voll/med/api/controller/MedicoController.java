package br.com.iagoomes.voll.med.api.controller;


import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("medicos")
public class MedicoController {

    @PostMapping
    public MedicoRequest cadastrar(@RequestBody MedicoRequest medicoRequest) {
        System.out.println(medicoRequest);
        return medicoRequest;
    }
}
