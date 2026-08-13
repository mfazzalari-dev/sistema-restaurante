package com.restaurante.api.controller;

import com.restaurante.api.model.Pedido;
import com.restaurante.api.model.ItemPedido;
import com.restaurante.api.model.Prato;
import com.restaurante.api.repository.PedidoRepository;
import com.restaurante.api.repository.PratoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private PratoRepository pratoRepository;

    @GetMapping
    public List<Pedido> listarTodos() {
        return pedidoRepository.findAll();
    }

    @PostMapping
    public Pedido criarPedido(@RequestBody Pedido pedido) {
        double subtotal = 0;

        for (ItemPedido item : pedido.getItens()) {
            Prato prato = pratoRepository.findById(item.getPrato().getId())
                    .orElseThrow(() -> new RuntimeException("Prato não encontrado"));
            item.setPrato(prato);
            item.setPedido(pedido);
            subtotal += prato.getPreco() * item.getQuantidade();
        }

        if ("DELIVERY".equals(pedido.getTipoEntrega())) {
            pedido.setTaxaEntrega(7.00);
        } else {
            pedido.setTaxaEntrega(0.00);
            pedido.setEndereco("Retirada no Balcão");
        }

        pedido.setValorTotal(subtotal + pedido.getTaxaEntrega());
        pedido.setStatus("PENDENTE");

        return pedidoRepository.save(pedido);
    }

    @PutMapping("/{id}/status")
    public Pedido atualizarStatus(@PathVariable Long id, @RequestParam String novoStatus) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
        pedido.setStatus(novoStatus);
        return pedidoRepository.save(pedido);
    }

    @GetMapping("/resumo")
    public Map<String, Object> obterResumoFinanceiro() {
        List<Pedido> todosPedidos = pedidoRepository.findAll();

        int totalPedidos = todosPedidos.size();
        double faturamentoTotal = todosPedidos.stream()
                .mapToDouble(Pedido::getValorTotal)
                .sum();

        Map<String, Object> resumo = new HashMap<>();
        resumo.put("totalPedidos", totalPedidos);
        resumo.put("faturamentoTotal", faturamentoTotal);

        return resumo;
    }
}