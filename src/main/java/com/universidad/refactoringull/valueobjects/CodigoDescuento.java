package com.universidad.refactoringull.valueobjects;

public class CodigoDescuento {
    private final String codigo;
    private final double porcentaje;

    public CodigoDescuento(String codigo, double porcentaje) {
        this.codigo = codigo;
        this.porcentaje = porcentaje;
    }

    public String getCodigo() { return codigo; }
    public double getPorcentaje() { return porcentaje; }
}