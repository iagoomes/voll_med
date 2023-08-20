package br.com.iagoomes.voll.med.api.domain.consulta;

import br.com.iagoomes.voll.med.api.domain.medico.Medico;
import br.com.iagoomes.voll.med.api.domain.paciente.Paciente;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Table(name = "consultas")
@Entity(name = "Consulta")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Consulta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medico_id")
    private Medico medico;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    private LocalDateTime data;
}
