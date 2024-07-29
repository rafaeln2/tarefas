package com.tarefas.tarefas.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "address")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode //posso usar somente @Data que eles faz todas as anotações do lombok
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cep;
    @NotBlank
    private String logradouro;
    private String complemento;
    @NotBlank
    private String bairro;
    @NotBlank
    private String uf;

    public Address(String cep, String logradouro, String complemento, String bairro, String uf) {
        this.cep = cep;
        this.logradouro = logradouro;
        this.complemento = complemento;
        this.bairro = bairro;
        this.uf = uf;
    }
}
