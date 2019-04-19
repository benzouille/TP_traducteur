
CREATE SEQUENCE public.langue_id_langue_seq;

CREATE TABLE public.Langue (
                ID_langue INTEGER NOT NULL DEFAULT nextval('public.langue_id_langue_seq'),
                langue VARCHAR(50) NOT NULL,
                CONSTRAINT langue_pk PRIMARY KEY (ID_langue)
);


ALTER SEQUENCE public.langue_id_langue_seq OWNED BY public.Langue.ID_langue;

CREATE SEQUENCE public.traduction_id_titre_seq;

CREATE TABLE public.Traduction (
                ID_traduction INTEGER NOT NULL DEFAULT nextval('public.traduction_id_titre_seq'),
                titre VARCHAR(100) NOT NULL,
                ID_langueOrigine INTEGER NOT NULL,
                ID_langueTraduite INTEGER NOT NULL,
                CONSTRAINT traduction_pk PRIMARY KEY (ID_traduction)
);


ALTER SEQUENCE public.traduction_id_titre_seq OWNED BY public.Traduction.ID_traduction;

CREATE SEQUENCE public.blocktrad_id_traduction_seq;

CREATE TABLE public.BlockTrad (
                ID_blockTrad INTEGER NOT NULL DEFAULT nextval('public.blocktrad_id_traduction_seq'),
                ID_traduction INTEGER NOT NULL,
                numligne INTEGER NOT NULL,
                temps VARCHAR(30) NOT NULL,
                ligneOrigine_1 VARCHAR(100) NOT NULL,
                ligneOrigine_2 VARCHAR(100),
                ligneTraduite_1 VARCHAR(100) NOT NULL,
                ligneTraduite_2 VARCHAR(100),
                CONSTRAINT blocktrad_pk PRIMARY KEY (ID_blockTrad, ID_traduction)
);


ALTER SEQUENCE public.blocktrad_id_traduction_seq OWNED BY public.BlockTrad.ID_blockTrad;

ALTER TABLE public.Traduction ADD CONSTRAINT langue_traduction_fk
FOREIGN KEY (ID_langueOrigine)
REFERENCES public.Langue (ID_langue)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Traduction ADD CONSTRAINT langue_traduction_fk1
FOREIGN KEY (ID_langueTraduite)
REFERENCES public.Langue (ID_langue)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.BlockTrad ADD CONSTRAINT traduction_blocktrad_fk
FOREIGN KEY (ID_traduction)
REFERENCES public.Traduction (ID_traduction)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;
