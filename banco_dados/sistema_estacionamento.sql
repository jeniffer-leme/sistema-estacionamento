DROP DATABASE sistema_estacionamento;
CREATE DATABASE IF NOT EXISTS sistema_estacionamento;
USE sistema_estacionamento;

CREATE TABLE usuario (
	id INT AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
	senha VARCHAR(128) NOT NULL,
	perfil	ENUM('ADMIN','OPERADOR') NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE cliente (
	id INT AUTO_INCREMENT,
	nome VARCHAR(100) NOT NULL,
	cpf	VARCHAR(11)	NOT NULL UNIQUE,
	telefone VARCHAR(15) NOT NULL,
	email VARCHAR(100),
	PRIMARY KEY(id)
);

CREATE TABLE veiculo (
	id INT AUTO_INCREMENT,
	placa VARCHAR(7) NOT NULL UNIQUE,
	modelo VARCHAR(60)	NOT NULL,
	cor	VARCHAR(30)	NOT NULL,
	tipo ENUM('CARRO','MOTO','CAMINHAO') NOT NULL,
	cliente_id	INT	NOT NULL,
	PRIMARY KEY(id),
    FOREIGN KEY(cliente_id) REFERENCES cliente(id)
);

CREATE TABLE vaga (
	id INT AUTO_INCREMENT,
	numero VARCHAR(10) NOT NULL UNIQUE,
	setor VARCHAR(30) NOT NULL,
	tipo ENUM('CARRO','MOTO','CAMINHAO','PCD') NOT NULL,
	status ENUM('LIVRE','OCUPADA','MANUTENCAO')  NOT NULL DEFAULT 'LIVRE',
	PRIMARY KEY(id)
);

CREATE TABLE movimentacao (
	id INT AUTO_INCREMENT,
	veiculo_id INT NOT NULL,
	vaga_id	INT	NOT NULL,
	usuario_id	INT NOT NULL,
	data_entrada DATETIME NOT NULL,
	data_saida DATETIME	NULL,
	valor DECIMAL(10,2)	NULL,
    PRIMARY KEY(id),
    FOREIGN KEY(veiculo_id) REFERENCES veiculo(id),
    FOREIGN KEY(vaga_id) REFERENCES vaga(id),
    FOREIGN KEY(usuario_id) REFERENCES usuario(id)
);









