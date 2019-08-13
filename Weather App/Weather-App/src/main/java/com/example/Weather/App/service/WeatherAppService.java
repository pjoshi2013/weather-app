package com.example.Weather.App.service;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

import com.example.Weather.App.WeatherAppProperties;
import com.example.Weather.App.servicehelper.WeatherAvgDataHelper;
import com.example.Weather.App.web.MissingHeaderInfoException;
import com.fasterxml.jackson.databind.ObjectMapper;


@Service
public class WeatherAppService {
	
	private static final Logger logger = LoggerFactory.getLogger(WeatherAppService.class);
	
	private static final String WEATHER_URL =
			"http://api.openweathermap.org/data/2.5/weather?q={city},{country}&APPID={key}";

	private static final String FORECAST_URL =
			"http://api.openweathermap.org/data/2.5/forecast?q={city},{country}&APPID={key}";

	
	private final RestTemplate restTemplate;

	private final String apiKey;
	
	@Autowired
	public WeatherAvgData weatherAvgData;
	
	@Autowired
	public WeatherAvgDataHelper weatherAvgDataHelper;
	
	@Autowired
	ObjectMapper Obj;

	public WeatherAppService(RestTemplateBuilder restTemplateBuilder, WeatherAppProperties weatherAppProperties) {
		this.restTemplate = restTemplateBuilder.build();
		this.apiKey = weatherAppProperties.getKey();
	}

	//@Cacheable("weather")
	public Weather getWeather(String country, String city) {
		logger.info("Requesting current weather", country, city);
		URI url = new UriTemplate(WEATHER_URL).expand(city, country, this.apiKey);
		return invoke(url, Weather.class);
	}
	
	public WeatherForecast getWeatherForecast(String country, String city) {
		logger.info("Requesting weather forecast ", country, city);
		URI url = new UriTemplate(FORECAST_URL).expand(city, country, this.apiKey);
		
		RequestEntity<?> request = RequestEntity.get(url)
				.accept(MediaType.APPLICATION_JSON).build();
		ResponseEntity<WeatherForecast> exchange = this.restTemplate
				.exchange(request, WeatherForecast.class);
		return exchange.getBody();
	}
	
	public String getWeatherForecastAvgData(String country, String city) throws MissingHeaderInfoException {
		logger.info("Requesting weather forecast for avg data press", country, city);
	
		WeatherAvgData wa= weatherAvgDataHelper.getWeatherAvgData
				(weatherAvgData,getWeatherForecast(country,city));
		
				wa.setName(city);
				String jsonStr =null;
		 try{
			 jsonStr = Obj.writeValueAsString(wa);
		 }
		 catch(Exception e)
		 {
			 e.printStackTrace();
		 }
		return jsonStr;
	}
	
	private <T> T invoke(URI url, Class<T> responseType) {
		RequestEntity<?> request = RequestEntity.get(url)
				.accept(MediaType.APPLICATION_JSON).build();
		ResponseEntity<T> exchange = this.restTemplate
				.exchange(request, responseType);
		return exchange.getBody();
	}
	

}
