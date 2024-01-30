truncate table hourly_weather_details, daily_weather_details, weather_result, current_weather_details;

alter table hourly_weather_details drop constraint fk6sipgri87ap5p9f8w0almp99e;
alter table daily_weather_details drop constraint fkp8bbjifdjghr4frqm8uhpwcqp;
alter table weather_result drop constraint fk540dqk2mi1qegy3v0lqpkra6i;

alter table hourly_weather_details drop column id;
alter table daily_weather_details drop column id;
alter table weather_result drop column id;
alter table current_weather_details drop column id;

alter table hourly_weather_details add column id uuid NOT NULL primary key;
alter table daily_weather_details add column id uuid NOT NULL primary key;
alter table weather_result add column id uuid NOT NULL primary key;
alter table current_weather_details add column id uuid NOT NULL primary key;

alter table hourly_weather_details drop column weather_result_id;
alter table daily_weather_details drop column weather_result_id;
alter table weather_result drop column current_weather_details_id;

alter table hourly_weather_details add column weather_result_id uuid NULL;
alter table daily_weather_details add column weather_result_id uuid NULL;
alter table weather_result add column current_weather_details_id uuid NULL;

ALTER TABLE hourly_weather_details ADD CONSTRAINT fk6sipgri87ap5p9f8w0almp99e FOREIGN KEY (weather_result_id) REFERENCES weather_result(id);
ALTER TABLE daily_weather_details ADD CONSTRAINT fkp8bbjifdjghr4frqm8uhpwcqp FOREIGN KEY (weather_result_id) REFERENCES weather_result(id);
ALTER TABLE weather_result ADD CONSTRAINT fk540dqk2mi1qegy3v0lqpkra6i FOREIGN KEY (current_weather_details_id) REFERENCES current_weather_details(id);

DROP SEQUENCE hibernate_sequence;