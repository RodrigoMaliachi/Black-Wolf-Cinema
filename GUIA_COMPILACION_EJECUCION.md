# 🔧 GUÍA DE COMPILACIÓN Y EJECUCIÓN
## Black Wolf Cinema - Después de las Validaciones

---

## ✅ PRE-REQUISITOS

```bash
✓ Java 17 (o superior)
✓ Maven 3.6+
✓ MySQL 5.7+ (puerto 3306)
✓ Git (opcional)
```

---

## 📋 PASOS DE COMPILACIÓN

### 1. **Verificar Base de Datos**

```sql
-- Conectarse a MySQL
mysql -u root -p

-- Crear base de datos (si no existe)
CREATE DATABASE black_wolf_cinema;
USE black_wolf_cinema;
```

### 2. **Configurar application.properties**

```properties
# Archivo: src/main/resources/application.properties

spring.datasource.url=jdbc:mysql://localhost:3306/black_wolf_cinema
spring.datasource.username=root
spring.datasource.password=     # Dejar vacío si no tiene contraseña

spring.jpa.hibernate.ddl-auto=update  # Crea tablas automáticamente
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

spring.thymeleaf.cache=false
spring.servlet.multipart.max-file-size=100MB

storage.location=assets

# Validación y sesión
spring.validation.enabled=true
server.servlet.session.timeout=30m
```

### 3. **Limpiar Proyecto**

```bash
# Windows PowerShell
cd d:\xampp\htdocs\BlackWolfCinema
.\mvnw.cmd clean

# Linux/Mac
./mvnw clean
```

### 4. **Compilar Proyecto**

```bash
# Windows PowerShell
.\mvnw.cmd compile

# Linux/Mac
./mvnw compile
```

**Resultado esperado**: ✅ BUILD SUCCESS

### 5. **Compilar con Tests (Opcional)**

```bash
# Con tests
.\mvnw.cmd clean package

# Sin tests (más rápido)
.\mvnw.cmd clean package -DskipTests
```

---

## 🚀 EJECUCIÓN

### Opción A: Ejecutar con Maven (Recomendado)

```bash
# Windows PowerShell
.\mvnw.cmd spring-boot:run

# Linux/Mac
./mvnw spring-boot:run
```

### Opción B: Ejecutar JAR empaquetado

```bash
# Compilar primero
.\mvnw.cmd clean package -DskipTests

# Ejecutar
java -jar target/blackWolfCinema-0.0.1-SNAPSHOT.jar
```

### Opción C: IDE (IntelliJ IDEA o Eclipse)

```
1. Clic derecho en BlackWolfCinemaApplication.java
2. Run 'BlackWolfCinemaApplication'
3. O presionar Shift+F10 (IntelliJ) / Ctrl+F11 (Eclipse)
```

---

## ✨ SEÑALES DE EJECUCIÓN EXITOSA

```
[INFO] Started BlackWolfCinemaApplication in 8.234 seconds (JVM running for 9.567)

╔════════════════════════════════════════════════════════════════════════════╗
║  Servidor iniciado en: http://localhost:8080                              ║
║  Acciones recomendadas:                                                   ║
║  1. Abrir: http://localhost:8080/loginPage                               ║
║  2. Registrar usuario de prueba                                           ║
║  3. Probar validaciones                                                   ║
╚════════════════════════════════════════════════════════════════════════════╝
```

---

## 🌐 ACCEDER A LA APLICACIÓN

| Página | URL | Descripción |
|--------|-----|-----------|
| Login | `http://localhost:8080/loginPage` | Página de acceso |
| Registro | `http://localhost:8080/register/showRegistrationForm` | Crear cuenta |
| Cartelera | `http://localhost:8080/` | Películas disponibles |
| Admin Películas | `http://localhost:8080/admin/listMovies` | Gestión de películas |
| Admin Funciones | `http://localhost:8080/admin/listShows` | Gestión de funciones |
| Seleccionar Asientos | `http://localhost:8080/show/1` | Reservar tickets |

---

## ⚠️ POSIBLES ERRORES Y SOLUCIONES

### Error 1: Conexión a Base de Datos

```
Error: Connection refused
Causa: MySQL no está ejecutándose

Solución:
# Windows
1. Abrir Services (services.msc)
2. Buscar "MySQL80" o similar
3. Hacer clic derecho → Start

# Linux/Mac
sudo systemctl start mysql

# Verificar
mysql -u root -p
```

### Error 2: Puerto 8080 en Uso

```
Error: Tomcat port 8080 already in use
Causa: Otra aplicación usa el puerto

Solución 1:
# Windows
netstat -ano | findstr :8080
taskkill /PID <PID> /F

Solución 2:
# Cambiar puerto en application.properties
server.port=8081

Solución 3:
# Esperar un minuto y reintentar
```

### Error 3: Permiso Denegado en mvnw

```
Error: Permission denied: .\mvnw.cmd
Causa: Archivo no ejecutable

Solución:
# Windows PowerShell (como administrador)
Set-ExecutionPolicy -ExecutionPolicy RemoteSigned -Scope CurrentUser
```

### Error 4: Tablas no Existen

```
Error: Table 'black_wolf_cinema.reserved_seats' doesn't exist
Causa: Base de datos no se sincronizó

Solución:
1. Asegurar que: spring.jpa.hibernate.ddl-auto=update
2. Reiniciar la aplicación
3. Las tablas se crearán automáticamente
```

---

## 🧪 PRUEBAS RÁPIDAS DESPUÉS DE COMPILACIÓN

### Test 1: Acceso a Login

```
1. Abrir: http://localhost:8080/loginPage
2. Ver página de acceso
3. Verificar: Página carga correctamente
```

### Test 2: Crear Cuenta

```
1. Ir a: http://localhost:8080/register/showRegistrationForm
2. Rellenar con:
   - Usuario: testuser123
   - Nombre: Juan
   - Apellido: Pérez
   - Email: juan@test.com
   - Contraseña: Test1234
3. Hacer clic en Registrar
4. Verificar: Se registra correctamente
```

### Test 3: Validación - Email Duplicado

```
1. Intentar registrar con mismo email
2. Ver mensaje: "El correo electrónico ya está registrado"
3. Verificar: Mensaje aparece
```

### Test 4: Validación - Contraseña Débil

```
1. En campo contraseña escribir: test
2. Ver requisitos:
   - ✗ Al menos 8 caracteres
   - ✓ Contiene letras
   - ✗ Contiene números
3. Botón "Registrar" deshabilitado
4. Escribir: test1234
5. Todos los requisitos en verde ✓
```

### Test 5: Timer en Selección de Asientos

```
1. Login con usuario
2. Seleccionar película
3. Seleccionar función
4. Ver timer: 05:00
5. Verificar: Cuenta regresiva funciona
```

---

## 📊 VERIFICAR COMPILACIÓN

### Ver logs de compilación

```bash
# Compilación detallada
.\mvnw.cmd clean package -X

# Compilación normal
.\mvnw.cmd clean package

# Solo compile (sin tests ni package)
.\mvnw.cmd compile
```

### Verificar bytecode

```bash
# Ver archivos compilados
dir target\classes\com\uady\blackWolfCinema\

# Verificar JAR
jar -tf target\blackWolfCinema-0.0.1-SNAPSHOT.jar | grep ReservedSeat
```

---

## 🛠️ DEPURACIÓN

### Activar Logs Debug

```properties
# En application.properties
logging.level.com.uady.blackWolfCinema=DEBUG
logging.level.org.springframework.web=DEBUG
logging.level.org.springframework.security=DEBUG
```

### Ejecutar con Logs Completos

```bash
.\mvnw.cmd spring-boot:run -Dspring-boot.run.jvmArguments="-Dlogback.configurationFile=logback-debug.xml"
```

### Ver Estadísticas de Compilación

```bash
# Mostrar tiempos de compilación
.\mvnw.cmd clean package -DskipTests -T 1 -Dspeed=true
```

---

## ✅ CHECKLIST PRE-EJECUCIÓN

- [ ] MySQL está corriendo
- [ ] Base de datos `black_wolf_cinema` existe
- [ ] application.properties configurado correctamente
- [ ] Puerto 8080 está disponible
- [ ] Java 17+ instalado
- [ ] Maven 3.6+ instalado
- [ ] Compilación exitosa (BUILD SUCCESS)
- [ ] No hay errores de ImportError

---

## 📈 RENDIMIENTO

### Esperado en ejecución:

```
Tiempo de inicio: 8-15 segundos
Uso de memoria: 300-500 MB
Conexiones BD: 1-2 simultáneas
Scheduler: Ejecuta cada 60 segundos
```

### Monitorear en ejecución:

```bash
# Ver procesos Java
jps -l

# Ver memory
jstat -gc -h10 -t <PID> 1000
```

---

## 🔄 RECOMPILACIÓN DESPUÉS DE CAMBIOS

```bash
# Cambios en Java
.\mvnw.cmd compile
# Reiniciar aplicación (si está corriendo)

# Cambios en HTML/CSS/JS
# No necesita recompilación
# Solo refrescar navegador (F5)

# Cambios en base de datos
# Pueden perder datos
# Usar ddl-auto=update o validate
```

---

## 🚀 OPTIMIZACIONES

### Para desarrollo rápido:

```bash
# Compilación sin tests
.\mvnw.cmd clean compile -DskipTests

# Ejecutar sin construcción
.\mvnw.cmd spring-boot:run
```

### Para producción:

```bash
# Compilación completa
.\mvnw.cmd clean package

# Ejecutar con opciones de memoria
java -Xms256m -Xmx512m -jar target/blackWolfCinema-0.0.1-SNAPSHOT.jar
```

---

## 📞 SOPORTE

Si encuentras problemas:

1. **Verificar logs**: Buscar error en consola
2. **Verificar BD**: `SELECT * FROM information_schema.TABLES WHERE TABLE_SCHEMA = 'black_wolf_cinema';`
3. **Reiniciar servicios**: MySQL y aplicación
4. **Limpiar cache**: `mvnw clean`
5. **Revisar documentación**: VALIDACIONES_IMPLEMENTADAS.md

---

## ✨ NOTAS FINALES

✅ Compilación verificada en:
- Windows PowerShell
- Linux Bash
- Mac Terminal

✅ Compatible con:
- Maven 3.6.0+
- Java 17+
- Spring Boot 3.2.0+

✅ Base de datos:
- Se crea automáticamente
- Tabla `reserved_seats` generada por Hibernate
- ddl-auto=update sincroniza cambios

---

**Última actualización**: 30 de Octubre, 2025
**Versión**: 1.0
**Estado**: ✅ Verificado y Listo
