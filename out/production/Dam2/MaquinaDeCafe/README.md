# ☕ Máquina de Café en Kotlin

Este proyecto implementa una **máquina de café virtual** en Kotlin utilizando el concepto de **máquina de estados**.  
El objetivo es simular el comportamiento de una maquina de café automática, gestionando recursos, implementando estados y haciendo uso de herencias

---

## 📌 Características principales

- ✅ **Máquina de estados** implementada con `sealed class`.
- ✅ Uso de `object` para estados simples y `data class` para estados con parámetros.
- ✅ Simulación de interacción con el usuario a través de una clase `Interfaz`.
- ✅ Diferentes tipos de café: **Americano**, **Africano** y **Capuchino**.
- ✅ Control de recursos internos: agua, café, leche, azúcar, vasos y palitos.
- ✅ Simulación de pago y selección de azúcar.
- ✅ Gestión automática de errores y reposición de la máquina.

---

## 🏗️ Estructura del proyecto

MaquinaDeCafe/  
│── Cafe.kt             # Clase abstracta y tipos de café  
│── Interfaz.kt         # Manejo de la interacción con el usuario(como una view)  
│── MaquinadeCafe.kt    # Lógica principal y estados de la máquina

---

## 🔄 Estados de la máquina

La lógica de la máquina se gestiona con una **clase sellada** `EstadoMaquinaCafe`, que contiene los posibles estados:

- `Idle` → Estado base.
- `VerificandoEstado`, `VerificandoCafe`, `VerificandoAgua`, `VerificandoAzucar`, `VerificandoLeche`, `VerificandoVaso`, `VerificandoPalito` → Verificación de recursos.
- `PidiendoCafe` → El usuario elige el tipo de café.
- `PidiendoTarjeta(cafe)` → Procesa el pago.
- `PidiendoAzucar(cafe)` → Solicita cantidad de azúcar.
- `HaciendoCafe(cafe)` → Simula la preparación.
- `CalculandoRecursos(cafe)` → Resta los recursos utilizados.
- `Reponiendo` → Restaura recursos cuando hay errores.
- `Error(mensaje)` → Maneja Errores.

---

## ▶️ Ejecución

1. Compila el proyecto en tu IDE o con `kotlin`.
2. Ejecuta la función `main()` en la Maquina de cafe.
3. Interactúa con la máquina siguiendo las instrucciones en consola.

Ejemplo de flujo:

1. Verificación de recursos.
2. Elección de café.
3. Pago simulado.
4. Selección de azúcar.
5. Preparación del café.
6. Consumo de recursos y retorno al estado `Idle`.

---

## 📚 Conceptos aplicados

- **Programación orientada a objetos (POO)**: herencia y polimorfismo con clases abstractas y abiertas.
- **Máquina de estados**: control robusto del flujo de ejecución.
- **Clases selladas (`sealed class`)**: seguridad en el control de estados con `when`.
- **Objetos Singleton (`object`)**: la máquina existe como única instancia.
- **Simulación interactiva**: uso de `readLine()` y `Thread.sleep()` para interacción y realismo.
