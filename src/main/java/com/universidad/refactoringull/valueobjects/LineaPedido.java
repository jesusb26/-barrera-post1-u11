package com.universidad.refactoringull.valueobjects;

public class LineaPedido {
    private final Long productoId;
    private final int cantidad;
    private final double precioUnitario;

    public LineaPedido(Long productoId, int cantidad, double precioUnitario) {
        this.productoId = productoId;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }

    public Long getProductoId() { return productoId; }
    public int getCantidad() { return cantidad; }
    public double getPrecioUnitario() { return precioUnitario; }
}