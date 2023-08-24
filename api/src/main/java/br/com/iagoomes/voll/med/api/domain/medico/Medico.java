package br.com.iagoomes.voll.med.api.domain.medico;

import br.com.iagoomes.voll.med.api.domain.endereco.Endereco;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.util.Objects;

@Entity(name = "Medico")
@Table(name = "medicos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String crm;
    private Boolean ativo = true;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @Embedded
    private Endereco endereco;

    public Medico(MedicoRequestPost medicoRequestPost) { //utilizar somente em testes caso contrario utilizar o mapper
        this.nome = medicoRequestPost.nome();
        this.email = medicoRequestPost.email();
        this.telefone = medicoRequestPost.telefone();
        this.crm = medicoRequestPost.crm();
        this.especialidade = medicoRequestPost.especialidade();
        this.endereco = new Endereco(medicoRequestPost.endereco());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Medico medico = (Medico) o;
        return getId() != null && Objects.equals(getId(), medico.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public void atualizarInformacoes(MedicoRequestPut medicoRequestPut) {
        if (medicoRequestPut.nome() != null && !medicoRequestPut.nome().isEmpty()) {
            this.nome = medicoRequestPut.nome();
        }
        if (medicoRequestPut.telefone() != null) {
            this.telefone = medicoRequestPut.telefone();
        }
        if (medicoRequestPut.endereco() != null) {
            this.endereco.atualizarInformacoes(medicoRequestPut.endereco());
        }
    }

    public void desativar() {
        this.ativo = false;
    }
}
