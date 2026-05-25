package com.universidad.refactoringull.valueobjects;

import java.util.Objects;

public final class Direccion {
    private final String calle;
    private final String ciudad;
    private final String codigoPostal;

    public Direccion(String calle, String ciudad, String codigoPostal) {
        if (calle == null || calle.isBlank())
            throw new IllegalArgumentException("La calle es obligatoria");
        if (ciudad == null || ciudad.isBlank())
            throw new IllegalArgumentException("La ciudad es obligatoria");
        if (codigoPostal == null || codigoPostal.isBlank())
            throw new IllegalArgumentException("El código postal es obligatorio");
        this.calle = calle;
        this.ciudad = ciudad;
        this.codigoPostal = codigoPostal;
    }

    // Getters (solo lectura)
    public String getCalle() { return calle; }
    public String getCiudad() { return ciudad; }
    public String getCodigoPostal() { return codigoPostal; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Direccion direccion = (Direccion) o;
        return Objects.equals(calle, direccion.calle) &&
               Objects.equals(ciudad, direccion.ciudad) &&
               Objects.equals(codigoPostal, direccion.codigoPostal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(calle, ciudad, codigoPostal);
    }
}