# Black Wolf Cinema - Sistema de Gestión de Cine

Una aplicación web desarrollada con **Spring Boot 3.2.0** para gestión de cines, compra de boletos y administración de películas.

## 📋 Requisitos previos

- **Java 17+** (JDK)
- **Maven 3.9+** (incluido en el proyecto con wrapper)
- **MySQL 5.7+** (o XAMPP con MySQL)
- **MySQL Connector J** (incluido en dependencias)

## 🚀 Instalación y Ejecución

### 1. Preparar la Base de Datos

Asegúrate de que MySQL esté corriendo. Si usas XAMPP:
- Inicia Apache y MySQL desde el panel de control de XAMPP
- O accede a phpMyAdmin en `http://localhost/phpmyadmin`

La base de datos debe existir con el nombre `black_wolf_cinema`. Si no existe, créala:

```sql
CREATE DATABASE IF NOT EXISTS black_wolf_cinema CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

**Credenciales por defecto (XAMPP):**
- Usuario: `root`
- Contraseña: (vacío)
- Host: `localhost:3306`

Si usas otras credenciales, modifica `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/black_wolf_cinema
spring.datasource.username=root
spring.datasource.password=tupassword
```

### 2. Compilar el Proyecto

Abre PowerShell en la raíz del proyecto y ejecuta:

```powershell
.\mvnw.cmd -DskipTests package
```

Esto descargará todas las dependencias (primera vez tarda más) y generará el JAR en `target/blackWolfCinema-0.0.1-SNAPSHOT.jar`.

### 3. Ejecutar la Aplicación

**Opción A: Usando el JAR empaquetado (recomendado)**

```powershell
java -jar .\target\blackWolfCinema-0.0.1-SNAPSHOT.jar --server.port=8081
```

**Opción B: Usando Maven wrapper en modo desarrollo**

```powershell
.\mvnw.cmd spring-boot:run
```

El servidor iniciará en **http://localhost:8081** (o 8080 si usas la opción B y el puerto está disponible).

### 4. Acceder a la Aplicación

Una vez que veas en consola:
```
Tomcat started on port 8081 (http) with context path ''
```

Abre tu navegador y accede a:

- **Login / Registro**: http://localhost:8081/loginPage
- **Cartelera**: http://localhost:8081/ (requiere iniciar sesión)
- **Panel de Admin**: http://localhost:8081/admin/billboard (requiere rol ADMIN)

### Credenciales de Prueba

- **Usuario**: `test`
- **Contraseña**: `test`

O regístrate creando un nuevo usuario en http://localhost:8081/register/showRegistrationForm

## 📁 Estructura del Proyecto

```
BlackWolfCinema/
├── src/main/
│   ├── java/
│   │   └── com/uady/blackWolfCinema/
│   │       ├── controller/      # Controladores REST y MVC
│   │       ├── dao/             # Acceso a datos (DAO)
│   │       ├── model/           # Entidades JPA
│   │       ├── service/         # Lógica de negocio
│   │       ├── security/        # Configuración de Spring Security
│   │       └── validation/      # Validaciones
│   └── resources/
│       ├── application.properties  # Configuración
│       ├── static/              # CSS, JS, imágenes
│       └── templates/           # Vistas Thymeleaf (HTML)
├── pom.xml                      # Dependencias Maven
└── mvnw.cmd                     # Maven wrapper para Windows
```

## 🔧 Configuración

### Puerto de la Aplicación

Para cambiar el puerto (por defecto 8081):

```powershell
java -jar .\target\blackWolfCinema-0.0.1-SNAPSHOT.jar --server.port=9000
```

O modifica en `src/main/resources/application.properties`:

```properties
server.port=9000
```

### Carpeta de Almacenamiento de Archivos

Las imágenes de películas se guardan en la carpeta especificada en `application.properties`:

```properties
storage.location=assets
```

Asegúrate de que la carpeta `assets/` exista en la raíz del proyecto.

### Habilitar/Deshabilitar Auto-recreación de Tablas

En `application.properties`:

```properties
# Crear/actualizar tablas automáticamente
spring.jpa.hibernate.ddl-auto=update

# Otras opciones:
# create-drop : Crea y elimina al parar (solo pruebas)
# create     : Solo crea
# update     : Actualiza sin eliminar datos
# validate   : Valida sin cambiar nada
```

## 🗄️ Base de Datos

La aplicación usa **Hibernate JPA** para mapeo objeto-relacional. Las tablas se crean automáticamente al iniciar (según `ddl-auto`).

**Tablas principales:**
- `users` — Usuarios del sistema
- `role` — Roles (ADMIN, CUSTOMER)
- `movie` — Películas
- `show` — Funciones/horarios
- `cinema_room` — Salas de cine
- `ticket` — Boletos
- `receipt` — Recibos/comprobantes

## 🛡️ Seguridad

La aplicación usa **Spring Security** con:
- Autenticación por usuario/contraseña
- Control de acceso por roles (ADMIN, CUSTOMER)
- Protección CSRF
- Rutas públicas: `/loginPage`, `/register/**`, `/css/**`, `/js/**`, `/img/**`

## 📝 Logs y Debugging

Los logs se muestran en consola. Para más detalle, añade a `application.properties`:

```properties
logging.level.root=INFO
logging.level.com.uady.blackWolfCinema=DEBUG
logging.level.org.springframework.web=DEBUG
```

## ❌ Solución de Problemas

### Puerto 8081 ya en uso

- Usa otro puerto: `--server.port=8082`
- O mata el proceso: `netstat -aon | findstr :8081` luego `taskkill /PID <PID> /F`

### Conexión a BD rechazada

- Verifica que MySQL esté corriendo
- Comprueba credenciales en `application.properties`
- En XAMPP: usuario `root` sin contraseña es el default

### Tablas no se crean

- Asegúrate de `spring.jpa.hibernate.ddl-auto=update` en `application.properties`
- Verifica que la BD exista

### Imágenes de películas no cargan

- Verifica que carpeta `assets/` exista en la raíz
- Confirma que `storage.location=assets` en `application.properties`

## 🚀 Deploy a Producción

Para deployar:

1. Cambia `spring.jpa.hibernate.ddl-auto=validate` (no modifiques BD automáticamente)
2. Usa una base de datos externa (no local)
3. Configura contraseña fuerte para usuarios BD
4. Genera JAR y despliega en servidor

```powershell
java -jar blackWolfCinema-0.0.1-SNAPSHOT.jar
```

## 📞 Contacto y Soporte

Proyecto desarrollado como demo de Spring Boot 3.2.0 con MySQL.

---

**Última actualización:** Octubre 20, 2025
