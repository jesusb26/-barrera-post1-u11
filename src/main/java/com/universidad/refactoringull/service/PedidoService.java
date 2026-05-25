package com.universidad.refactoringull.service;

import java.util.Arrays;

import org.springframework.stereotype.Service;

import com.universidad.refactoringull.domain.Pedido;
import com.universidad.refactoringull.repository.PedidoRepository;
import com.universidad.refactoringull.valueobjects.CodigoDescuento;
import com.universidad.refactoringull.valueobjects.DatosCliente;
import com.universidad.refactoringull.valueobjects.LineaPedido;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final NotificacionService notificacionService;

    // Inyección por constructor (elimina @Autowired en campos)
    public PedidoService(PedidoRepository pedidoRepository, NotificacionService notificacionService) {
        this.pedidoRepository = pedidoRepository;
        this.notificacionService = notificacionService;
    }

    public String procesarPedido(Long clienteId, DatosCliente cliente, 
                                 LineaPedido[] lineas, String metodoPago,
                                 boolean esUrgente, CodigoDescuento descuento) {
        double total = calcularTotal(lineas);
        double totalConDescuento = aplicarDescuento(total, descuento);
        notificacionService.notificarPedido(cliente, esUrgente);
        return persistirPedido(clienteId, cliente, totalConDescuento);
    }

    private double calcularTotal(LineaPedido[] lineas) {
        return Arrays.stream(lineas)
                .mapToDouble(l -> l.getPrecioUnitario() * l.getCantidad())
                .sum();
    }

    private double aplicarDescuento(double total, CodigoDescuento d) {
        return d != null ? total * (1 - d.getPorcentaje()) : total;
    }

    private String persistirPedido(Long clienteId, DatosCliente cliente, double total) {
        Pedido pedido = new Pedido(clienteId, cliente.getNombre(), total);
        Pedido saved = pedidoRepository.save(pedido);
        return "OK_" + saved.getId();
    }
}