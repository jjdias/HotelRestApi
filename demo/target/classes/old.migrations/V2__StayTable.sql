CREATE TABLE IF NOT EXISTS checkin (
    id UUID NOT NULL PRIMARY KEY,
    clientId UUID NOT NULL REFERENCES client (id),
    entrada DATE NOT NULL,
    saida DATE NOT NULL,
    adicionalVeiculo BOOLEAN NOT NULL
);

ALTER TABLE client
    ADD COLUMN IF NOT EXISTS telefone VARCHAR(20)