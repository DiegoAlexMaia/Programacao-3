-- Criação da tabela filmes
CREATE TABLE filmes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    ano_lancamento INT NOT NULL,
    CONSTRAINT chk_ano_positivo CHECK (ano_lancamento > 0)
);

-- Inserção de filmes reais
INSERT INTO filmes (titulo, ano_lancamento) VALUES
('O Poderoso Chefão', 1972),
('O Senhor dos Anéis: O Retorno do Rei', 2003),
('Batman: O Cavaleiro das Trevas', 2008),
('Parasita', 2019),
('Interestelar', 2014),
('Clube da Luta', 1999),
('A Origem', 2010),
('Pulp Fiction: Tempo de Violência', 1994),
('Cidade de Deus', 2002),
('Toy Story', 1995);