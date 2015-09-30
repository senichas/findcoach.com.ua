CREATE TABLE "user"
(
  user_id serial NOT NULL,
  first_name character varying(255) NOT NULL,
  last_name character varying(255) NOT NULL,
  email character varying(255) NOT NULL,
  is_padawan integer DEFAULT 0,
  is_coach integer DEFAULT 0,
  is_active boolean NOT NULL DEFAULT false,
  CONSTRAINT user_id_pk PRIMARY KEY (user_id),
  CONSTRAINT user_email_uc UNIQUE (email)
)