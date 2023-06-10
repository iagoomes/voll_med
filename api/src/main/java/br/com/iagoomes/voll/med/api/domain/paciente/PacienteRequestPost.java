package br.com.iagoomes.voll.med.api.domain.paciente;

import br.com.iagoomes.voll.med.api.domain.endereco.EnderecoRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

public record PacienteRequestPost(
        @NotBlank
        String nome,
        @Email
        String email,
        @NotBlank
        String telefone,
        @CPF
        @NotBlank(message = "CPF deve ser informado!")
        String cpf,
        @NotNull
        @Valid
        EnderecoRequest endereco) {
}
