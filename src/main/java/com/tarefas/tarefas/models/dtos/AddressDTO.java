package com.tarefas.tarefas.models.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//Endereco https://viacep.com.br/
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddressDTO {
    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;
    private String ibge;
    private String gia;
    private String ddd;
    private String siafi;

//    public AddressDTO(String cep, String logradouro, String complemento, String bairro, String uf) {
//        this.cep = cep;
//        this.logradouro = logradouro;
//        this.complemento = complemento;
//        this.bairro = bairro;
//        this.uf = uf;
//    }

    public String getCep() {
        return cep;
    }

    public AddressDTO setCep(String cep) {
        this.cep = cep;
        return this;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public AddressDTO setLogradouro(String logradouro) {
        this.logradouro = logradouro;
        return this;
    }

    public String getComplemento() {
        return complemento;
    }

    public AddressDTO setComplemento(String complemento) {
        this.complemento = complemento;
        return this;
    }

    public String getBairro() {
        return bairro;
    }

    public AddressDTO setBairro(String bairro) {
        this.bairro = bairro;
        return this;
    }

    public String getLocalidade() {
        return localidade;
    }

    public AddressDTO setLocalidade(String localidade) {
        this.localidade = localidade;
        return this;
    }

    public String getUf() {
        return uf;
    }

    public AddressDTO setUf(String uf) {
        this.uf = uf;
        return this;
    }

    public String getIbge() {
        return ibge;
    }

    public AddressDTO setIbge(String ibge) {
        this.ibge = ibge;
        return this;
    }

    public String getGia() {
        return gia;
    }

    public AddressDTO setGia(String gia) {
        this.gia = gia;
        return this;
    }

    public String getDdd() {
        return ddd;
    }

    public AddressDTO setDdd(String ddd) {
        this.ddd = ddd;
        return this;
    }

    public String getSiafi() {
        return siafi;
    }

    public AddressDTO setSiafi(String siafi) {
        this.siafi = siafi;
        return this;
    }
}
