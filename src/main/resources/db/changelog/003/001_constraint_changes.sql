truncate table hourly_weather_details, daily_weather_details, weather_result, current_weather_details;

alter table hourly_weather_details drop constraint if exists fk6sipgri87ap5p9f8w0almp99e;
ALTER TABLE daily_weather_details drop CONSTRAINT if exists fkp8bbjifdjghr4frqm8uhpwcqp;
ALTER TABLE weather_result drop CONSTRAINT if exists fk540dqk2mi1qegy3v0lqpkra6i;
ALTER TABLE current_weather_details drop CONSTRAINT if exists fk540dqk2mi1qegy3v0lqpkra6i;

alter table weather_result drop column if exists current_weather_details_id;
alter table current_weather_details drop column if exists weather_result_id;
alter table current_weather_details add weather_result_id uuid NULL;

ALTER TABLE hourly_weather_details ADD CONSTRAINT fk6sipgri87ap5p9f8w0almp99e FOREIGN KEY (weather_result_id) REFERENCES weather_result(id) on delete cascade;
ALTER TABLE daily_weather_details ADD CONSTRAINT fkp8bbjifdjghr4frqm8uhpwcqp FOREIGN KEY (weather_result_id) REFERENCES weather_result(id) on delete cascade;
ALTER TABLE current_weather_details ADD CONSTRAINT fk540dqk2mi1qegy3v0lqpkra6i FOREIGN KEY (weather_result_id) REFERENCES weather_result(id) on delete cascade;
