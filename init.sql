-- Script de inicialização do banco de dados
USE singer_db;

-- Criação da tabela de papéis
CREATE TABLE IF NOT EXISTS tbl_role (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE
);

-- Criação da tabela de usuários
CREATE TABLE IF NOT EXISTS tbl_user (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    enabled BOOLEAN DEFAULT TRUE,
    role_id BIGINT,
    FOREIGN KEY (role_id) REFERENCES tbl_role(id)
);

-- Criação da tabela de cantores
CREATE TABLE IF NOT EXISTS singer (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    genre VARCHAR(50) NOT NULL,
    birth_date DATE NOT NULL,
    nationality VARCHAR(50) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Os papéis e usuários serão criados automaticamente pelo DataInitializer Spring Boot

-- Inserção de alguns cantores de exemplo
INSERT INTO singer (name, genre, birth_date, nationality) VALUES 
('Roberto Carlos', 'MPB', '1941-04-19', 'Brasileiro'),
('Caetano Veloso', 'MPB', '1942-08-07', 'Brasileiro'),
('Gilberto Gil', 'MPB', '1942-06-26', 'Brasileiro'),
('Maria Bethânia', 'MPB', '1946-06-18', 'Brasileiro'),
('Gal Costa', 'MPB', '1945-09-26', 'Brasileiro');