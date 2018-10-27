-- DDL

CREATE SCHEMA `centrocultural` ;

CREATE TABLE IF NOT EXISTS `centrocultural`.`Participantes` (
  `idParticipante` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `apellido` VARCHAR(45) NOT NULL,
  `direccion` VARCHAR(45) NOT NULL,
  `telefono` VARCHAR(45) NOT NULL,
  `fechaNacimiento` DATE NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `tutor` VARCHAR(45) NULL,
  PRIMARY KEY (`idParticipante`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `centrocultural`.`Aulas` (
  `idAula` INT NOT NULL AUTO_INCREMENT,
  `numero` INT NOT NULL,
  PRIMARY KEY (`idAula`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `centrocultural`.`Tematicas` (
  `idTematica` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idTematica`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `centrocultural`.`Profesores` (
  `idProfesor` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `apellido` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idProfesor`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `centrocultural`.`Cursos` (
  `idCurso` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `duracion` VARCHAR(45) NOT NULL,
  `cargaHoraria` VARCHAR(45) NOT NULL,
  `cupo` INT NOT NULL,
  `tieneExamenFinal` TINYINT NOT NULL DEFAULT 0,
  `idAula` INT NOT NULL,
  `idProfesor` INT NOT NULL,
  `idTematica` INT NOT NULL,
  PRIMARY KEY (`idCurso`),
  INDEX `AulaCursos_idx` (`idAula` ASC) VISIBLE,
  INDEX `ProfesorCursos_idx` (`idProfesor` ASC) VISIBLE,
  INDEX `TematicaCursos_idx` (`idTematica` ASC) VISIBLE,
  CONSTRAINT `AulasCursosFK`
    FOREIGN KEY (`idAula`)
    REFERENCES `centrocultural`.`Aulas` (`idAula`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `ProfesoresCursosFK`
    FOREIGN KEY (`idProfesor`)
    REFERENCES `centrocultural`.`Profesores` (`idProfesor`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
 CONSTRAINT `TematicasCursosFK`
    FOREIGN KEY (`idTematica`)
    REFERENCES `centrocultural`.`Tematicas` (`idTematica`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `centrocultural`.`Inscripciones` (
  `idInscripcion` INT NOT NULL AUTO_INCREMENT,
  `estado` VARCHAR(11) NOT NULL,
  `fechaInscripcion` DATE NULL,
  `idParticipante` INT NOT NULL,
  `idCurso` INT NOT NULL,
  PRIMARY KEY (`idInscripcion`),
  INDEX `ParticipantesCursosFK_idx` (`idParticipante` ASC) VISIBLE,
  INDEX `CursosInscripcionesFK_idx` (`idCurso` ASC) VISIBLE,
  CONSTRAINT `ParticipantesInscripcionesFK`
    FOREIGN KEY (`idParticipante`)
    REFERENCES `centrocultural`.`Participantes` (`idParticipante`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `CursosInscripcionesFK`
    FOREIGN KEY (`idCurso`)
    REFERENCES `centrocultural`.`Cursos` (`idCurso`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- Modifico la estructura para tener una fecha por defecto, si es que no es provista
ALTER TABLE `inscripciones` MODIFY COLUMN `fechaInscripcion` TIMESTAMP NOT
NULL DEFAULT CURRENT_TIMESTAMP;

-- Trigger para establecer un estado de acuerdo a las reglas de negocio planteadas
DROP TRIGGER IF EXISTS `centrocultural`.`inscripciones_BEFORE_INSERT`;

DELIMITER $$
USE `centrocultural`$$
CREATE DEFINER = CURRENT_USER TRIGGER `centrocultural`.`inscripciones_BEFORE_INSERT` BEFORE INSERT ON `inscripciones` FOR EACH ROW
BEGIN
SELECT count(*) INTO @A from inscripciones where estado = 'ACEPTADA' AND idCurso = NEW.idCurso;
SELECT cupo INTO @B from cursos where idCurso = NEW.idCurso;
if @A >= @B THEN
  SET NEW.estado = 'CONDICIONAL';
ELSE
  SET NEW.estado = 'ACEPTADA';
END IF; 
END$$
DELIMITER ;


-- DML

INSERT INTO `centrocultural`.`Tematicas` (`nombre`) VALUES ('Manualidades'),('Literatura'),('Dibujo'),('Musica');

INSERT INTO `centrocultural`.`Profesores` (`nombre`,`apellido`) VALUES  ('Ana','Gonzales'),('Juan','Suarez'),('Maria','Sosa'),('Haruki','Murakami'), ('Paul','Auster'); 

INSERT INTO `centrocultural`.`Aulas` (`numero`) VALUES (1),(4),(6),(7),(12),(18),(21),(23),(27); 

INSERT INTO `centrocultural`.`Participantes` (`nombre`, `apellido`,`direccion`,`telefono`,`fechaNacimiento`,`email`) VALUES 
 ('Juan','Gallo','Dorrego 8411','4723125','1984-05-17','juan@gmail.com'),
 ('Marta','Salamon','Luro 1011','4789555','1985-04-16','marta@gmail.com'),
 ('Ana','Marie','Cordoba 2497','4963148','1988-09-02','ana@gmail.com'),
 ('Pedro','Lopez','San Martin 6631','4366542','1999-03-19','pedro@gmail.com');
INSERT INTO `centrocultural`.`Participantes` (`nombre`, `apellido`,`direccion`,`telefono`,`fechaNacimiento`,`email`, `tutor`) VALUES 
 ('Valentina','Gaetan','Colon 2021','4985517','2002-07-17','valentina@gmail.com', 'Javier Maresna');
 
INSERT INTO `centrocultural`.`Cursos` (`nombre`,`duracion`,`cargaHoraria`,`cupo`,`tieneExamenFinal`,`idAula`,`idProfesor`,`idTematica`) VALUES 
('Literatura Moderna','2 meses','6hs/semana',10,0,8,4,2),
('Taller Literario','6 meses','2hs/semana',2,0,1,5,2);
INSERT INTO `centrocultural`.`Cursos` (`nombre`,`duracion`,`cargaHoraria`,`cupo`,`tieneExamenFinal`,`idAula`,`idProfesor`,`idTematica`) VALUES 
('Manos a la obra','3 meses','1hs/semana',15,0,2,1,1);
INSERT INTO `centrocultural`.`Cursos` (`nombre`,`duracion`,`cargaHoraria`,`cupo`,`tieneExamenFinal`,`idAula`,`idProfesor`,`idTematica`) VALUES 
('Picasso y yo','5 meses','3hs/semana',20,1,3,2,3);
INSERT INTO `centrocultural`.`Cursos` (`nombre`,`duracion`,`cargaHoraria`,`cupo`,`tieneExamenFinal`,`idAula`,`idProfesor`,`idTematica`) VALUES 
('Bajo cuerdas','1 meses','2hs/semana',8,0,4,3,4);
