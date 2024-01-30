SET client_encoding TO 'UTF8';
CREATE SEQUENCE IF NOT EXISTS hibernate_sequence START 1 INCREMENT BY 1;

CREATE TABLE daily_weather_details (
	id int8 NOT NULL primary key,
	cloud_coverage int4 NULL,
	conditions varchar(255) NULL,
	description varchar(255) NULL,
	feels_like int4 NULL,
	humidity int4 NULL,
	pressure int4 NULL,
	rain int4 NULL,
	snow int4 NULL,
	temperature int4 NULL,
	temperature_max int4 NULL,
	temperature_min int4 NULL,
	"timestamp" timestamp NULL,
	wind_degrees int4 NULL,
	wind_gust int4 NULL,
	wind_speed int4 NULL,
	weather_result_id int8 NULL
);

CREATE TABLE hourly_weather_details (
	id int8 NOT NULL primary key,
	cloud_coverage int4 NULL,
	conditions varchar(255) NULL,
	feels_like int4 NULL,
	humidity int4 NULL,
	precipitation_probability int4 NULL,
	pressure int4 NULL,
	rain int4 NULL,
	snow int4 NULL,
	temperature int4 NULL,
	"timestamp" timestamp NULL,
	wind_degrees int4 NULL,
	wind_gust int4 NULL,
	wind_speed int4 NULL,
	weather_result_id int8 NULL
);

CREATE TABLE weather_result (
	id int8 NOT NULL primary key,
	city varchar(255) NULL,
	creation_date timestamp NULL,
	latitude varchar(255) NULL,
	longitude varchar(255) NULL,
	sunrise timestamp NULL,
	sunset timestamp NULL,
	current_weather_details_id int8 NULL
);

CREATE TABLE current_weather_details (
	id int8 NOT NULL primary key,
	cloud_coverage int4 NULL,
	feels_like int4 NULL,
	humidity int4 NULL,
	precipitation_probability int4 NULL,
	pressure int4 NULL,
	rain int4 NULL,
	snow int4 NULL,
	temperature int4 NULL,
	"timestamp" timestamp NULL,
	weather_description varchar(255) NULL,
	weather_main varchar(255) NULL,
	wind_degrees int4 NULL,
	wind_gust int4 NULL,
	wind_speed int4 NULL
);

ALTER TABLE hourly_weather_details ADD CONSTRAINT fk6sipgri87ap5p9f8w0almp99e FOREIGN KEY (weather_result_id) REFERENCES weather_result(id);
ALTER TABLE daily_weather_details ADD CONSTRAINT fkp8bbjifdjghr4frqm8uhpwcqp FOREIGN KEY (weather_result_id) REFERENCES weather_result(id);
ALTER TABLE weather_result ADD CONSTRAINT fk540dqk2mi1qegy3v0lqpkra6i FOREIGN KEY (current_weather_details_id) REFERENCES current_weather_details(id);