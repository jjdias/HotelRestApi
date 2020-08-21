CREATE TABLE IF NOT EXISTS client (
    id UUID NOT NULL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    cpf VARCHAR(11) NOT NULL,
    telefone VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS checkin (
     id UUID NOT NULL PRIMARY KEY,
     clientId UUID NOT NULL REFERENCES client (id),
     entrada DATE NOT NULL,
     saida DATE,
     adicionalVeiculo BOOLEAN NOT NULL
 );

ALTER TABLE checkin
    DROP CONSTRAINT checkin_clientid_fkey;

ALTER TABLE checkin
    ADD CONSTRAINT checkin_clientid_fkey
    FOREIGN KEY (clientId)
    REFERENCES client(id)
    ON DELETE CASCADE;

