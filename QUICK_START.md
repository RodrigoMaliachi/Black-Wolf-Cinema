# ⚡ QUICK START - Black Wolf Cinema
## Comenzar en 5 minutos

---

## 🎯 Si solo tienes 5 minutos...

### ✅ 1. Verificar MySQL (30 segundos)

```bash
# Windows - Abrir Services
services.msc
# Buscar MySQL80 → Start

# Linux/Mac
sudo systemctl start mysql
```

### ✅ 2. Compilar (2 minutos)

```bash
cd d:\xampp\htdocs\BlackWolfCinema

# Windows PowerShell
.\mvnw.cmd clean compile

# Linux/Mac
./mvnw clean compile
```

Resultado esperado: `BUILD SUCCESS ✅`

### ✅ 3. Ejecutar (1 minuto)

```bash
.\mvnw.cmd spring-boot:run
# O presionar F5 en tu IDE
```

Esperar: "Started BlackWolfCinemaApplication in X seconds"

### ✅ 4. Abrir navegador (1 minuto)

```
http://localhost:8080/register/showRegistrationForm
```

---

## 🎬 Si tienes 15 minutos...

### Prueba 1: Validación de Email Duplicado

```bash
1. Crear usuario: usuario@test.com
2. Intentar crear otro con email igual
3. Ver: ❌ "El correo electrónico ya está registrado"
```

### Prueba 2: Validación de Contraseña

```bash
1. Campo contraseña: "test"
2. Ver requisitos en rojo ✗
3. Campo contraseña: "test1234"
4. Ver requisitos en verde ✓
5. Botón "Registrar" se habilita
```

### Prueba 3: Solo Letras en Nombre

```bash
1. Nombre: "Juan123"
2. Enviar
3. Ver: ❌ "solo debe contener caracteres alfabéticos"
```

### Prueba 4: Timer 5 Minutos

```bash
1. Seleccionar película
2. Seleccionar función
3. Ir a seleccionar asientos
4. Ver contador: 05:00 ⏱️
5. Cambio de color en 02:00 (amarillo)
6. Cambio de color en 01:00 (rojo con animación)
```

---

## 🔍 Si tienes 30 minutos...

### Leer documentación clave:

```
1. RESUMEN_IMPLEMENTACION.md (5 min)
   - Visión general de todo

2. VALIDACIONES_IMPLEMENTADAS.md (10 min)
   - Detalle técnico

3. GUIA_VALIDACIONES_PRUEBAS.md (10 min)
   - Cómo probar todo

4. Revisar código:
   - validation/PasswordValidator.java
   - validation/UniqueEmailValidator.java
   - scheduler/SeatReleaseScheduler.java
```

---

## 📋 CHECKLIST DE VERIFICACIÓN

- [ ] MySQL está corriendo
- [ ] Compilación exitosa
- [ ] Servidor iniciado en puerto 8080
- [ ] Página de login accesible
- [ ] Registro funciona
- [ ] Email duplicado muestra error
- [ ] Contraseña débil muestra error
- [ ] Validación de nombres funciona
- [ ] Validación de películas funciona
- [ ] Validación de funciones funciona
- [ ] Timer de 5 minutos funciona
- [ ] Modal de expiración aparece

---

## 🆘 PROBLEMAS COMUNES

| Problema | Solución |
|----------|----------|
| Puerto 8080 en uso | Cambiar `server.port=8081` en application.properties |
| MySQL no corre | `services.msc` → MySQL80 → Start |
| Error de compilación | Ejecutar `.\mvnw.cmd clean compile` |
| Tabla no existe | Reiniciar app (Hibernate crea tablas) |
| Timer no funciona | Actualizar F5 la página |

---

## 📚 DOCUMENTACIÓN COMPLETA

Si necesitas más detalle:

1. **RESUMEN_IMPLEMENTACION.md** - Estado del proyecto
2. **VALIDACIONES_IMPLEMENTADAS.md** - Detalles técnicos
3. **GUIA_VALIDACIONES_PRUEBAS.md** - Cómo probar
4. **GUIA_COMPILACION_EJECUCION.md** - Compilación/ejecución
5. **INDICE_CAMBIOS.md** - Todos los cambios

---

## 🎓 ENTENDER EL CÓDIGO

### Validador Personalizado (Ejemplo Simple)

```java
// validation/PasswordValidator.java
@Override
public boolean isValid(String value, ConstraintValidatorContext context) {
    if (value.length() < 8) {
        context.buildConstraintViolationWithTemplate(
            "La contraseña debe tener al menos 8 caracteres"
        ).addConstraintViolation();
        return false;
    }
    return true;
}
```

### Uso en Modelo

```java
// validation/UserValidation.java
@ValidPassword  // Usa el validador
private String password;
```

### Uso en Controlador

```java
// controller/RegisterController.java
@Valid @ModelAttribute("newUser") UserValidation theNewUser,
BindingResult theBindingResult
```

---

## 🚀 ARQUITECTURA EN 30 SEGUNDOS

```
Usuario (HTML/JavaScript)
    ↓
Spring Validation (@Valid)
    ↓
Validadores Personalizados
    ↓
Base de Datos (MySQL)
    ↓
Scheduler (Cada minuto)
    ↓
Liberación Automática
```

---

## 💡 TIPS

✅ Usar `.\mvnw.cmd clean compile` si hay errores  
✅ Usar `Ctrl+F5` para refrescar sin cache  
✅ Ver logs en consola para debug  
✅ Revisar `reserved_seats` en BD para asientos apartados  
✅ Timer se inicia automáticamente al seleccionar asientos  

---

## 🎯 PRÓXIMOS PASOS

### Para Desarrolladores:
1. Leer VALIDACIONES_IMPLEMENTADAS.md
2. Explorar código en validation/
3. Entender flujo de validación
4. Revisar controladores actualizados

### Para Testers:
1. Leer GUIA_VALIDACIONES_PRUEBAS.md
2. Seguir cada prueba
3. Reportar bugs (habrá pocos 😉)

### Para DevOps:
1. Leer GUIA_COMPILACION_EJECUCION.md
2. Configurar CI/CD
3. Desplegar en producción

---

## 📱 ACCESOS RÁPIDOS

| Página | URL | Función |
|--------|-----|---------|
| 🏠 Home | http://localhost:8080/ | Ver películas |
| 🔐 Login | http://localhost:8080/loginPage | Acceso |
| ✍️ Registro | http://localhost:8080/register/showRegistrationForm | Crear cuenta |
| 🎬 Películas | http://localhost:8080/admin/listMovies | Admin |
| 🎭 Funciones | http://localhost:8080/admin/listShows | Admin |
| 🎫 Asientos | http://localhost:8080/show/1 | Reservar |

---

## ⏱️ CRONOGRAMA DE APRENDIZAJE

```
Día 1 (1 hora):
  - 15 min: Compilar y ejecutar
  - 15 min: Leer RESUMEN_IMPLEMENTACION.md
  - 15 min: Probar validaciones
  - 15 min: Revisar timer

Día 2 (1 hora):
  - 30 min: Leer VALIDACIONES_IMPLEMENTADAS.md
  - 30 min: Explorar código

Día 3 (30 min):
  - 30 min: Leer GUIA_VALIDACIONES_PRUEBAS.md
```

---

## 🎉 ¡LISTO!

Todo está implementado y documentado.

```
✅ Validaciones de usuarios
✅ Validaciones de películas
✅ Validaciones de funciones
✅ Timer de 5 minutos
✅ Liberación automática de asientos
✅ Mensajes de error en español
✅ Documentación completa
```

**¡A disfrutar el proyecto!** 🎬🍿

---

**Última actualización**: 30 de Octubre, 2025
**Tiempo de lectura**: 5 minutos
**Estado**: ✅ Ready to Go
