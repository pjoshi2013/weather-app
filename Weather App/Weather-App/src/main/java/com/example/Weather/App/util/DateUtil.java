package com.example.Weather.App.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	
	private static final String DATEFORMAT = "yyyy-MM-dd";
	
	/**
	 * 
	 * @param strDate
	 * @return
	 * @throws Exception
	 */
	public static Date getStringToDate(String strDate)  throws Exception
	{
		return new SimpleDateFormat(DATEFORMAT).parse(strDate);
	}
	
	/*
	 * Return string from Date.
	 */
	public static String getDateToString(Date dt) 
	{
		return new SimpleDateFormat(DATEFORMAT).format(dt);
	}
	
	/*
	 * Return Next Date.
	 */
	public static Date getNextDate(Date dt)  
	{
		return  new Date(dt.getTime() + (1000 * 60 * 60 * 24));
	}
	/*
	 * Return boolean value true if two dates are same
	 */
	public static boolean isCurrentDate(Date dt1,Date dt2) 
	{
		return dt1.equals(dt2);
	}
	
	/*
	 * Split date with space
	 */
	public static String[] parseDateGetDate(String strDt) 
	{
		return strDt.split(" ");
	}
	
	/*
	 * Split time with ":"
	 */
	public static String[] parseTimeData(String strDt)
	{
		return strDt.split(":");
	}
	
	

}
