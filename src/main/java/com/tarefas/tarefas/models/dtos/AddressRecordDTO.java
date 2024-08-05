package com.tarefas.tarefas.models.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AddressRecordDTO(String cep,
                               String logradouro,
                               String complemento,
                               String bairro,
                               String localidade,
                               String uf,
                               String ibge,
                               String gia,
                               String ddd,
                               String siafi) {
}
