ALTER TABLE pacientes ADD ativo tinyint;
UPDATE pacientes set ativo = 1;
ALTER TABLE pacientes MODIFY ativo tinyint NOT NULL;
