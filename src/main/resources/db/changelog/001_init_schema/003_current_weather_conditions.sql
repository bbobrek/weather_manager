alter table current_weather_details drop column weather_description;
alter table current_weather_details drop column weather_main;
alter table current_weather_details add column conditions varchar(255);