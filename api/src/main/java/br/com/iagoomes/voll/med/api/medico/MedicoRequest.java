package br.com.iagoomes.voll.med.api.medico;

import br.com.iagoomes.voll.med.api.endereco.EnderecoRequest;

public record MedicoRequest(String nome, String email, String crm, Especialidade especialidade, EnderecoRequest enderecoRequest) {
}
