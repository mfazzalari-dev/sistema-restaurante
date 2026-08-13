package com.restaurante.api.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeCliente;
    private String tipoEntrega;
    private String endereco;
    private String status = "PENDENTE"; // Valor padrão
    private Double valorTotal;
    private Double taxaEntrega;

    // Campo chave para o nosso reloginho funcionar
    private LocalDateTime dataCriacao;

    @OneToMany(cascade = CascadeType.ALL)
    private List<ItemPedido> itens;

    // Construtor vazio (obrigatório para o JPA/Hibernate)
    public Pedido() {
        this.dataCriacao = LocalDateTime.now(); // Garante a data no momento da criação
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNomeCliente() { return nomeCliente; }
    public void setNomeCliente(String nomeCliente) { this.nomeCliente = nomeCliente; }

    public String getTipoEntrega() { return tipoEntrega; }
    public void setTipoEntrega(String tipoEntrega) { this.tipoEntrega = tipoEntrega; }

    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Double getValorTotal() { return valorTotal; }
    public void setValorTotal(Double valorTotal) { this.valorTotal = valorTotal; }

    public Double getTaxaEntrega() { return taxaEntrega; }
    public void setTaxaEntrega(Double taxaEntrega) { this.taxaEntrega = taxaEntrega; }

    public LocalDateTime getDataCriacao() { return dataCriacao; }
    public void setDataCriacao(LocalDateTime dataCriacao) { this.dataCriacao = dataCriacao; }

    public List<ItemPedido> getItens() { return itens; }
    public void setItens(List<ItemPedido> itens) { this.itens = itens; }
}