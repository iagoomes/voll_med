package br.com.iagoomes.voll.med.api.domain.paciente;

import br.com.iagoomes.voll.med.api.domain.endereco.Endereco;

public record PacienteDetalhamentoResponse(Long id, String nome, String email, String telefone, String cpf,
                                           Endereco endereco) {
}
