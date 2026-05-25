package com.universidad.refactoringull.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.universidad.refactoringull.domain.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}