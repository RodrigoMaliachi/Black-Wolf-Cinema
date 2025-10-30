# 🎬 Black Wolf Cinema - Resumen de Validaciones e Implementaciones

## ✅ Cambios Realizados

### 1. **Validaciones Personalizadas (paquete validation/)**

#### Archivos Creados:
- `ValidPassword.java` - Anotación para validar contraseña fuerte
- `PasswordValidator.java` - Validador que verifica:
  - ✓ Mínimo 8 caracteres
  - ✓ Contiene letras (a-zA-Z)
  - ✓ Contiene números (0-9)
  - ✓ Mensajes de error específicos para cada caso

- `ValidNames.java` - Anotación para validar nombres
- `NamesValidator.java` - Validador que verifica:
  - ✓ No vacío
  - ✓ Solo caracteres alfabéticos (incluyendo acentos)
  - ✓ Mensajes de error específicos

- `UniqueEmail.java` - Anotación para email único
- `UniqueEmailValidator.java` - Validador que verifica:
  - ✓ Email único en la base de datos
  - ✓ Mensaje: "El correo electrónico ya está registrado"

- `ValidDates.java` - Anotación para validar fechas
- `DatesValidator.java` - Validador que verifica:
  - ✓ Fecha no en el pasado
  - ✓ Mensajes de error específicos

### 2. **Actualización de Modelos**

#### User.java
- Mantiene la estructura existente (sin cambios en campos)

#### UserValidation.java (Validación de Registro)
```java
@NotBlank(message = "El nombre de usuario no puede estar vacío")
@Size(min = 4, message = "El nombre de usuario debe tener al menos 4 caracteres")
private String userName;

@ValidPassword // Valida: 8 caracteres, letras y números
private String password;

@NotBlank(message = "El nombre no puede estar vacío")
@ValidNames // Valida: solo caracteres alfabéticos
private String firstName;

@NotBlank(message = "El apellido no puede estar vacío")
@ValidNames // Valida: solo caracteres alfabéticos
private String lastName;

@NotBlank(message = "El correo electrónico no puede estar vacío")
@Pattern(...) // Valida formato email
@UniqueEmail // Valida email único en BD
private String email;
```

#### Movie.java
```java
@NotBlank(message = "El nombre de la película no puede estar vacío")
private String name;

@NotBlank(message = "La sinopsis no puede estar vacía")
private String synopsis;

@NotNull(message = "La duración no puede estar vacía")
@Min(value = 1, message = "La duración debe ser mayor que cero")
private int duration;

@NotBlank(message = "El trailer no puede estar vacío")
private String trailer;
```

#### MovieValidation.java
- Mensajes de validación mejorados
- Importes corregidos (groovyjar a jakarta.validation.constraints)

#### Show.java
```java
@ValidDates // Valida fechas
public class Show {
    @NotNull(message = "La fecha no puede estar vacía")
    private LocalDate showDate;

    @NotNull(message = "La hora no puede estar vacía")
    private LocalTime showHour;
    // ...
}
```

### 3. **Modelo de Asientos Apartados Temporalmente**

#### ReservedSeat.java (Nuevo)
```java
@Entity
@Table(name = "reserved_seats")
public class ReservedSeat {
    - seatNumber: Número del asiento
    - show: Referencia a la función
    - sessionId: ID de sesión del usuario
    - reservedAt: Fecha/hora de reserva
    - expiresAt: Fecha/hora de expiración (5 minutos después)
}
```

### 4. **DAOs y Servicios**

#### ReservedSeatDao.java
- `save()` - Guardar asiento apartado
- `delete()` - Liberar asiento
- `findExpiredSeats()` - Obtener asientos expirados
- `findByShowId()` - Asientos por función
- `findBySessionId()` - Asientos por sesión

#### ReservedSeatService.java & ReservedSeatServiceImpl.java
- Métodos de gestión de asientos apartados
- `releaseExpiredSeats()` - Liberar automáticamente asientos vencidos

### 5. **Scheduler de Liberación de Asientos**

#### SeatReleaseScheduler.java (Nuevo)
```java
@Scheduled(fixedDelay = 60000) // Cada minuto
public void releaseExpiredSeats()
```
- Ejecuta cada minuto
- Libera automáticamente asientos apartados hace más de 5 minutos

### 6. **Controladores Actualizados**

#### RegisterController.java
- Validación mejorada en `processRegister()`
- Mensaje de error actualizado: "El nombre de usuario ya está registrado"

#### MovieController.java
- Validación de imagen no vacía en `register()`
- Mensaje: "La imagen no puede estar vacía"
- Validación de tipo de archivo

#### ShowController.java
- `save()` con validación completa:
  - Fecha no vacía
  - Hora no vacía
  - Fecha no en el pasado
  - Película seleccionada
  - Sala seleccionada
- Retorno a formulario con errores si hay problemas

### 7. **Actualizaciones de DAO/Service**

#### UserDao.java & UserDaoImpl.java
- Nuevo método: `existsByEmail(String email)`
- Verifica si un email ya existe en la base de datos

#### UserService.java & UserServiceImpl.java
- Nuevo método: `existsByEmail(String email)`
- Delegado a DAO

### 8. **Templates HTML Mejorados**

#### signup.html
✨ Mejoras:
- Validación JavaScript en tiempo real
- Mostrador de requisitos de contraseña:
  - ✓/✗ Mínimo 8 caracteres
  - ✓/✗ Contiene letras
  - ✓/✗ Contiene números
- Desactivación automática de botón hasta cumplir requisitos
- Validación de nombres (solo letras)
- Estilos mejorados para errores
- Mensajes de error específicos por campo

#### movie-form.html
✨ Mejoras:
- Validación de imagen no vacía
- Validación de tipo de archivo
- Estilos para campos con error
- Mensajes de error claros
- Validación en tiempo real

#### show-form.html
✨ Mejoras:
- Validación de fechas:
  - Fecha mínima = hoy
  - No permite fechas pasadas
- Validación JavaScript mejorada
- Mensajes de error específicos
- Modal de alerta para errores
- Campos de error destacados

#### select-seats.html
✨ Mejoras:
- **⏱️ TIMER DE 5 MINUTOS**
  - Cuenta regresiva visible
  - Cambios de color según tiempo:
    - Verde normal
    - Amarillo cuando < 2 minutos
    - Rojo cuando < 1 minuto
  - Animación de pulso en estado crítico
- **Modal de Tiempo Expirado**
  - Mensaje amigable
  - Botón para volver a cartelera
  - Se libera sesión automáticamente
- Los asientos se liberan si no completa compra

### 9. **Archivos de Configuración**

#### application.properties
```properties
spring.validation.enabled=true
server.servlet.session.timeout=30m
```

---

## 📋 Mensajes de Validación Implementados

### Registro de Usuarios (signup.html)
| Campo | Validación | Mensaje |
|-------|-----------|---------|
| Nombre Usuario | No vacío, 4+ caracteres | "El nombre de usuario no puede estar vacío" / "El nombre de usuario debe tener al menos 4 caracteres" |
| Nombre | No vacío, solo letras | "El nombre no puede estar vacío" / "El nombre solo debe contener caracteres alfabéticos" |
| Apellido | No vacío, solo letras | "El apellido no puede estar vacío" / "El apellido solo debe contener caracteres alfabéticos" |
| Email | No vacío, formato válido, único | "El correo electrónico no puede estar vacío" / "El correo electrónico no es válido" / "El correo electrónico ya está registrado" |
| Contraseña | 8+ caracteres, letras, números | "La contraseña debe tener al menos 8 caracteres" / "La contraseña debe contener letras" / "La contraseña debe contener números" |

### Películas (movie-form.html)
| Campo | Validación | Mensaje |
|-------|-----------|---------|
| Nombre | No vacío | "El nombre de la película no puede estar vacío" |
| Sinopsis | No vacío | "La sinopsis no puede estar vacía" |
| Duración | No vacío, > 0 | "La duración no puede estar vacía" / "La duración debe ser mayor que cero" |
| Trailer | No vacío | "El trailer no puede estar vacío" |
| Imagen | No vacía, debe ser imagen | "La imagen no puede estar vacía" / "El archivo debe ser una imagen" |

### Funciones (show-form.html)
| Campo | Validación | Mensaje |
|-------|-----------|---------|
| Fecha | No vacía, no en pasado | "La fecha no puede estar vacía" / "La fecha no puede ser en el pasado" |
| Hora | No vacía | "La hora no puede estar vacía" |
| Película | Seleccionada | "La película no puede estar vacía" |
| Sala | Seleccionada | "La sala no puede estar vacía" |

### Selección de Asientos (select-seats.html)
| Funcionalidad | Descripción |
|--------------|-----------|
| Timer | Cuenta regresiva de 5 minutos antes de expirar reserva |
| Expiración | Modal amigable al expirar tiempo |
| Liberación Automática | Se liberan asientos si no completa compra |

---

## 🔄 Flujo de Validación

### 1️⃣ **Validación Cliente (JavaScript)**
   - Validación en tiempo real
   - Feedback inmediato al usuario
   - Desactivación de botones según requisitos

### 2️⃣ **Validación Servidor (Spring Validation)**
   - Anotaciones en modelos/clases de validación
   - BindingResult para capturar errores
   - Retorno a formulario con errores

### 3️⃣ **Validación en Base de Datos**
   - Único de email
   - Liberación automática de asientos

---

## 🚀 Características Adicionales

### Liberación Automática de Asientos
- **Scheduler**: Ejecuta cada minuto
- **Lógica**: Libera asientos apartados hace > 5 minutos
- **Tabla**: `reserved_seats` con campos:
  - `seat_number`
  - `idshow`
  - `session_id`
  - `reserved_at`
  - `expires_at`

### Timer de Compra (5 minutos)
- Cuenta regresiva visible
- Cambios visuales según tiempo
- Modal de expiración
- Retorno a cartelera al expirar

---

## 📝 Notas Técnicas

1. **Anotaciones de Validación**:
   - Personalizadas para casos específicos
   - Mensajes en español
   - Validación a nivel de objeto (Show) y campo

2. **Spring Boot Validation**:
   - `spring-boot-starter-validation` (en pom.xml)
   - `@Valid` en controladores
   - `BindingResult` para manejo de errores

3. **Scheduler**:
   - `@EnableScheduling` en componente
   - `@Scheduled(fixedDelay)` para tareas periódicas
   - No interfiere con el flujo principal

4. **JavaScript**:
   - Validación en tiempo real
   - Feedback visual inmediato
   - No bloquea el servidor

5. **HTML Templates**:
   - Thymeleaf para renderizado
   - Estilos CSS integrados
   - Responsive design

---

## ✨ Mejoras de UX

✅ Mensajes de error claros y específicos
✅ Validación en tiempo real
✅ Visualización de requisitos
✅ Desactivación de botones según validación
✅ Colores y animaciones para estados críticos
✅ Modal amigable para timeouts
✅ Feedback inmediato al usuario

---

## 🔗 Relaciones de Clases

```
UserValidation
├── @UniqueEmail → UniqueEmailValidator → UserService → UserDao
├── @ValidPassword → PasswordValidator
├── @ValidNames → NamesValidator
└── @Pattern → Email validation

Movie / MovieValidation
└── Validaciones de campos

Show
├── @ValidDates → DatesValidator
└── Validación de fechas

ReservedSeat
├── ReservedSeatDao/Impl
├── ReservedSeatService/Impl
└── SeatReleaseScheduler
```

---

## 📦 Estructura de Archivos Nueva

```
src/main/java/com/uady/blackWolfCinema/
├── validation/
│   ├── ValidPassword.java
│   ├── PasswordValidator.java
│   ├── ValidNames.java
│   ├── NamesValidator.java
│   ├── UniqueEmail.java
│   ├── UniqueEmailValidator.java
│   ├── ValidDates.java
│   └── DatesValidator.java
├── model/
│   └── ReservedSeat.java (new)
├── dao/
│   ├── ReservedSeatDao.java (new)
│   └── ReservedSeatDaoImpl.java (new)
├── service/
│   ├── ReservedSeatService.java (new)
│   └── ReservedSeatServiceImpl.java (new)
└── scheduler/
    └── SeatReleaseScheduler.java (new)
```

---

**Última actualización**: 30 de Octubre, 2025
**Versión**: 1.0
**Estado**: ✅ Completo
