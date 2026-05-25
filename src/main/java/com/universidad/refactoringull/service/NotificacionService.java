package com.universidad.refactoringull.service;

import org.springframework.stereotype.Service;

import com.universidad.refactoringull.valueobjects.DatosCliente;

@Service public class NotificacionService {     
public void notificarPedido(DatosCliente cliente, boolean urgente) {         
    // logica de notificacion (email, SMS, etc.)     
    } 
} 
