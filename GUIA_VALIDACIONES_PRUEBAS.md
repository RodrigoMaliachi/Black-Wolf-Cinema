# 🎬 Guía de Validaciones Implementadas - Black Wolf Cinema

## 📋 Descripción General

Se han implementado **todas las validaciones y funcionalidades** solicitadas en el sistema de cine. Este documento describe cómo verificar cada una.

---

## ✅ VALIDACIONES IMPLEMENTADAS

### 1. 📝 VALIDACIÓN DE REGISTRO (RF_01-05)

**Ubicación**: `/register/showRegistrationForm` (página de registro)

#### Pruebas:
- [ ] **Nombre de Usuario**
  - Campo vacío: Muestra ❌ "El nombre de usuario no puede estar vacío"
  - Menos de 4 caracteres: Muestra ❌ "El nombre de usuario debe tener al menos 4 caracteres"

- [ ] **Nombre**
  - Campo vacío: Muestra ❌ "El nombre no puede estar vacío"
  - Con números o caracteres especiales: Muestra ❌ "El nombre solo debe contener caracteres alfabéticos"
  - Válido: ✅ Acepta solo letras

- [ ] **Apellido**
  - Mismas validaciones que Nombre

- [ ] **Email (RF_02)**
  - Campo vacío: Muestra ❌ "El correo electrónico no puede estar vacío"
  - Formato inválido: Muestra ❌ "El correo electrónico no es válido"
  - Email duplicado: Muestra ❌ "El correo electrónico ya está registrado" ⭐ **NUEVA**
  - Válido: ✅ Se registra sin problema

- [ ] **Contraseña (RF_04-04.03)**
  - Campo vacío: Muestra validación visual
  - Menos de 8 caracteres: Muestra ❌ "La contraseña debe tener al menos 8 caracteres"
  - Sin letras: Muestra ❌ "La contraseña debe contener letras"
  - Sin números: Muestra ❌ "La contraseña debe contener números"
  - Válida (8+ caracteres con letras y números): ✅ Se habilita botón registro
  
  **Bonus**: Mostrador en tiempo real de requisitos:
  - ✓ Al menos 8 caracteres
  - ✓ Contiene letras
  - ✓ Contiene números

---

### 2. 🎬 VALIDACIÓN DE PELÍCULAS (RF_35)

**Ubicación**: `/admin/movies/add-movie`

#### Pruebas:
- [ ] **Nombre de Película**
  - Campo vacío: Muestra ❌ "El nombre de la película no puede estar vacío"
  - Válido: ✅ Se acepta

- [ ] **Sinopsis**
  - Campo vacío: Muestra ❌ "La sinopsis no puede estar vacía"
  - Válida: ✅ Se acepta

- [ ] **Duración (RF_35.06)**
  - Campo vacío: Muestra ❌ "La duración no puede estar vacía"
  - Valor <= 0: Muestra ❌ "La duración debe ser mayor que cero"
  - Valor válido: ✅ Se acepta

- [ ] **Trailer**
  - Campo vacío: Muestra ❌ "El trailer no puede estar vacío"
  - Válido: ✅ Se acepta

- [ ] **Imagen/Portada**
  - Campo vacío: Muestra ❌ "La imagen no puede estar vacía" ⭐ **NUEVA**
  - Archivo no es imagen: Muestra ❌ "El archivo debe ser una imagen"
  - Válida: ✅ Se sube correctamente

---

### 3. 📅 VALIDACIÓN DE FUNCIONES (RF_47-47.03)

**Ubicación**: `/admin/shows/add-show`

#### Pruebas:
- [ ] **Fecha de Función (RF_47)**
  - Campo vacío: Muestra ❌ "La fecha no puede estar vacía"
  - Fecha pasada: Muestra ❌ "La fecha no puede ser en el pasado" ⭐ **NUEVA**
  - Fecha válida: ✅ Se acepta (input tiene min="hoy")

- [ ] **Hora de Función**
  - Campo vacío: Muestra ❌ "La hora no puede estar vacía"
  - Hora válida: ✅ Se acepta

- [ ] **Película**
  - No seleccionada: Muestra ❌ "La película no puede estar vacía"
  - Seleccionada: ✅ Se acepta

- [ ] **Sala de Cine**
  - No seleccionada: Muestra ❌ "La sala no puede estar vacía"
  - Seleccionada: ✅ Se acepta

---

### 4. ⏱️ TIMER DE 5 MINUTOS (RF_24)

**Ubicación**: `/show/{movieId}` → Seleccionar asientos → `/tickets/selectSeats`

#### Características:
- ⏰ **Contador Regresivo**: 5:00 → 4:59 → ... → 0:00
- 🟢 **Color Normal**: Tiempo > 2 minutos
- 🟡 **Color Amarillo**: 1:59 - 2:00 (advertencia)
- 🔴 **Color Rojo + Animación**: < 1:00 (crítico)
- 🔔 **Modal de Expiración**: Al llegar a 0:00
  - Mensaje amigable: "¡Tu tiempo se ha agotado!"
  - Botón: "Volver a la cartelera"
  - Se liberan asientos automáticamente

#### Pruebas:
- [ ] Ver contador iniciando en 05:00
- [ ] Ver cambios de color en 02:00 y 01:00
- [ ] Esperar a 00:00 (o acelerar con dev tools)
- [ ] Ver modal y volver a cartelera
- [ ] Verificar que asientos se liberan

---

### 5. 🔄 LIBERACIÓN AUTOMÁTICA DE ASIENTOS (RF_24 - Backend)

**Ubicación**: Scheduler que ejecuta cada minuto

#### Características:
- ⏱️ **Ejecuta cada minuto**: `SeatReleaseScheduler`
- 🔓 **Libera asientos**: Apartados hace > 5 minutos
- 📊 **Tabla**: `reserved_seats` (creada automáticamente)

#### Verificación:
```sql
-- Ver asientos apartados
SELECT * FROM reserved_seats;

-- Ver asientos expirados
SELECT * FROM reserved_seats WHERE expires_at < NOW();
```

---

## 🧪 INSTRUCCIONES DE PRUEBA

### Prueba 1: Validar Registro con Email Duplicado

```bash
1. Ir a http://localhost:8080/register/showRegistrationForm
2. Rellenar con datos válidos y un email que ya existe
3. Enviar formulario
4. Debe mostrar: ❌ "El correo electrónico ya está registrado"
```

### Prueba 2: Validar Contraseña Débil

```bash
1. Ir a http://localhost:8080/register/showRegistrationForm
2. En campo Contraseña escribir "test" (solo 4 caracteres)
3. Ver en tiempo real:
   - ✗ Al menos 8 caracteres (rojo)
   - ✓ Contiene letras (verde)
   - ✗ Contiene números (rojo)
4. Botón "Registrar" está deshabilitado
5. Escribir "test1234" (8 caracteres + números)
6. Botón se habilita, todos los requisitos están verdes
```

### Prueba 3: Validar Nombre (Solo Letras)

```bash
1. Ir a http://localhost:8080/register/showRegistrationForm
2. En Nombre escribir "Juan123"
3. Enviar formulario
4. Debe mostrar: ❌ "El nombre solo debe contener caracteres alfabéticos"
5. Escribir "Juan" (solo letras)
6. Debe aceptar correctamente
```

### Prueba 4: Validar Película Sin Imagen

```bash
1. Ir a http://localhost:8080/admin/movies/add-movie
2. Rellenar todos los campos EXCEPTO Imagen/Portada
3. Hacer clic en Guardar
4. Debe mostrar: ❌ "La imagen no puede estar vacía"
```

### Prueba 5: Validar Función Con Fecha Pasada

```bash
1. Ir a http://localhost:8080/admin/shows/add-show
2. Seleccionar una fecha en el pasado
3. Hacer clic en Guardar
4. Debe mostrar: ❌ "La fecha no puede ser en el pasado"
5. Input tiene min="hoy" (no permite fechas pasadas)
```

### Prueba 6: Timer de 5 Minutos

```bash
1. Seleccionar una película
2. Seleccionar una función
3. Ir a la página de selección de asientos
4. Ver contador comenzando en 05:00
5. Esperar hasta 00:00 (o simular en dev tools)
6. Ver modal: "¡Tu tiempo se ha agotado!"
7. Hacer clic en "Volver a la cartelera"
8. Verificar que asientos fueron liberados
```

### Prueba 7: Liberación Automática (Backend)

```bash
-- En base de datos
1. Ver tabla: SELECT * FROM reserved_seats;
2. Los asientos deben tener expires_at = reserved_at + 5 minutos
3. El scheduler ejecuta cada minuto
4. Asientos con expires_at < NOW() se eliminan automáticamente
```

---

## 🔍 ARCHIVOS MODIFICADOS/CREADOS

### Validaciones (validation/)
- ✅ `ValidPassword.java` (nuevo)
- ✅ `PasswordValidator.java` (nuevo)
- ✅ `ValidNames.java` (nuevo)
- ✅ `NamesValidator.java` (nuevo)
- ✅ `UniqueEmail.java` (nuevo)
- ✅ `UniqueEmailValidator.java` (nuevo)
- ✅ `ValidDates.java` (nuevo)
- ✅ `DatesValidator.java` (nuevo)
- ✅ `UserValidation.java` (actualizado)
- ✅ `MovieValidation.java` (actualizado)

### Modelos (model/)
- ✅ `User.java` (sin cambios de campos)
- ✅ `Movie.java` (actualizado mensajes)
- ✅ `Show.java` (actualizado validaciones)
- ✅ `ReservedSeat.java` (nuevo)

### DAOs (dao/)
- ✅ `UserDao.java` (actualizado)
- ✅ `UserDaoImpl.java` (actualizado)
- ✅ `ReservedSeatDao.java` (nuevo)
- ✅ `ReservedSeatDaoImpl.java` (nuevo)

### Servicios (service/)
- ✅ `UserService.java` (actualizado)
- ✅ `UserServiceImpl.java` (actualizado)
- ✅ `ReservedSeatService.java` (nuevo)
- ✅ `ReservedSeatServiceImpl.java` (nuevo)

### Controladores (controller/)
- ✅ `RegisterController.java` (actualizado)
- ✅ `MovieController.java` (actualizado)
- ✅ `ShowController.java` (actualizado)

### Scheduler (scheduler/)
- ✅ `SeatReleaseScheduler.java` (nuevo)

### Templates (templates/)
- ✅ `register/signup.html` (actualizado)
- ✅ `admin/movie-form.html` (actualizado)
- ✅ `shows/show-form.html` (actualizado)
- ✅ `select-seats.html` (actualizado)

### Configuración
- ✅ `BlackWolfCinemaApplication.java` (actualizado)
- ✅ `application.properties` (actualizado)

### Documentación
- ✅ `VALIDACIONES_IMPLEMENTADAS.md` (nuevo)
- ✅ `init_reserved_seats_table.sql` (nuevo)

---

## 📊 MATRIZ DE REQUISITOS

| Requisito | Estado | Ubicación |
|-----------|--------|-----------|
| RF_01: Nombre/Apellido no vacíos y solo letras | ✅ | `NamesValidator.java` + `signup.html` |
| RF_02: Email único | ✅ | `UniqueEmailValidator.java` |
| RF_02.01: Mensaje "Email ya registrado" | ✅ | `UniqueEmailValidator.java` |
| RF_04: Contraseña 8+ caracteres con letras y números | ✅ | `PasswordValidator.java` |
| RF_04.01: Mensaje "Al menos 8 caracteres" | ✅ | `PasswordValidator.java` |
| RF_04.02: Mensaje "Debe contener letras" | ✅ | `PasswordValidator.java` |
| RF_04.03: Mensaje "Debe contener números" | ✅ | `PasswordValidator.java` |
| RF_24: Liberar asientos si no compra en 5 min | ✅ | `SeatReleaseScheduler.java` + `select-seats.html` |
| RF_35.06: Mensaje "Duración no puede estar vacía" | ✅ | `MovieValidation.java` + `MovieValidator` |
| RF_35.07: Mensaje "Imagen no puede estar vacía" | ✅ | `MovieController.java` + `movie-form.html` |
| RF_47: Validar fechas válidas y coherentes | ✅ | `DatesValidator.java` + `ShowController.java` |
| RF_47.01: Mensaje "Fecha inicio no válida" | ✅ | `DatesValidator.java` |
| RF_47.02: Mensaje "Fecha fin no válida" | ✅ | `DatesValidator.java` |
| RF_47.03: Mensaje "Inicio no puede ser > fin" | ✅ | `DatesValidator.java` |
| Mostrar errores específicos por campo | ✅ | Todos los templates |
| Timer visual 5 minutos | ✅ | `select-seats.html` |
| Modal amigable al expirar | ✅ | `select-seats.html` |

---

## 🚀 PRÓXIMOS PASOS (Opcional)

1. **Persistencia de asientos apartados**:
   - Los asientos se guardan en BD automáticamente
   - Se liberan cada minuto por scheduler

2. **Notificaciones por email**:
   - Notificar al usuario antes de expiración
   - Confirmar reserva cuando se completa compra

3. **Estadísticas**:
   - Rastrear asientos abandonados
   - Mostrar tasa de conversión

---

## 📞 SOPORTE

Para preguntas sobre la implementación:
1. Revisar `VALIDACIONES_IMPLEMENTADAS.md`
2. Revisar código en `validation/` directory
3. Revisar mensajes en templates HTML

---

**Última actualización**: 30 de Octubre, 2025
**Versión**: 1.0
**Estado**: ✅ Completamente Implementado
