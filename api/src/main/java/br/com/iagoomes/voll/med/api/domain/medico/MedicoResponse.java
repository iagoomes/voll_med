package br.com.iagoomes.voll.med.api.domain.medico;

public record MedicoResponse(
        Long id,
        String nome,
        String email,
        String crm,
        Especialidade especialidade) {
}
