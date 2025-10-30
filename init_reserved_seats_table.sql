-- Script para crear la tabla de asientos apartados temporalmente
-- Ejecutar esto en la base de datos black_wolf_cinema

-- Tabla para rastrear asientos apartados temporalmente durante 5 minutos
CREATE TABLE IF NOT EXISTS reserved_seats (
    id INT AUTO_INCREMENT PRIMARY KEY,
    seat_number VARCHAR(10) NOT NULL,
    idshow INT NOT NULL,
    session_id VARCHAR(255),
    reserved_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    expires_at DATETIME NOT NULL,
    FOREIGN KEY (idshow) REFERENCES movie_show(idshow) ON DELETE CASCADE,
    INDEX idx_show (idshow),
    INDEX idx_expires_at (expires_at),
    INDEX idx_session_id (session_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Nota: Esta tabla será creada automáticamente por Hibernate 
-- si usas spring.jpa.hibernate.ddl-auto=update
