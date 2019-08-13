package com.example.Weather.App.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

@Component
public class WeatherAvgData implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	private String name;
	
	private List<WeatherAvg> weatherAvg = new ArrayList();

	public List<WeatherAvg> getWeatherAvg() {
		return weatherAvg;
	}

	public void setWeatherAvg(List<WeatherAvg> weatherAvg) {
		this.weatherAvg = weatherAvg;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@JsonProperty("city")
	public void setCity(Map<String, Object> city) {
		setName(city.get("name").toString());
	}

}
