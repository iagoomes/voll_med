package br.com.iagoomes.voll.med.api.domain.medico;

import br.com.iagoomes.voll.med.api.domain.consulta.Consulta;
import br.com.iagoomes.voll.med.api.domain.endereco.EnderecoRequest;
import br.com.iagoomes.voll.med.api.domain.paciente.Paciente;
import br.com.iagoomes.voll.med.api.domain.paciente.PacienteRequestPost;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class MedicoRepositoryTest {

    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private TestEntityManager em;


    @Test
    @DisplayName("Deveria devolver null quando unico medico cadastrado nao esta disponivel na data")
    void escolherMedicoAleatorioLivreNaDataCenario1() {
        var proximaSegundaAs10 = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10,0);

        var medico = cadastrarMedico("Amanda Ventura", "amanda.ventura@voll.med", "998883", Especialidade.CARDIOLOGIA);
        var paciente = cadastrarPaciente("Ana de Souza", "Ana_souza@gmail.com", "95671732220");
        cadastrarConsulta(medico, paciente, proximaSegundaAs10);

        Medico medicoLivre = medicoRepository.escolherMedicoAleatorioLivreNaData(Especialidade.CARDIOLOGIA, proximaSegundaAs10);
        assertThat(medicoLivre).isNull();
    }

    @Test
    @DisplayName("Deveria devolver medico estiver disponivel na data")
    void escolherMedicoAleatorioLivreNaDataCenario2() {
        var proximaSegundaAs10 = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10,0);

        var medico = cadastrarMedico("Amanda Ventura", "amanda.ventura@voll.med", "998883", Especialidade.CARDIOLOGIA);

        Medico medicoLivre = medicoRepository.escolherMedicoAleatorioLivreNaData(Especialidade.CARDIOLOGIA, proximaSegundaAs10);
        assertThat(medicoLivre).isEqualTo(medico);
    }

    private void cadastrarConsulta(Medico medico, Paciente paciente, LocalDateTime data) {
        em.persist(new Consulta(null, medico, paciente, data));
    }

    private Medico cadastrarMedico(String nome, String email, String crm, Especialidade especialidade) {
        var medico = new Medico(dadosMedico(nome, email, crm, especialidade));
        em.persist(medico);
        return medico;
    }

    private Paciente cadastrarPaciente(String nome, String email, String cpf) {
        var paciente = new Paciente(dadosPaciente(nome, email, cpf));
        em.persist(paciente);
        return paciente;
    }

    private MedicoRequestPost dadosMedico(String nome, String email, String crm, Especialidade especialidade) {
        return new MedicoRequestPost(nome, email, "61999999999", crm, especialidade, dadosEndereco());

    }

    private PacienteRequestPost dadosPaciente(String nome, String email, String cpf) {
        return new PacienteRequestPost(
                nome,
                email,
                "61999999999",
                cpf,
                dadosEndereco()
        );
    }

    private EnderecoRequest dadosEndereco() {
        return new EnderecoRequest(
                "rua xpto",
                "bairro",
                "00000000",
                "Brasilia",
                "DF",
                "MG",
                "87345654"
        );
    }
}