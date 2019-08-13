package com.example.Weather.App.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Weather.App.service.Weather;
import com.example.Weather.App.service.WeatherAppService;
import com.example.Weather.App.service.WeatherAvgData;
import com.example.Weather.App.service.WeatherForecast;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/weather") 
public class WeatherAppController {
	
	@Autowired
	private ObjectMapper Obj;
	/*@RequestMapping("/")
	String home()
	{
		return "HOME ";
	}*/
	
	private final WeatherAppService weatherAppService;
	
	

	public WeatherAppController(WeatherAppService weatherAppService) {
		this.weatherAppService = weatherAppService;
	}

	/*
	 * URI : /now/{country}/{city}
	 * Return : JsonData Object
	 * Get weather data in Json format
	 */
	@RequestMapping("/now/{country}/{city}")
	public Weather getWeather(@PathVariable String country,
			@PathVariable String city) {
		return this.weatherAppService.getWeather(country, city);
	}
	
	/*
	 * URI : /weekly/{country}/{city}
	 * Return : JsonData Object
	 * Get forcast data for 5 days in Json format
	 */
	@RequestMapping("/weekly/{country}/{city}")
	public WeatherForecast getWeatherForecast(@PathVariable String country,
			@PathVariable String city) {
		return this.weatherAppService.getWeatherForecast(country, city);
	}
	
	
	/*
	 * 
	 * URI : /weekly/{country}/{city}
	 * Return : JsonData Object
	 * Get forcast data for 3 days in Json format
	 * Object WeatherAvgDat , WeatherAvg
	 * Fields : pressureAvg  (Gives Avg of pressure data)
	 * Fields : tempDayAvg  (Gives Avg of Daily temperature data)
	 * Fields : tempNightAvg  (Gives Avg of Night temperature data)
	 */
	@RequestMapping("/avg/{country}/{city}")
	public String getWeatherForecastAvgData(@PathVariable String country,
			@PathVariable String city) throws MissingHeaderInfoException{
		
		return this.weatherAppService.getWeatherForecastAvgData(country, city);
	}
	
	@RequestMapping("/avgexp/{country}/{city}")
	public String getWeatherForecastAvgDataExp(@PathVariable String country,
			@PathVariable String city) throws MissingHeaderInfoException{
		
		throw new MissingHeaderInfoException("Error in constructor ...");
		
		//return this.weatherAppService.getWeatherForecastAvgData(country, city);
	}
	


}
