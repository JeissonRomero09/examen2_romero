-- ================================================================
--  Script MySQL — examen2_romero
--
--  Estrategia de modelado: Relacional (No herencia)
--  Tipo de relación: Propietario → Inmueble (1 : N)
--
--  Este script define la estructura completa de la base de datos
--  y los datos de prueba del sistema de gestión inmobiliaria.
--
--  Mapeo clase → tabla:
--
--  ┌────────────────────────────────────────────────────────────┐
--  │  Clase Java        │  Tabla MySQL    │  Relación          │
--  ├────────────────────────────────────────────────────────────┤
--  │  Propietario       │  propietario    │  —                 │
--  │  Inmueble          │  inmueble       │  N → 1 propietario │
--  └────────────────────────────────────────────────────────────┘
--
--  Relaciones:
--    inmueble.propietario_id → propietario.id   (@ManyToOne * → 1)
-- ================================================================

CREATE DATABASE IF NOT EXISTS examen2_romero
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE examen2_romero;

-- ================================================================
-- TABLA 1 — propietario
-- Clase: Propietario
-- @Entity @Table(name = "propietario")
-- ================================================================
CREATE TABLE IF NOT EXISTS propietario (

    id      VARCHAR(20)  NOT NULL COMMENT '@Id',
    nombre  VARCHAR(100) NOT NULL COMMENT '@Column',

    PRIMARY KEY (id)

) ENGINE = InnoDB
  COMMENT = '@Entity Propietario';

-- ================================================================
-- TABLA 2 — inmueble
-- Clase: Inmueble
-- @Entity @Table(name = "inmueble")
-- ================================================================
CREATE TABLE IF NOT EXISTS inmueble (

    numero         VARCHAR(20) NOT NULL COMMENT '@Id',
    fechaCompra    VARCHAR(50) COMMENT '@Column',
    estado         TINYINT(1)  DEFAULT 1 COMMENT '@Column (1 activo / 0 inactivo)',

    tipo           VARCHAR(20) COMMENT '@Column (Casa / Apartamento)',
    numero_pisos   INT COMMENT '@Column (solo casas)',
    numero_piso    INT COMMENT '@Column (solo apartamentos)',

    propietario_id VARCHAR(20) NOT NULL COMMENT '@ManyToOne → propietario.id',

    PRIMARY KEY (numero),

    CONSTRAINT fk_inmueble_propietario
        FOREIGN KEY (propietario_id)
        REFERENCES propietario(id)
        ON UPDATE CASCADE
        ON DELETE RESTRICT

) ENGINE = InnoDB
  COMMENT = '@Entity Inmueble';

-- ================================================================
-- DATOS DE PRUEBA — PROPIETARIOS
-- ================================================================
INSERT INTO propietario (id, nombre) VALUES
('P001', 'Juan David Romero'),
('P002', 'Maria Fernanda Lopez'),
('14', 'JEISON'),
('4080', 'jeisson romero');

-- ================================================================
-- DATOS DE PRUEBA — INMUEBLES
-- ================================================================
INSERT INTO inmueble (numero, fechaCompra, estado, propietario_id, tipo, numero_pisos, numero_piso) VALUES

('I001', '2023-02-10', 1, 'P001', 'Casa', 2, NULL),
('I002', '2022-07-18', 1, 'P002', 'Casa', 3, NULL),
('I004', '2024-01-15', 1, 'P001', 'Apartamento', NULL, 5),
('I005', '2023-09-22', 0, 'P002', 'Apartamento', NULL, 8),

('12', '2026-04-08', 1, '14', 'Casa', NULL, NULL),
('13', '2026-04-07', 1, '4080', 'Casa', NULL, NULL);
