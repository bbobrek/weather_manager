SET client_encoding TO 'UTF8';

CREATE TABLE reverse_geocoding (
	id uuid NOT NULL,
	latitude varchar(255) NULL,
	longitude varchar(255) NULL,
	city varchar(255) NULL,
	locality varchar(255) NULL,
	CONSTRAINT reverse_geocoding_pkey PRIMARY KEY (id)
);