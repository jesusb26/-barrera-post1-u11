package com.universidad.refactoringull.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.universidad.refactoringull.domain.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}