package com.hackerrank.weather.repository.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.springframework.stereotype.Component;

import com.hackerrank.weather.model.Weather;
import com.hackerrank.weather.repository.WeatherRepository;

@Component
public class WeatherRepoImpl implements WeatherRepository {

	Map<Long, Weather> map = new TreeMap<>();

	@Override
	public List<Weather> getWeather(Date date, Float lat, Float lon) {
		List<Weather> myList = new ArrayList<>();
		for (Long i : map.keySet()) {
			System.out.println(map.get(i).getDate().toString() + "  " + date);
			if (date != null && map.get(i).getDate().equals(date)) {
				myList.add(map.get(i));
			}
			if (lat != null && lon != null && map.get(i).getLocation().getLat().equals(lat)
					&& map.get(i).getLocation().getLon().equals(lon)) {
				myList.add(map.get(i));
			}
		}
		return myList;
	}

	@Override
	public String postWeather(Weather weather) {
		Weather temp = map.put(weather.getId(), weather);
		System.out.println(weather.getDate());
		if (temp == null) {
			return "SUCCESS";
		} else {
			return null;
		}

	}

	@Override
	public void eraseWeatherAll() {
		map.clear();

	}

	@Override
	public void eraseWeatherRange(Date startDate, Date endDate, Float lat, Float lon) {

		Set<Long> toBeDelete = new HashSet<>();
		for (Long i : map.keySet()) {
			System.out.println(map.get(i).getDate().toString() + "  " + startDate);
			if ((map.get(i).getDate().after(startDate) || map.get(i).getDate().equals(startDate))
					&& (map.get(i).getDate().before(endDate) || map.get(i).getDate().equals(endDate))
					&& map.get(i).getLocation().getLat().equals(lat) 
					&& map.get(i).getLocation().getLon().equals(lon)) {
				toBeDelete.add(i);
			}
		}
		for (Long i : toBeDelete) {
			map.remove(i);
		}
	}

	@Override
	public List<Weather> getWeather() {
		List<Weather> myList = new ArrayList<>();
		for (Long i : map.keySet()) {
			myList.add(map.get(i));
		}
		return myList;
	}

}
