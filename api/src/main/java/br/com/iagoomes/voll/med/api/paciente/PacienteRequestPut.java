package br.com.iagoomes.voll.med.api.paciente;

import br.com.iagoomes.voll.med.api.endereco.EnderecoRequest;
import jakarta.validation.constraints.NotNull;

public record PacienteRequestPut(
        @NotNull
        Long id,
        String nome,
        String telefone,
        EnderecoRequest endereco) {
}
