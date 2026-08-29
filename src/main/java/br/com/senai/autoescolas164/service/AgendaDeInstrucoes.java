package br.com.senai.autoescolas164.service;

import br.com.senai.autoescolas164.domain.agenda.*;
import br.com.senai.autoescolas164.domain.agenda.validacao.ValidadorAgendamento;
import br.com.senai.autoescolas164.domain.aluno.Aluno;
import br.com.senai.autoescolas164.domain.aluno.AlunoNotFoundException;
import br.com.senai.autoescolas164.domain.aluno.AlunoRepository;
import br.com.senai.autoescolas164.domain.instrutor.Instrutor;
import br.com.senai.autoescolas164.domain.instrutor.InstrutorNotFoundException;
import br.com.senai.autoescolas164.domain.instrutor.InstrutorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AgendaDeInstrucoes {
    private final AlunoRepository alunoRepository;
    private final InstrutorRepository instrutorRepository;
    private final InstrucaoRepository repository;
    private final List<ValidadorAgendamento> validadoresAgendamento;

    public DadosDetalhamentoAgendamento agendar(DadosAgendamento dados) {
        if (!alunoRepository.existsById(dados.idAluno())) {
            throw new AlunoNotFoundException("ID do aluno informado não existe!");
        }
        if (dados.idInstrutor() != null && !instrutorRepository.existsById(dados.idInstrutor())) {
            throw new InstrutorNotFoundException("ID do instrutor informado não existe!");
        }

        //Validações
        validadoresAgendamento.forEach(validador -> validador.validar(dados));

        Aluno aluno = alunoRepository.getReferenceById(dados.idAluno());
        Instrutor instrutor = escolherInstrutor(dados);
        if (instrutor == null) {
            throw new ValidacaoException("Não existe instrutor disponível para a dara/hora informada");
        }
        Instrucao instrucao = new Instrucao(null, aluno, instrutor, dados.dataHora(), true);
        Instrucao salva = repository.save(instrucao);
        return new DadosDetalhamentoAgendamento(salva);
    }

    private Instrutor escolherInstrutor(DadosAgendamento dados) {
        if (dados.idInstrutor() != null) {
            instrutorRepository.getReferenceById(dados.idInstrutor());
        }
        if (dados.especialidade() == null) {
            throw new ValidacaoException("Especialidade é campo obrigatório caso o instrutor não seja informado!");
        }
        return instrutorRepository.escolherInstrutorAleatorioDisponivel(
                dados.especialidade(),
                dados.dataHora()
        );
    }

    @Transactional
    public void excluirInstrucao(Long id) {
        Instrucao instrucao = repository.findById(id).orElseThrow(() -> new InstrucaoNotFound("Agendamento não encontrado!"));
        instrucao.excluir();
        repository.save(instrucao);
    }
}
