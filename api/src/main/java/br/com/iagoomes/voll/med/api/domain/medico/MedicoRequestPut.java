package br.com.iagoomes.voll.med.api.domain.medico;

import br.com.iagoomes.voll.med.api.domain.endereco.EnderecoRequest;
import jakarta.validation.constraints.NotNull;

public record MedicoRequestPut(
        @NotNull
        Long id,
        String nome,
        String telefone,
        EnderecoRequest endereco) {
}
