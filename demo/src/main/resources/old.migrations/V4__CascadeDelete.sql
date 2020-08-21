ALTER TABLE checkin
   ADD CONSTRAINT FK_T1_T2_Cascade
   FOREIGN KEY (clientId) REFERENCES client(id) ON DELETE CASCADE


   SELECT  con.*
          FROM pg_catalog.pg_constraint con
               INNER JOIN pg_catalog.pg_class rel
                          ON rel.oid = con.conrelid
               INNER JOIN pg_catalog.pg_namespace nsp
                          ON nsp.oid = connamespace
          WHERE nsp.nspname = 'demodb'
                AND rel.relname = 'checkin';