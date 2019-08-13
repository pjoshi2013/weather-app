package com.example.Weather.App.servicehelper;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.example.Weather.App.WeatherAppProperties;
import com.example.Weather.App.service.WeatherAppService;
import com.example.Weather.App.service.WeatherAvg;
import com.example.Weather.App.service.WeatherAvgData;
import com.example.Weather.App.service.WeatherEntry;
import com.example.Weather.App.service.WeatherForecast;
import com.example.Weather.App.util.DateUtil;
import com.example.Weather.App.web.MissingHeaderInfoException;

@Component
public class WeatherAvgDataHelper {
	
	private static final Logger logger = LoggerFactory.getLogger(WeatherAvgDataHelper.class);
	
	//Took from application.properties file
	private String dayCount="3";
	

	private final String CELSIUS = " C";
	
	private static DecimalFormat df = new DecimalFormat("#.##");
	
	//Took from application.properties file
	private String DAYMIN = "6";
	
	//Took from application.properties file
	private String DAYMAX = "18";
	
	public WeatherAvgDataHelper(WeatherAppProperties weatherAppProperties)
	{
		this.dayCount=weatherAppProperties.getDayCount();
		this.DAYMIN=weatherAppProperties.getDayMin();
		this.DAYMAX=weatherAppProperties.getDayMax();
	}
	
	/*
	 * Return WeatherAvgData data with three days data and average data.
	 */
	public WeatherAvgData getWeatherAvgData(WeatherAvgData weatherAvgData,WeatherForecast weatherForecast) 
	{
		
		logger.info(" ...getWeatherAvgData...");
		List<WeatherEntry> weatherEntry = weatherForecast.getEntries();
	
		try{
		
		List<Date> datenew = new ArrayList();
		datenew.add(DateUtil.getNextDate(new Date()));
		
		for(int i=0;i< (Integer.parseInt(dayCount)-1);i++)
		{
			datenew.add(DateUtil.getNextDate(datenew.get(i)));
			//datenew.add(DateUtil.getNextDate(datenew.get(i)));
		}
		
		List<WeatherAvg> wad =new ArrayList();

		for(Date d : datenew)
		{
			boolean flag1 =false;
			WeatherAvg wadData1 = null;
			List<Double> pa1 = new ArrayList();
			List<Double> day = new ArrayList();
			List<Double> night = new ArrayList();
			if(null != weatherEntry)
			{
				for(WeatherEntry we :weatherEntry )
				{
						try{
							if(null != we && null != we.getDateTime())
							{
								String strDate = we.getDateTime();
								
								String sDate[] = DateUtil.parseDateGetDate(strDate);
								String sTime[] = DateUtil.parseTimeData(sDate[1]);
								
								
								logger.info("STime " );
								
									if(DateUtil.getStringToDate(sDate[0]).
										equals(DateUtil.getStringToDate(
													DateUtil.getDateToString(d))))
									{
										
										if(!flag1)
										{
											wadData1 = new WeatherAvg();
											wadData1.setDate(we.getDateTime());
										}
										flag1=true;
										pa1.add(we.getPressure());
										
										if(isBW6TO18Time(sTime[0]))
											day.add(we.getTemperature());
										else
											night.add(we.getTemperature());
									}
								}
							
						}
						catch(Exception e)
						{
							e.printStackTrace();
						}
				
					}
			}	
			if(null != wadData1)
			{
				wadData1.setPressureAvgList(pa1);
				wadData1.setPressureAvg(getRoundModeData(getAvgOfData(wadData1.getPressureAvgList())));
				wadData1.setDailyDTemp(day);
				wadData1.setNightlyNTemp(night);
				wadData1.setTempDayAvg(getCelsiusTemperature(getAvgOfData(wadData1.getDailyDTemp()))+ CELSIUS);
				wadData1.setTempNightAvg(getCelsiusTemperature(getAvgOfData(wadData1.getNightlyNTemp()))+CELSIUS);
			}
			wad.add(wadData1);	
		
		}

		weatherAvgData.setWeatherAvg(wad);
		}
		catch(MissingHeaderInfoException e)
		{
			e.printStackTrace();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return weatherAvgData;
	}
	
	public String getRoundModeData(Double d)
	{
		 df.setRoundingMode(RoundingMode.UP);
		 return df.format(d);
		
	}
	
	public Double getAvgOfData(List<Double> data)
	{		
		Double avgData= 0.0;
		
		/*OptionalDouble average = data
	            .stream()
	            .mapToDouble(a -> a)
	            .average();*/
		
		if(null != data && data.size() > 0)
		{
			for(Double d1:data)
			{
				avgData += d1;
			}
		}
		
		if(avgData > 0)
			return avgData / data.size();
		else
			return avgData;
	}
	
	public String getCelsiusTemperature(double temperature) {
		System.out.println("temperature :: "+temperature);
		double celsiusTemp = temperature - 273.15;
		return String.format("%4.2f", celsiusTemp);
	}
	
	

	
	public boolean isBW6TO18Time(String str)
	{
		int data = Integer.parseInt(str);
		int dayMin = Integer.parseInt(DAYMIN);
		int dayMax = Integer.parseInt(DAYMAX);
		
		if(data >= dayMin && data <dayMax)
			return true;
		
		return false;
	}

}
