package br.com.senai.autoescolas164.adapter.out.repository.persistence;

import br.com.senai.autoescolas164.adapter.out.repository.entity.AlunoEntity;
import br.com.senai.autoescolas164.adapter.out.repository.entity.InstrutorEntity;
import br.com.senai.autoescolas164.application.core.domain.Aluno;
import br.com.senai.autoescolas164.application.core.domain.Instrucao;
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
    LocalDateTime proxSegundaAs10 = LocalDateTime.now()
            .with(TemporalAdjusters.next(
                    DayOfWeek.MONDAY)
            )
            .withHour(10)
            .withMinute(0)
            .withSecond(0)
            .withNano(0);

    Aluno aluno = cadastrarAluno();

    private Endereco dadosEndereco() {
        return new Endereco(
                "Rua Teste",
                "000",
                "Casa dos Fundos",
                "Vila Teste",
                "Test CIty",
                "AP",
                "00000-000"
        );

    }
    private AlunoEntity cadastrarAluno(String nome, String telefone, String email, String cpf, Endereco endereco) {
        Aluno aluno = new Aluno(nome, cpf, email, telefone, endereco) {

        }
    }

    InstrutorEntity instrutor = cadastrarInstrutor(
            "Instrutor Teste",
            "instrutorteste@gmail.com.br",
            "(11) 91234-5678",
            "0234567890",
            "72",
            Especialidade.MOTOS
    );

    private InstrutorEntity cadastrarInstrutor(
            String nome,
            String email,
            String numeroDeTelefone,
            String cnh,
            String number,
            Especialidade especialidade) {
        InstrutorEntity instrutor = new InstrutorEntity(
                null,
                nome,
                email,
                numeroDeTelefone,
                cnh,
                true,
                Especialidade.MOTOS,
                Endereco endereco
        );
    }

    void agendarInstrucao(
            aluno,
            instrutor,
            proxSegundaAs10
            ) {

    }

    @Autowired
    InstrutorJpaRepository repository;

    @Autowired
    TestEntityManager entityManager;

    @Test
    @DisplayName("Expectativa: retornar null quando instrutor não está disponível.")
    void escolherInstrutorAleatorioDisponivelCenario1() {
        //When
        InstrutorEntity instrutorDisponivel = repository.escolherInstrutorAleatorioDisponivel(Especialidade.MOTOS, proxSegundaAs10);

        //Act
        assertThat(instrutorDisponivel).isNull();
    }

    private void agendarInstrutor(
            AlunoEntity aluno,
            InstrutorEntity instrutor,
            LocalDateTime dataHora
    ) {
        Instrucao instrucao = new Instrucao(null, aluno, instrutor, dataHora, true);
        entityManager.persist(instrucao);
    }
}


