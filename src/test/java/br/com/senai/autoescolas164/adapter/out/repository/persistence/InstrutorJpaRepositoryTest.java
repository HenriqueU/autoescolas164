package br.com.senai.autoescolas164.adapter.out.repository.persistence;

import br.com.senai.autoescolas164.adapter.out.repository.entity.AlunoEntity;
import br.com.senai.autoescolas164.adapter.out.repository.entity.InstrucaoEntity;
import br.com.senai.autoescolas164.adapter.out.repository.entity.InstrutorEntity;
import br.com.senai.autoescolas164.shared.vo.endereco.Endereco;
import br.com.senai.autoescolas164.shared.vo.enums.Especialidade;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class InstrutorJpaRepositoryTest {
    @Autowired
    InstrutorJpaRepository repository;

    @Autowired
    TestEntityManager entityManager;

    @Test
    @DisplayName("Expectativa: retornar null quando instrutor não está disponível.")
    void escolherInstrutorAleatorioDisponivelCenario1() {
        LocalDateTime proximaSegundaAs10 = LocalDateTime
                .now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .withHour(10)
                .withMinute(0)
                .withSecond(0)
                .withNano(0);

        //cadastrar aluno
        AlunoEntity aluno = cadastrarAluno(
                "Aluno Teste",
                "alunoteste@email.com.br",
                "(11) 98765-4321",
                "12345678901"
        );

        //cadastrar instrutor
        InstrutorEntity instrutor = cadastrarInstrutor(
                "Instrutor Teste",
                "instrutorteste@email.com.br",
                "(11) 91234-5678",
                "01234567890",
                Especialidade.MOTOS
        );

        //agendar instrução
        agendarInstrucao(
                aluno,
                instrutor,
                proximaSegundaAs10
        );

        //when or act
        InstrutorEntity instrutorDisponivel = repository
                .escolherInstrutorAleatorioDisponivel(
                        Especialidade.MOTOS,
                        proximaSegundaAs10
                );
        //then or assert
        assertThat(instrutorDisponivel).isNull();
    }

    @Test
    @DisplayName("Expectativa: retornar o instrutor quando não está ocupado.")
    void escolherInstrutorAleatorioDisponivelCenario2() {
        LocalDateTime proximaSegundaAs10 = LocalDateTime
                .now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .withHour(10)
                .withMinute(0)
                .withSecond(0)
                .withNano(0);

        //cadastrar instrutor
        InstrutorEntity instrutor = cadastrarInstrutor(
                "Instrutor Teste",
                "instrutorteste@email.com.br",
                "(11) 91234-5678",
                "01234567890",
                Especialidade.MOTOS
        );

        //when or act
        InstrutorEntity instrutorDisponivel = repository
                .escolherInstrutorAleatorioDisponivel(
                        Especialidade.MOTOS,
                        proximaSegundaAs10
                );
        //then or assert
        assertThat(instrutorDisponivel).isEqualTo(instrutor);
    }

    private Endereco dadosEndereco() {
        return new Endereco(
                "Rua Teste",
                "000",
                "Casa dos Fundos",
                "Vila Teste",
                "TestCity",
                "AB",
                "00000-000"
        );
    }

    private AlunoEntity cadastrarAluno(
            String nome,
            String email,
            String telefone,
            String cpf) {
        AlunoEntity aluno = new AlunoEntity(
                null,
                nome,
                email,
                telefone,
                cpf,
                true,
                dadosEndereco()
        );
        entityManager.persist(aluno);
        return aluno;
    }

    private InstrutorEntity cadastrarInstrutor(
            String nome,
            String email,
            String telefone,
            String cnh,
            Especialidade especialidade) {
        InstrutorEntity instrutor = new InstrutorEntity(
                null,
                nome,
                email,
                telefone,
                cnh,
                true,
                especialidade,
                dadosEndereco()
        );
        entityManager.persist(instrutor);
        return instrutor;
    }

    private void agendarInstrucao(
            AlunoEntity aluno,
            InstrutorEntity instrutor,
            LocalDateTime dataHora
        ) {
        InstrucaoEntity instrucao = new InstrucaoEntity(
                null,
                aluno,
                instrutor,
                dataHora,
                true
        );
        entityManager.persist(instrucao);
    }
}