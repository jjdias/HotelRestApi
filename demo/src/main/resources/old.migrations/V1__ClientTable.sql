CREATE TABLE IF NOT EXISTS client (
    id UUID NOT NULL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    cpf VARCHAR(11) NOT NULL
);

ALTER TABLE checkin DROP CONSTRAINT checkin_clientid_fkey ;
ALTER TABLE checkin ADD CONSTRAINT checkin_clientid_fkey FOREIGN KEY (clientId) REFERENCES client(id) ON DELETE
 CASCADE;