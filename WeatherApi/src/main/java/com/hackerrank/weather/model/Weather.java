package com.hackerrank.weather.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Weather {
    private Long id;
    @JsonFormat(pattern="yyyy-MM-dd", timezone="CST")
    private Date date;
    private Location location;
    private Float[] temperature;

    public Weather() {
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Float[] getTemperature() {
		return temperature;
	}

	public void setTemperature(Float[] temperature) {
		this.temperature = temperature;
	}

}
