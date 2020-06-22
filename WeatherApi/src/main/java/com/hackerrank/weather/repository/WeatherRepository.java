package com.hackerrank.weather.repository;

import java.util.Date;
import java.util.List;

import com.hackerrank.weather.model.Weather;

public interface WeatherRepository {
	
	public List<Weather> getWeather();
	public String postWeather(Weather weather);
	public void eraseWeatherAll();
	public void eraseWeatherRange(Date startDate,
			Date endDate,
			Float lat,
			Float lon);
	public List<Weather> getWeather(Date date, Float lat, Float lon);

	
}
