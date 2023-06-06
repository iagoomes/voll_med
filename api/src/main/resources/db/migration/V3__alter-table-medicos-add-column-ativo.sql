ALTER TABLE medicos ADD ativo tinyint;
UPDATE medicos set ativo = 1;
ALTER TABLE medicos MODIFY ativo tinyint NOT NULL;
