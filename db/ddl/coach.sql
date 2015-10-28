CREATE TABLE coach
(
  user_id integer NOT NULL,
  status character varying(20) NOT NULL,
  alias character varying(60),
   title CHARACTER VARYING(60),
   description CHARACTER VARYING(140),
  CONSTRAINT user_id_fk FOREIGN KEY (user_id)
      REFERENCES "user" (user_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)