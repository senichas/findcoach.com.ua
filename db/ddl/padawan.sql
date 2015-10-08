﻿CREATE TABLE padawans
(
  user_id integer NOT NULL,
  user_status character varying(15) NOT NULL,
  CONSTRAINT user_id_fk FOREIGN KEY (user_id)
      REFERENCES "user" (user_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)