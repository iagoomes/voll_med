package br.com.iagoomes.voll.med.api.medico;

public record MedicoResponse(
        String nome,
        String email,
        String crm,
        Especialidade especialidade) {
}
