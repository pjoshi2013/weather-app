package com.example.Weather.App.service;

import java.io.Serializable;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

public class WeatherEntry implements Serializable{
	private Instant timestamp;

	private double temperature=0.0;
	
	private double temperatureMin;
	
	private double temperatureMax;

	private Integer weatherId;

	private String weatherIcon;
	
	private String dateTime;
	
	private double pressure=0.0;


	@JsonProperty("timestamp")
	public Instant getTimestamp() {
		return this.timestamp;
	}

	@JsonSetter("dt")
	public void setTimestamp(long unixTime) {
		this.timestamp = Instant.ofEpochMilli(unixTime * 1000);
	}

	/**
	 * Return the temperature in Kelvin (K).
	 */
	public double getTemperature() {
		return this.temperature;
	}

	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	@JsonProperty("main")
	public void setMain(Map<String, Object> main) {
		setTemperature(Double.parseDouble(main.get("temp").toString()));
		setTemperatureMin(Double.parseDouble(main.get("temp_min").toString()));
		setTemperatureMax(Double.parseDouble(main.get("temp_max").toString()));
		setPressure(Double.parseDouble(main.get("pressure").toString()));
	}
	
	public Integer getWeatherId() {
		return this.weatherId;
	}

	public void setWeatherId(Integer weatherId) {
		this.weatherId = weatherId;
	}

	public String getWeatherIcon() {
		return this.weatherIcon;
	}

	public double getPressure() {
		return pressure;
	}

	public void setPressure(double pressure) {
		this.pressure = pressure;
	}

	public double getTemperatureMin() {
		return temperatureMin;
	}

	public void setTemperatureMin(double temperatureMin) {
		this.temperatureMin = temperatureMin;
	}

	public double getTemperatureMax() {
		return temperatureMax;
	}

	public void setTemperatureMax(double temperatureMax) {
		this.temperatureMax = temperatureMax;
	}

	public void setWeatherIcon(String weatherIcon) {
		this.weatherIcon = weatherIcon;
	}

	@JsonProperty("weather")
	public void setWeather(List<Map<String, Object>> weatherEntries) {
		Map<String, Object> weather = weatherEntries.get(0);
		setWeatherId((Integer) weather.get("id"));
		setWeatherIcon((String) weather.get("icon"));
	}
	
	@JsonProperty("dt_txt")
	public void setdt_txt(String dt_txt) {
		setDateTime(dt_txt);
	}

}
