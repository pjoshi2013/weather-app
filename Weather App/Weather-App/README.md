


#Weather App

Register an API Key on the OpenWeatherApp service.

Application Configration : 
1) SpringBoot Module
2) Eclipse Kepler
3) JDK 1.8
4) Maven 4.0

Assumption : Used OpenWeatherApp service should give properdata with same format.

To Run this project :

	Import project into eclipse and run with class WeatherAppApplication.

Files are available with project.
1)Dataformat.txt
2)weatherapp.rtf
	

Below URL use to get output data.

1) Application return Json Data , 
2) Average of pressure for next three days in celsius
3) Average of temperatur for next three days in celsius
4) Unit test cases include.
5) Exception handled through eclipse.
	 /*
	 * http://localhost:8080/api/weather/avg/uk/London
	 * URI : /weekly/{country}/{city}
	 * Return : JsonData Object
	 * Get forcast data for 3 days in Json format
	 * Object WeatherAvgDat , WeatherAvg
	 * Fields : pressureAvg  (Gives Avg of pressure data)
	 * Fields : tempDayAvg  (Gives Avg of Daily temperature data in Celsius)
	 * Fields : tempNightAvg  (Gives Avg of Night temperature data in Celsius)
	 */
	 
	 Output would be like : pressureAvg , tempDayAvg , tempNightAvg (Requ
	 
	 {"name":"London",

	"weatherAvg":[
		{"date":"2019-08-09 00:00:00",
		 "pressureAvgList":[1005.91,1003.76,1002.18,1001.74,1001.65,1001.1,1000.31,1001.33],
		 "pressureAvg":"1002.25","dailyDTemp":[291.543,293.765,295.465,296.211],
		 "nightlyNTemp":[292.462,291.747,293.512,291.338],
		 "tempDayAvg":"21.10 C","tempNightAvg":"19.11 C"},
		 
		 {"date":"2019-08-10 00:00:00",
		 "pressureAvgList":[1000.75,1001.55,1002.76,1005.51,1006.93,1008.41,1009.87,1011.53],
		 "pressureAvg":"1005.92","dailyDTemp":[290.391,292.659,294.147,293.3],
		 "nightlyNTemp":[291.246,290.795,292.2,289.8],"tempDayAvg":"19.47 C",
		 "tempNightAvg":"17.86 C"},
		 
		 {"date":"2019-08-11 00:00:00",
		 "pressureAvgList":[1012.22,1011.9,1011.6,1011.67,1010.79,1010.18,1010.04,1011.05],
		 "pressureAvg":"1011.19","dailyDTemp":[288.627,292.251,294.322,294.61],
		 "nightlyNTemp":[288.7,288.177,291.836,289.286],
		 "tempDayAvg":"19.30 C",
		 "tempNightAvg":"16.35 C"}
		 ]
	}

/*
	 URL Call : http://localhost:8080/api/weather/now/uk/London
	 * URI : /now/{country}/{city}
	 * Return : JsonData Object
	 * Get weather data in Json format
		Method : getWeather
	 */
	 
/*
http://localhost:8080/api/weather/weekly/uk/London
	 * URI : /weekly/{country}/{city}
	 * Return : JsonData Object
	 * Get forcast data for 5 days in Json format
	 Method : getWeatherForecast
	 */
	 
