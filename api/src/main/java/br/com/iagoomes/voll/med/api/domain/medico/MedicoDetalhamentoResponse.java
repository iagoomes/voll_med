package br.com.iagoomes.voll.med.api.domain.medico;

import br.com.iagoomes.voll.med.api.domain.endereco.Endereco;

public record MedicoDetalhamentoResponse(Long id, String nome,
                                         String email,
                                         String telefone,
                                         String crm,
                                         Boolean ativo,
                                         Especialidade especialidade,
                                         Endereco endereco
) {
}
