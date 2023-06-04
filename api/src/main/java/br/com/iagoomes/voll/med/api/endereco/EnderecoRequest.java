package br.com.iagoomes.voll.med.api.endereco;

public record EnderecoRequest(String logradouro,
                              String numero,
                              String complemento,
                              String bairro,
                              String cidade,
                              String uf,
                              String cep) {


}
