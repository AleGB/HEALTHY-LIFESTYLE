BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS `usuario` (
	`idUsr`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
	`nmbUsr`	TEXT NOT NULL UNIQUE,
	`correo`	TEXT NOT NULL UNIQUE,
	`edad`	INTEGER NOT NULL,
	`contraseña`	TEXT NOT NULL,
	`calDesa`	NUMERIC NOT NULL,
	`calCola`	NUMERIC NOT NULL,
	`calComida`	NUMERIC NOT NULL,
	`calCena`	NUMERIC NOT NULL
);
CREATE TABLE IF NOT EXISTS `tipoPer` (
	`idTipo`	integer NOT NULL PRIMARY KEY AUTOINCREMENT,
	`tipoAct`	text NOT NULL
);
CREATE TABLE IF NOT EXISTS `relAliPer` (
	`idUsr2`	integer NOT NULL,
	`idAli1`	integer NOT NULL,
	FOREIGN KEY(`idUsr2`) REFERENCES `usuario`(`idUsr`),
	FOREIGN KEY(`idAli1`) REFERENCES `alimentos`(`idAli`)
);
CREATE TABLE IF NOT EXISTS `relActUsr` (
	`idUsr3`	integer NOT NULL,
	`idTipo1`	integer NOT NULL,
	FOREIGN KEY(`idUsr3`) REFERENCES `usuario`(`idUsr`),
	FOREIGN KEY(`idTipo1`) REFERENCES `tipoPer`(`idTipo`)
);
CREATE TABLE IF NOT EXISTS `datosAlimenticios` (
	`idDat`	integer NOT NULL PRIMARY KEY AUTOINCREMENT,
	`IMC`	numeric NOT NULL,
	`fecha`	date NOT NULL,
	`idUsr1`	int NOT NULL,
	FOREIGN KEY(`idUsr1`) REFERENCES `usuario`(`idUsr`)
);
CREATE TABLE IF NOT EXISTS `alimentos` (
	`idAli`	integer NOT NULL PRIMARY KEY AUTOINCREMENT,
	`nombre`	text NOT NULL,
	`calorias`	numeric NOT NULL
);
COMMIT;
