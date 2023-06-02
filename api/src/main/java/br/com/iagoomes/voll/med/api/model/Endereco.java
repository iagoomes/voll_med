package br.com.iagoomes.voll.med.api.model;

public record Endereco(String logradouro,
                       String numero,
                       String complemento,
                       String bairro,
                       String cidade,
                       String uf,
                       String cep) {


}
