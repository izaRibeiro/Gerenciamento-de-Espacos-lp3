-- MySQL Script generated by MySQL Workbench
-- Thu Nov  1 00:07:02 2018
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema bancoisport
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema bancoisport
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `bancoisport` DEFAULT CHARACTER SET utf8 ;
USE `bancoisport` ;

-- -----------------------------------------------------
-- Table `bancoisport`.`cliente`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bancoisport`.`cliente` ;

CREATE TABLE IF NOT EXISTS `bancoisport`.`cliente` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NULL,
  `sobrenome` VARCHAR(45) NULL,
  `data_nascimento` VARCHAR(45) NULL,
  `email` VARCHAR(45) NULL,
  `cpf` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bancoisport`.`modalidade_predominante`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bancoisport`.`modalidade_predominante` ;

CREATE TABLE IF NOT EXISTS `bancoisport`.`modalidade_predominante` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `modalidade_predominante` VARCHAR(45) NULL,
  `descricao` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bancoisport`.`tipo_espaco`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bancoisport`.`tipo_espaco` ;

CREATE TABLE IF NOT EXISTS `bancoisport`.`tipo_espaco` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NULL,
  `modalidade_predominante_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_tipos_de_espacos_modalidade_predominante1_idx` (`modalidade_predominante_id` ASC),
  CONSTRAINT `fk_tipos_de_espacos_modalidade_predominante1`
    FOREIGN KEY (`modalidade_predominante_id`)
    REFERENCES `bancoisport`.`modalidade_predominante` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bancoisport`.`espaco`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bancoisport`.`espaco` ;

CREATE TABLE IF NOT EXISTS `bancoisport`.`espaco` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NULL,
  `cnpj` VARCHAR(45) NULL,
  `cep` VARCHAR(45) NULL,
  `logradouro` VARCHAR(45) NULL,
  `numero` VARCHAR(45) NULL,
  `complemento` VARCHAR(45) NULL,
  `bairro` VARCHAR(45) NULL,
  `cidade` VARCHAR(45) NULL,
  `uf` VARCHAR(45) NULL,
  `area` VARCHAR(45) NULL,
  `quantidade_pessoas` VARCHAR(45) NULL,
  `hora_funcionamento_inicio` VARCHAR(10) NULL,
  `hora_funcionamento_final` VARCHAR(10) NULL,
  `tipo_espaco_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_espaco_tipo_espaco1_idx` (`tipo_espaco_id` ASC),
  CONSTRAINT `fk_espaco_tipo_espaco1`
    FOREIGN KEY (`tipo_espaco_id`)
    REFERENCES `bancoisport`.`tipo_espaco` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bancoisport`.`reserva`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bancoisport`.`reserva` ;

CREATE TABLE IF NOT EXISTS `bancoisport`.`reserva` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `data_reserva` VARCHAR(10) NULL,
  `hora_inicio` VARCHAR(10) NULL,
  `hora_fim` VARCHAR(10) NULL,
  `quantidade_pessoas` VARCHAR(45) NULL,
  `valor_espaco` DOUBLE NULL,
  `nota_avaliacao` VARCHAR(45) NULL,
  `espaco_id` INT NOT NULL,
  `cliente_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_reservas_espaco1_idx` (`espaco_id` ASC),
  INDEX `fk_reservas_cliente1_idx` (`cliente_id` ASC),
  CONSTRAINT `fk_reservas_espaco1`
    FOREIGN KEY (`espaco_id`)
    REFERENCES `bancoisport`.`espaco` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_reservas_cliente1`
    FOREIGN KEY (`cliente_id`)
    REFERENCES `bancoisport`.`cliente` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bancoisport`.`pagamento`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bancoisport`.`pagamento` ;

CREATE TABLE IF NOT EXISTS `bancoisport`.`pagamento` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `vencimento` VARCHAR(10) NULL,
  `numero_codigo_barras` VARCHAR(45) NULL,
  `valor_total` DOUBLE NULL,
  `reserva_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_pagamento_reserva1_idx` (`reserva_id` ASC),
  CONSTRAINT `fk_pagamento_reserva1`
    FOREIGN KEY (`reserva_id`)
    REFERENCES `bancoisport`.`reserva` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bancoisport`.`reembolso`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bancoisport`.`reembolso` ;

CREATE TABLE IF NOT EXISTS `bancoisport`.`reembolso` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `estado` VARCHAR(45) NULL,
  `pagamento_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_reembolso_pagamento1_idx` (`pagamento_id` ASC),
  CONSTRAINT `fk_reembolso_pagamento1`
    FOREIGN KEY (`pagamento_id`)
    REFERENCES `bancoisport`.`pagamento` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bancoisport`.`irregularidade`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bancoisport`.`irregularidade` ;

CREATE TABLE IF NOT EXISTS `bancoisport`.`irregularidade` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `autor` VARCHAR(45) NULL,
  `descricao` VARCHAR(45) NULL,
  `espaco_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_denuncia_espaco1_idx` (`espaco_id` ASC),
  CONSTRAINT `fk_denuncia_espaco1`
    FOREIGN KEY (`espaco_id`)
    REFERENCES `bancoisport`.`espaco` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bancoisport`.`contato`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bancoisport`.`contato` ;

CREATE TABLE IF NOT EXISTS `bancoisport`.`contato` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `numero` INT NULL,
  `cliente_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_telefone_cliente1_idx` (`cliente_id` ASC),
  CONSTRAINT `fk_telefone_cliente1`
    FOREIGN KEY (`cliente_id`)
    REFERENCES `bancoisport`.`cliente` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bancoisport`.`disponibilidade`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bancoisport`.`disponibilidade` ;

CREATE TABLE IF NOT EXISTS `bancoisport`.`disponibilidade` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `data_disponivel` VARCHAR(45) NULL,
  `hora_disponivel` VARCHAR(45) NULL,
  `espaco_id` INT NOT NULL,
  `hora_inicio` VARCHAR(45) NULL,
  `hora_fim` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_disponibilidade_espaco1_idx` (`espaco_id` ASC),
  CONSTRAINT `fk_disponibilidade_espaco1`
    FOREIGN KEY (`espaco_id`)
    REFERENCES `bancoisport`.`espaco` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bancoisport`.`cartao`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bancoisport`.`cartao` ;

CREATE TABLE IF NOT EXISTS `bancoisport`.`cartao` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `bandeira` VARCHAR(45) NULL,
  `validade` VARCHAR(10) NULL,
  `numero` INT NULL,
  `codigo_seguranca` INT NULL,
  `cliente_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_cartoes_cliente1_idx` (`cliente_id` ASC),
  CONSTRAINT `fk_cartoes_cliente1`
    FOREIGN KEY (`cliente_id`)
    REFERENCES `bancoisport`.`cliente` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bancoisport`.`administrador`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bancoisport`.`administrador` ;

CREATE TABLE IF NOT EXISTS `bancoisport`.`administrador` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NULL,
  `email` VARCHAR(45) NULL,
  `senha` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;