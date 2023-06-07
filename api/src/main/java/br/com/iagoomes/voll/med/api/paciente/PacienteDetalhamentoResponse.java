package br.com.iagoomes.voll.med.api.paciente;

import br.com.iagoomes.voll.med.api.endereco.Endereco;

public record PacienteDetalhamentoResponse(Long id, String nome, String email, String telefone, String cpf,
                                           Endereco endereco) {
}
