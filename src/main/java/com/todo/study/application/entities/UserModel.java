package com.todo.study.application.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = "Users")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String nome;
    private String cargo;
    @Column(nullable = false, unique = true)
    private String email;
    private String senha;
    private String telefone;
    @Column(length = 8)
    private String cep;
    private String rua;
    private String numero;
    private String cidade;
    private String empresa;
    private String uf;
}
