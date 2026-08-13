package com.restaurante.api.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore; // IMPORTANTE

@Entity
public class ItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    @JsonIgnore // 👈 ESSA LINHA DIZ PARA O JAVA NÃO ENTRAR EM LOOP
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "prato_id")
    private Prato prato;

    private int quantidade;

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Pedido getPedido() { return pedido; }
    public void setPedido(Pedido pedido) { this.pedido = pedido; }

    public Prato getPrato() { return prato; }
    public void setPrato(Prato prato) { this.prato = prato; }

    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }
}