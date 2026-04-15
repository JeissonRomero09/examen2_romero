-- ================================================================
--  Script MySQL — examen2_romero
--
--  Estrategia de modelado: Relacional (Propietario → Inmueble)
--  Tipo de relación: 1 a N
--
--  Este script contiene la estructura de la base de datos
--  y los datos de prueba principales del sistema.
--
--  Mapeo:
--
--  ┌────────────────────────────────────────────────────────────┐
--  │  Clase Java        │  Tabla MySQL    │  Relación          │
--  ├────────────────────────────────────────────────────────────┤
--  │  Propietario       │  propietario    │  —                 │
--  │  Inmueble          │  inmueble       │  N → 1 propietario │
--  └────────────────────────────────────────────────────────────┘
-- ================================================================

CREATE DATABASE IF NOT EXISTS examen2_romero
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE examen2_romero;

-- ================================================================
-- TABLA 1 — propietario
-- ================================================================
CREATE TABLE IF NOT EXISTS propietario (

    id      VARCHAR(20)  NOT NULL,
    nombre  VARCHAR(100) NOT NULL,

    PRIMARY KEY (id)

) ENGINE=InnoDB;

-- ================================================================
-- TABLA 2 — inmueble
-- ================================================================
CREATE TABLE IF NOT EXISTS inmueble (

    numero         VARCHAR(20) NOT NULL,
    fechaCompra    VARCHAR(50),
    estado         TINYINT(1) DEFAULT 1,

    tipo           VARCHAR(20),
    numero_pisos   INT,
    numero_piso    INT,

    propietario_id VARCHAR(20),

    PRIMARY KEY (numero),

    CONSTRAINT fk_inmueble_propietario
        FOREIGN KEY (propietario_id)
        REFERENCES propietario(id)
        ON UPDATE CASCADE
        ON DELETE RESTRICT

) ENGINE=InnoDB;

-- ================================================================
-- DATOS DE PRUEBA (LIMPIOS)
-- ================================================================

-- PROPIETARIOS
INSERT INTO propietario (id, nombre) VALUES
('P001', 'Juan David Romero'),
('P002', 'Maria Fernanda Lopez');

-- INMUEBLES
INSERT INTO inmueble (numero, fechaCompra, estado, propietario_id, tipo, numero_pisos, numero_piso) VALUES
('I001', '2023-02-10', 1, 'P001', 'Casa', 2, NULL),
('I002', '2022-07-18', 1, 'P002', 'Casa', 3, NULL),
('I004', '2024-01-15', 1, 'P001', 'Apartamento', NULL, 5),
('I005', '2023-09-22', 0, 'P002', 'Apartamento', NULL, 8);
