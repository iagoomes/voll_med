package br.com.iagoomes.voll.med.api.controller;

import br.com.iagoomes.voll.med.api.model.Endereco;
import br.com.iagoomes.voll.med.api.model.Especialidade;

public record MedicoRequest(String nome, String email, String crm, Especialidade especialidade, Endereco endereco) {
}
