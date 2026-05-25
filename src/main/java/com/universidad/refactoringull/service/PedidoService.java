package com.universidad.refactoringull.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.universidad.refactoringull.domain.Pedido;
import com.universidad.refactoringull.domain.Producto;
import com.universidad.refactoringull.repository.PedidoRepository;
import com.universidad.refactoringull.repository.ProductoRepository;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private ProductoRepository productoRepository;

    // Long Method + Large Parameter List + mezcla de responsabilidades
    public String procesarPedido(Long clienteId, String clienteNombre, String clienteEmail,
                                 String clienteTelefono, String clienteDireccion,
                                 String clienteCiudad, String clienteCodigoPostal,
                                 List<Long> productosIds, List<Integer> cantidades,
                                 String metodoPago, boolean esUrgente, String codigoDescuento) {

        // Validación del cliente (debería ser un método separado)
        if (clienteId == null || clienteNombre == null || clienteNombre.isBlank() ||
            clienteEmail == null || !clienteEmail.contains("@")) {
            return "ERROR_CLIENTE";
        }

        // Cálculo del total (lógica extensa)
        double total = 0;
        for (int i = 0; i < productosIds.size(); i++) {
            Producto p = productoRepository.findById(productosIds.get(i)).orElse(null);
            if (p == null) return "ERROR_PRODUCTO";
            total += p.getPrecio() * cantidades.get(i);
        }

        // Aplicar descuento (lógica de negocio mezclada)
        if (codigoDescuento != null && codigoDescuento.equals("VIP10")) {
            total = total * 0.90;
        } else if (codigoDescuento != null && codigoDescuento.equals("NEW20")) {
            total = total * 0.80;
        }

        // Notificación (responsabilidad ajena)
        System.out.println("Enviando email a: " + clienteEmail);
        System.out.println("Pedido urgente: " + esUrgente);

        // Persistencia
        Pedido pedido = new Pedido(clienteId, clienteNombre, total);
        Pedido saved = pedidoRepository.save(pedido);
        return "OK_" + saved.getId();
    }
}