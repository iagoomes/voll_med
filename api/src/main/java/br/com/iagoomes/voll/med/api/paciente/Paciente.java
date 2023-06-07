package br.com.iagoomes.voll.med.api.paciente;

import br.com.iagoomes.voll.med.api.endereco.Endereco;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.util.Objects;

@Entity(name = "Paciente")
@Table(name = "pacientes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String cpf;
    private Endereco endereco;
    private Boolean ativo = true;

    public void atualizarInformacoes(PacienteRequestPut pacienteRequestPut) {
        if (pacienteRequestPut.nome() != null && !pacienteRequestPut.nome().isEmpty()) {
            this.nome = pacienteRequestPut.nome();
        }
        if (pacienteRequestPut.telefone() != null && !pacienteRequestPut.telefone().isEmpty()) {
            this.telefone = pacienteRequestPut.telefone();
        }
        if (pacienteRequestPut.endereco() != null) {
            this.endereco.atualizarInformacoes(pacienteRequestPut.endereco());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Paciente paciente = (Paciente) o;
        return getId() != null && Objects.equals(getId(), paciente.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }


    public void desabilitar() {
        this.ativo = false;
    }
}
