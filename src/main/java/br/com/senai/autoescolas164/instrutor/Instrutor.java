package br.com.senai.autoescolas164.instrutor;

import br.com.senai.autoescolas164.endereco.Endereco;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name="Instrutor")
@Table(name="instrutores")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of="id")
public class Instrutor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String cnh;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @Embedded
    private Endereco endereco;

    public Instrutor(DadosInstrutor dados) {
        this.nome = dados.getNome();
        this.email = dados.getEmail();
        this.telefone = dados.getTelefone();
        this.cnh = dados.getCnh();
        this.especialidade = dados.getEspecialidade();
        this.endereco = new Endereco(dados.getEndereco());
    }
}
