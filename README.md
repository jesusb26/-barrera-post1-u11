# Refactorización Avanzada – Unidad 11 (Post-Contenido 1)

## 📊 Comparativa de métricas (antes vs después)

| Métrica | Antes de refactorizar (1er análisis) | Después de refactorizar (2º análisis) | Mejora |
|---------|--------------------------------------|----------------------------------------|--------|
| **Complejidad Ciclomática** del método `procesarPedido` | ~8 (estimado, por 12 parámetros) | 1 (cada método extraído ≤ 2) | ↓ 87% |
| **Code Smells totales** | 6 | 3 | ↓ 50% |
| **Cobertura de pruebas** | ~5.9% | ~5.0% | — |
| **Líneas de código** | ~186 | 289 | +103 (por nuevas clases) |
| **Parámetros en `procesarPedido`** | 12 | 6 | ↓ 50% |

> **Nota**: Los valores iniciales se obtuvieron del primer análisis (captura `issues-inicial.png` donde se reporta un método con 12 parámetros y severidad Blocker). La cobertura inicial no se muestra en las capturas.

## 🧹 Code smells identificados y corregidos

### Smells en el código original
1. **Long Parameter List** – `procesarPedido` recibía 12 parámetros (violación de "método pequeño").
2. **Primitive Obsession** – Datos del cliente (nombre, email, teléfono, dirección, ciudad, código postal) como tipos primitivos.
3. **Long Method** – El método mezclaba validación, cálculo, descuento, notificación y persistencia.
4. **Feature Envy** – La lógica de notificación estaba dentro de `PedidoService`.

### Smell remanente después de la refactorización
- **Parámetro no utilizado** – `metodoPago` se declara pero no se usa dentro del método. SonarQube lo detecta como un code smell de severidad *Medium*. *(Se puede eliminar o implementar en una futura iteración)*.

## 🔧 Técnicas de refactorización aplicadas

| Técnica | Aplicación |
|---------|-------------|
| **Introducir Value Object** | Se crearon `Direccion` y `DatosCliente` para encapsular los datos del cliente. |
| **Extract Method** | Se extrajeron `calcularTotal`, `aplicarDescuento`, `persistirPedido` y `notificarCliente`. |
| **Extract Class** | Se creó `NotificacionService` para separar la responsabilidad de notificación. |
| **Inyección por constructor** | Se eliminó `@Autowired` en campo, reemplazado por constructor con campos `final`. |

## 📸 Evidencia de los análisis

### Primer análisis (antes de refactorizar)
![alt text](<docs/Captura de pantalla 2026-05-25 162304.png>)
![alt text](<docs/Captura de pantalla 2026-05-25 162340.png>)
![alt text](<docs/Captura de pantalla 2026-05-25 162353.png>)

### Segundo análisis (después de refactorizar)
![alt text](<docs/Captura de pantalla 2026-05-25 170221.png>)
![alt text](<docs/Captura de pantalla 2026-05-25 170243.png>)
![alt text](<docs/Captura de pantalla 2026-05-25 170250.png>)

## Comparativa de los dos analisis:
![alt text](<docs/Captura de pantalla 2026-05-25 170351.png>)

## ✅ Estado del Quality Gate


En el segundo análisis, el **Quality Gate falló** debido a:
- 1 code smell de maintainability (parámetro no usado `metodoPago`).
- Cobertura baja (no alcanza el mínimo configurado).

Sin embargo, se logró una reducción significativa de la complejidad y de la mayoría de los smells originales bajando de 6 a 3.

## 🚀 Instrucciones para reproducir el análisis

```bash
# Asegurar que SonarQube local está corriendo
docker start sonarqube

# Ejecutar análisis (usar token global o de proyecto)
mvn clean verify sonar:sonar -Dsonar.token=TU_TOKEN_GLOBAL
```
