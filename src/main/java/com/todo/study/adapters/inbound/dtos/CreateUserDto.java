package com.todo.study.adapters.inbound.dtos;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class CreateUserDto {
    @NotBlank
    private String nome;
    @NotBlank
    private String cargo;
    @NotBlank
    @Pattern(regexp="/^[a-z0-9.]+@[a-z0-9]+\\.[a-z]+\\.([a-z]+)?$/i]")
    private String email;
    @NotBlank
    @Size(min = 8, max = 21)
    private String senha;
    @NotBlank
    @Pattern(regexp="(\\(?\\d{2}\\)?\\s)?(\\d{4,5}\\-\\d{4})")
    private String telefone;
    @NotBlank
    @Size(min = 8, max = 8)
    private String cep;
    @NotBlank
    private String rua;
    @NotBlank
    private String numero;
    @NotBlank
    private String cidade;
    @NotBlank
    private String empresa;
    @NotBlank
    @Size(min = 2, max = 2)
    private String uf;
}
