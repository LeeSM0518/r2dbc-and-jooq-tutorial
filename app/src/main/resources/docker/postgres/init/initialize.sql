CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
CREATE TABLE account
(
  id           UUID      DEFAULT gen_random_uuid(),
  username     VARCHAR(20) NOT NULL,
  name         VARCHAR(50),
  password     VARCHAR(60),
  email        VARCHAR(50),
  role         VARCHAR(20) NOT NULL,
  activation   BOOLEAN,
  code         VARCHAR(20),
  cell_phone   VARCHAR(18),
  reg_datetime TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  mod_datetime TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT account_pk PRIMARY KEY (id),
  CONSTRAINT account_uk_username UNIQUE (username)
);

BEGIN;
CREATE
OR REPLACE FUNCTION update_timestamp()
  RETURNS TRIGGER AS
$$
BEGIN
  NEW.mod_datetime
= now();
RETURN new;
END;
$$
LANGUAGE 'plpgsql';
COMMIT;

CREATE TRIGGER account_timestamp
  BEFORE INSERT OR
UPDATE
  on account
  FOR EACH ROW
  EXECUTE PROCEDURE update_timestamp();
COMMIT;

INSERT INTO account (username, name, password, email, role, activation, code, cell_phone)
VALUES ('admin', 'KPC Admin', '', 'admin@wisoft.space', 'ADMIN', TRUE, '', '');
