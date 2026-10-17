CREATE TABLE IF NOT EXISTS instrucoes (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    aluno_id BIGINT NOT NULL,
    instrutor_id BIGINT NOT NULL,
    data_hora DATETIME NOT NULL,

    CONSTRAINT fk_instrucoes_aluno_id FOREIGN KEY(aluno_id) REFERENCES alunos(id),
    CONSTRAINT fk_instrucoes_instrutor_id FOREIGN KEY(instrutor_id) REFERENCES instrutores(id)
)