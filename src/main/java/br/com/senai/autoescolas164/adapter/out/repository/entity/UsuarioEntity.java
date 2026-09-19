package br.com.senai.autoescolas164.adapter.out.repository.entity;

import br.com.senai.autoescolas164.shared.vo.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "Usuario")
@Table(name = "usuarios")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String senha;

    @Enumerated(EnumType.STRING)
    private Role perfil; //Informa se o Usuário é "comum" (USER) ou "administrador" (ADMIN)

    private boolean ativo = true;
}
