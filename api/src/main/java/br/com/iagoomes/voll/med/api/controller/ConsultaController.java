package br.com.iagoomes.voll.med.api.controller;

import br.com.iagoomes.voll.med.api.domain.consulta.AgendaDeConsultas;
import br.com.iagoomes.voll.med.api.domain.consulta.DadosAgendamentoConsulta;
import br.com.iagoomes.voll.med.api.domain.consulta.DadosDetalhamentoConsulta;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("consultas")
public class ConsultaController {
    private final AgendaDeConsultas agenda;

    public ConsultaController(AgendaDeConsultas agenda) {
        this.agenda = agenda;
    }

    @PostMapping
    @Transactional
    public ResponseEntity agendar(@RequestBody @Valid DadosAgendamentoConsulta dados){
        DadosDetalhamentoConsulta detalhamentoConsulta = agenda.agendar(dados);
        return ResponseEntity.ok(detalhamentoConsulta);
    }
}
