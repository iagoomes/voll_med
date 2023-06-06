package br.com.iagoomes.voll.med.api.medico;

import br.com.iagoomes.voll.med.api.endereco.Endereco;

public record MedicoDetalhamentoResponse(Long id, String nome,
                                         String email,
                                         String telefone,
                                         String crm,
                                         Boolean ativo,
                                         Especialidade especialidade,
                                         Endereco endereco
) {
}
