package com.hackerrank.weather.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hackerrank.weather.model.Weather;
import com.hackerrank.weather.repository.impl.WeatherRepoImpl;

@RestController
public class WeatherApiRestController {

	@Autowired
	WeatherRepoImpl weatherRepoImpl;

	@RequestMapping(value = "/weather", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity getWeather(

			@RequestParam(value = "date", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
			@RequestParam(value = "lat", required = false) Float lat,
			@RequestParam(value = "lon", required = false) Float lon) {

		List<Weather> output = new ArrayList<>();
		if (date != null || lat != null) {
			output = weatherRepoImpl.getWeather(date, lat, lon);
		} else {
			output = weatherRepoImpl.getWeather();
		}

		if (output == null || output.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(output);
		}

	}

	@RequestMapping(value = "/erase", method = RequestMethod.DELETE, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity eraseWeather(
			@RequestParam(value = "start", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
			@RequestParam(value = "end", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
			@RequestParam(value = "lat", required = false) Float lat,
			@RequestParam(value = "lon", required = false) Float lon) {

		if (startDate == null || lat == null) {
			weatherRepoImpl.eraseWeatherAll();
			return ResponseEntity.status(HttpStatus.OK).body(null);
		} else {
			weatherRepoImpl.eraseWeatherRange(startDate, endDate, lat, lon);
			return ResponseEntity.status(HttpStatus.OK).body(null);
		}

	}

	@RequestMapping(value = "/weather", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity addWeather(@RequestBody Weather myWeather) {
		String temp = weatherRepoImpl.postWeather(myWeather);

		if (temp == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(null);
		}
	}

}
