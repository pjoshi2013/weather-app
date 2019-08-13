package com.example.Weather.App;

import java.util.Arrays;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("app.weather")
public class WeatherAppProperties {
	
	//Not in use
	private List<String> locations = Arrays.asList("UK/London", "Russia/Moscow");
	
	@NotNull
	private String key;
	
	private String dayCount;
	
	private String dayMin;
	
	private String dayMax;

	public String getDayMin() {
		return dayMin;
	}

	public void setDayMin(String dayMin) {
		this.dayMin = dayMin;
	}

	public String getDayMax() {
		return dayMax;
	}

	public void setDayMax(String dayMax) {
		this.dayMax = dayMax;
	}

	public String getDayCount() {
		return dayCount;
	}

	public void setDayCount(String dayCount) {
		this.dayCount = dayCount;
	}

	public String getKey() {
		return this.key;
	}

	public void setKey(String key) {
		this.key = key;
	}


	public List<String> getLocations() {
		return this.locations;
	}

	public void setLocations(List<String> locations) {
		this.locations = locations;
	}

}
