@echo off
REM ============================================
REM Black Wolf Cinema - Compilar y Ejecutar
REM ============================================

echo.
echo ╔════════════════════════════════════════════════════════════╗
echo ║  Black Wolf Cinema - Compilar y Ejecutar                   ║
echo ╚════════════════════════════════════════════════════════════╝
echo.

REM Verificar si Maven está disponible
call mvnw.cmd --version >nul 2>&1
if errorlevel 1 (
    echo ❌ Error: Maven no encontrado
    echo Por favor verifica que mvnw.cmd existe en el directorio actual
    pause
    exit /b 1
)

echo ✅ Maven encontrado
echo.

REM Limpiar y compilar
echo ⏳ Limpiando proyecto...
call mvnw.cmd clean
if errorlevel 1 (
    echo ❌ Error durante la limpieza
    pause
    exit /b 1
)

echo.
echo ⏳ Compilando proyecto...
call mvnw.cmd compile
if errorlevel 1 (
    echo ❌ Error durante la compilación
    pause
    exit /b 1
)

echo.
echo ✅ Compilación exitosa
echo.
echo 🚀 Iniciando aplicación...
echo.

REM Ejecutar la aplicación
call mvnw.cmd spring-boot:run

pause
