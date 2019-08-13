package com.example.Weather.App;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import java.time.Instant;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.Weather.App.service.Weather;
import com.example.Weather.App.service.WeatherAppService;
import com.example.Weather.App.service.WeatherAvgData;
import com.example.Weather.App.service.WeatherEntry;
import com.example.Weather.App.service.WeatherForecast;
import com.example.Weather.App.servicehelper.WeatherAvgDataHelper;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class WeatherAppControllerTest {
	
	@MockBean
	private WeatherAppService weatherAppService;

	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private ObjectMapper Obj;
	
	@Autowired
	public WeatherAvgDataHelper weatherAvgDataHelper;
	
	@Autowired
	WeatherAvgData wForecastAvg1;
	
	@Test
	public void weather() throws Exception {
		Weather weather = new Weather();
		weather.setName("London");
		setWeatherEntry(weather, 290.72, 800, "01d", Instant.ofEpochSecond(1234),"");
		given(this.weatherAppService.getWeather("uk", "london")).willReturn(weather);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/api/weather/now/uk/london").accept(
				MediaType.APPLICATION_JSON);
		
		MvcResult result = this.mvc.perform(requestBuilder).andReturn();
		
		ResultMatcher ok = MockMvcResultMatchers.status().isOk();
	
		System.out.println(result.getResponse());
		
		this.mvc.perform(requestBuilder)
				.andExpect(ok)
				.andExpect(MockMvcResultMatchers.jsonPath("$.name").value("London")) 
				.andExpect(MockMvcResultMatchers.jsonPath("$.temperature").value(290.72)	
				);
	}
	
	@Test
	public void weatherForecast() throws Exception {
		WeatherForecast forecast = new WeatherForecast();
		forecast.setName("Brussels");
		forecast.getEntries().add(createWeatherEntry(285.45, 600, "02d", Instant.ofEpochSecond(1234),""));
		forecast.getEntries().add(createWeatherEntry(294.45, 800, "01d", Instant.ofEpochSecond(5678),""));
		given(this.weatherAppService.getWeatherForecast("be", "brussels")).willReturn(forecast);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/api/weather/weekly/be/brussels").accept(
				MediaType.APPLICATION_JSON);
		
		this.mvc.perform(requestBuilder)
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.entries[0].temperature").value(285.45)) 
				.andExpect(MockMvcResultMatchers.jsonPath("$.entries[1].temperature").value(294.45)	
				);
		
		verify(this.weatherAppService).getWeatherForecast("be", "brussels");
	}
	
	@Test
	public void weatherForecastAvgData() throws Exception {
		
		WeatherForecast forecast = new WeatherForecast();
		forecast.setName("Brussels");
		forecast.getEntries().add(createWeatherEntry(285.45, 600, "02d", Instant.ofEpochSecond(1234),"2019-08-09 06:00:00"));
		forecast.getEntries().add(createWeatherEntry(294.45, 800, "01d", Instant.ofEpochSecond(5678),"2019-08-10 06:00:00"));
		forecast.getEntries().add(createWeatherEntry(294.45, 800, "01d", Instant.ofEpochSecond(5678),"2019-08-11 06:00:00"));
		
		given(this.weatherAppService.getWeatherForecast("be", "brussels")).willReturn(forecast);
		
		//WeatherAvgData wForecastAvg = new WeatherAvgData();
		WeatherAvgData wForecastAvg= weatherAvgDataHelper.getWeatherAvgData
				(wForecastAvg1,forecast);
		wForecastAvg.setName("Brussels");
		given(this.weatherAppService.getWeatherForecastAvgData("be", "brussels")).willReturn(getJsonStringData(wForecastAvg));
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/api/weather/weekly/be/brussels").accept(
				MediaType.APPLICATION_JSON);
		
		this.mvc.perform(requestBuilder)
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.entries[0].pressure").value(1008.00)//) 
				//.andExpect(MockMvcResultMatchers.jsonPath("$.entries[1].temperature").value(294.45)	
				);
		
		verify(this.weatherAppService).getWeatherForecast("be", "brussels");
	}
	
	private String getJsonStringData(WeatherAvgData wa)
	{
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

	private static WeatherEntry createWeatherEntry(double temperature, int id, String icon,
			Instant timestamp,String dateTime) {
		WeatherEntry entry = new WeatherEntry();
		setWeatherEntry(entry, temperature, id, icon, timestamp,dateTime);
		return entry;
	}
	
	private static void setWeatherEntry(WeatherEntry entry, double temperature, int id, String icon,
			Instant timestamp,String dateTime) {
		entry.setTemperature(temperature);
		entry.setWeatherId(id);
		entry.setWeatherIcon(icon);
		entry.setTimestamp(timestamp.getEpochSecond());
		entry.setDateTime(dateTime);
		entry.setPressure(1008.00);
	}

}
