package br.com.iagoomes.voll.med.api.domain.endereco;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String uf;
    private String cep;

    public Endereco(EnderecoRequest enderecoRequest) { //utilizar somente em testes caso contrario utilizar o mapper
        this.logradouro = enderecoRequest.logradouro();
        this.numero = enderecoRequest.numero();
        this.complemento = enderecoRequest.complemento();
        this.bairro = enderecoRequest.bairro();
        this.cidade = enderecoRequest.cidade();
        this.uf = enderecoRequest.uf();
        this.cep = enderecoRequest.cep();
    }

    public void atualizarInformacoes(EnderecoRequest endereco) {
        if (endereco.logradouro() != null) {
            this.logradouro = endereco.logradouro();
        }
        if (endereco.numero() != null) {
            this.numero = endereco.numero();
        }
        if (endereco.complemento() != null) {
            this.complemento = endereco.complemento();
        }
        if (endereco.bairro() != null) {
            this.bairro = endereco.bairro();
        }
        if (endereco.cidade() != null) {
            this.cidade = endereco.cidade();
        }
        if (endereco.uf() != null) {
            this.uf = endereco.uf();
        }
        if (endereco.cep() != null) {
            this.cep = endereco.cep();
        }
    }
}
