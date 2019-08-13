package com.example.Weather.App.service;

import java.util.ArrayList;
import java.util.List;

public class WeatherAvg {
	
	public String date;
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	private List<Double> pressureAvgList = new ArrayList();
	
	private String pressureAvg;
	
	
	public List<Double> getPressureAvgList() {
		return pressureAvgList;
	}

	public void setPressureAvgList(List<Double> pressureAvgList) {
		this.pressureAvgList = pressureAvgList;
	}

	

	private List<Double> dailyDTemp = new ArrayList();
	
	private List<Double> nightlyNTemp = new ArrayList();
	
	
	private String tempDayAvg ;
	private String tempNightAvg;

	public List<Double> getDailyDTemp() {
		return dailyDTemp;
	}
	
	

	public String getPressureAvg() {
		return pressureAvg;
	}

	public void setPressureAvg(String pressureAvg) {
		this.pressureAvg = pressureAvg;
	}



	public String getTempDayAvg() {
		return tempDayAvg;
	}

	public void setTempDayAvg(String tempDayAvg) {
		this.tempDayAvg = tempDayAvg;
	}

	public String getTempNightAvg() {
		return tempNightAvg;
	}

	public void setTempNightAvg(String tempNightAvg) {
		this.tempNightAvg = tempNightAvg;
	}

	public void setDailyDTemp(List<Double> dailyDTemp) {
		this.dailyDTemp = dailyDTemp;
	}

	public List<Double> getNightlyNTemp() {
		return nightlyNTemp;
	}

	public void setNightlyNTemp(List<Double> nightlyNTemp) {
		this.nightlyNTemp = nightlyNTemp;
	}

	

}
