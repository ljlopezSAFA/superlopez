-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         10.6.4-MariaDB - mariadb.org binary distribution
-- SO del servidor:              Win64
-- HeidiSQL Versión:             11.3.0.6295
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Volcando estructura de base de datos para superlopez
CREATE DATABASE IF NOT EXISTS `superlopez` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `superlopez`;

-- Volcando estructura para tabla superlopez.cliente
CREATE TABLE IF NOT EXISTS `cliente` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(150) NOT NULL,
  `apellidos` varchar(150) NOT NULL,
  `dni` varchar(9) NOT NULL,
  `codigo_validacion` int(5) DEFAULT NULL,
  `direccion` varchar(150) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla superlopez.cliente: ~2 rows (aproximadamente)
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
REPLACE INTO `cliente` (`id`, `nombre`, `apellidos`, `dni`, `codigo_validacion`, `direccion`) VALUES
	(12, 'Pepe', 'López Sánchez', '78456123P', NULL, 'Calle Huelva 12 ');
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;

-- Volcando estructura para tabla superlopez.hibernate_sequence
CREATE TABLE IF NOT EXISTS `hibernate_sequence` (
  `next_not_cached_value` bigint(21) NOT NULL,
  `minimum_value` bigint(21) NOT NULL,
  `maximum_value` bigint(21) NOT NULL,
  `start_value` bigint(21) NOT NULL COMMENT 'start value when sequences is created or value if RESTART is used',
  `increment` bigint(21) NOT NULL COMMENT 'increment value',
  `cache_size` bigint(21) unsigned NOT NULL,
  `cycle_option` tinyint(1) unsigned NOT NULL COMMENT '0 if no cycles are allowed, 1 if the sequence should begin a new cycle when maximum_value is passed',
  `cycle_count` bigint(21) NOT NULL COMMENT 'How many cycles have been done'
) ENGINE=InnoDB SEQUENCE=1;

-- Volcando datos para la tabla superlopez.hibernate_sequence: ~1 rows (aproximadamente)
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
REPLACE INTO `hibernate_sequence` (`next_not_cached_value`, `minimum_value`, `maximum_value`, `start_value`, `increment`, `cache_size`, `cycle_option`, `cycle_count`) VALUES
	(1001, 1, 9223372036854775806, 1, 1, 1000, 0, 0);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;

-- Volcando estructura para tabla superlopez.linea_pedido
CREATE TABLE IF NOT EXISTS `linea_pedido` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `id_pedido` int(10) NOT NULL,
  `id_lote_barbacoa` int(10) NOT NULL,
  `cantidad` int(10) NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`),
  KEY `linea_pedido_pedido_fk` (`id_pedido`),
  KEY `linea_pedido_lote_fk` (`id_lote_barbacoa`),
  CONSTRAINT `linea_pedido_lote_fk` FOREIGN KEY (`id_lote_barbacoa`) REFERENCES `lote_barbacoa` (`id`),
  CONSTRAINT `linea_pedido_pedido_fk` FOREIGN KEY (`id_pedido`) REFERENCES `pedido` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla superlopez.linea_pedido: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `linea_pedido` DISABLE KEYS */;
/*!40000 ALTER TABLE `linea_pedido` ENABLE KEYS */;

-- Volcando estructura para tabla superlopez.lote_barbacoa
CREATE TABLE IF NOT EXISTS `lote_barbacoa` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `codigo` varchar(10) NOT NULL,
  `num_lote` int(10) NOT NULL,
  `apodo` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla superlopez.lote_barbacoa: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `lote_barbacoa` DISABLE KEYS */;
/*!40000 ALTER TABLE `lote_barbacoa` ENABLE KEYS */;

-- Volcando estructura para tabla superlopez.lote_producto
CREATE TABLE IF NOT EXISTS `lote_producto` (
  `id_producto` int(10) NOT NULL,
  `id_lote_barbacoa` int(10) NOT NULL,
  `unidad_medida` int(1) NOT NULL,
  `cantidad` double NOT NULL,
  `lote_barbacoa_id` int(11) NOT NULL,
  `productos_id` int(11) NOT NULL,
  KEY `lote_producto_producto_fk` (`id_producto`),
  KEY `lote_producto_lote_fk` (`id_lote_barbacoa`),
  KEY `FK6mgh2j1suh0jhfeono45obcpf` (`productos_id`),
  KEY `FKo80ppq32y3l7yv7flicqtpjla` (`lote_barbacoa_id`),
  CONSTRAINT `FK6mgh2j1suh0jhfeono45obcpf` FOREIGN KEY (`productos_id`) REFERENCES `producto` (`id`),
  CONSTRAINT `FKo80ppq32y3l7yv7flicqtpjla` FOREIGN KEY (`lote_barbacoa_id`) REFERENCES `lote_barbacoa` (`id`),
  CONSTRAINT `lote_producto_lote_fk` FOREIGN KEY (`id_lote_barbacoa`) REFERENCES `lote_barbacoa` (`id`),
  CONSTRAINT `lote_producto_producto_fk` FOREIGN KEY (`id_producto`) REFERENCES `producto` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla superlopez.lote_producto: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `lote_producto` DISABLE KEYS */;
/*!40000 ALTER TABLE `lote_producto` ENABLE KEYS */;

-- Volcando estructura para tabla superlopez.pedido
CREATE TABLE IF NOT EXISTS `pedido` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `codigo` varchar(10) NOT NULL,
  `id_cliente` int(10) NOT NULL,
  `fecha_realizacion` datetime NOT NULL,
  `fecha_entrega_deseada` datetime NOT NULL,
  `preparado` tinyint(1) NOT NULL DEFAULT 0,
  `entregado` tinyint(1) NOT NULL DEFAULT 0,
  `apodo` varchar(255) DEFAULT NULL,
  `num_lote` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `pedido_cliente_fk` (`id_cliente`),
  CONSTRAINT `pedido_cliente_fk` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla superlopez.pedido: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `pedido` DISABLE KEYS */;
/*!40000 ALTER TABLE `pedido` ENABLE KEYS */;

-- Volcando estructura para tabla superlopez.producto
CREATE TABLE IF NOT EXISTS `producto` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(150) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla superlopez.producto: ~9 rows (aproximadamente)
/*!40000 ALTER TABLE `producto` DISABLE KEYS */;
REPLACE INTO `producto` (`id`, `descripcion`) VALUES
	(1, 'Filete de Pollo'),
	(2, 'Filete de Lomo'),
	(3, 'Hamburguesas'),
	(4, 'Salchichas'),
	(5, 'Pinchitos'),
	(6, 'Presa Ibérica'),
	(10, 'Chorizo casero'),
	(12, 'Plumilla Ibérica'),
	(13, 'Secreto Ibérico');
/*!40000 ALTER TABLE `producto` ENABLE KEYS */;

-- Volcando estructura para tabla superlopez.usuario
CREATE TABLE IF NOT EXISTS `usuario` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `username` varchar(25) NOT NULL,
  `password` varchar(150) NOT NULL,
  `rol` int(1) DEFAULT 2,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla superlopez.usuario: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
REPLACE INTO `usuario` (`id`, `username`, `password`, `rol`) VALUES
	(1, 'admin', '$2a$10$mkKRz9KMBAsaLBjZxRE6SeZy1auQJKXlfdy72bGPc8NiHTLw1tgvG', 0);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
