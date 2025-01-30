package com.test.utility;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.TimeZone;



public class DateUtil {
	
	

	public  String getCurrentDateAsString(String format) { // "dd-M-yyyy"
		Date currentDate = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		return formatter.format(currentDate);
	}

	public  String getCurrentTimeAsString(String format) { // "hh:mm:ss"
		Date currentDate = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		return formatter.format(currentDate);
	}

	public static  String getCurrentDateTimeAsString(String format) { // dd-MMM-yyyy hh:mm:ss, dd-M-yyyy hh:mm:ss
		Date currentDate = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		return formatter.format(currentDate);
	}

	public  String getCurrentCSTDateTimeAsString(String format, String gmt) { // yyyy-MM-dd HH:mm:ss.SSS
		Date currentDate = new Date();
		DateFormat gmtFormat = new SimpleDateFormat(format);
		String convCurrentDates = gmtFormat.format(currentDate);
		Date dateInput = null;
		try {
			dateInput = new SimpleDateFormat(format).parse(convCurrentDates);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		TimeZone gmtTime = TimeZone.getTimeZone(gmt);
		gmtFormat.setTimeZone(gmtTime);
		gmtFormat.format(dateInput);
		return gmtFormat.format(dateInput);
	}

	public  Long getDuration1(String date1, String date2) throws ParseException { // yyyy-MM-dd HH:mm:ss.SSS
		Long difference = null;
		Date startDate;
		Date endDate;
//		Long startDateTime;
//		Long endDateTime;
		try {
			startDate = new SimpleDateFormat(Constants.DATE_FORMAT).parse(date1);
			endDate = new SimpleDateFormat(Constants.DATE_FORMAT).parse(date2);
//			startDateTime = startDate.getTime() / 1000;
//			endDateTime = endDate.getTime() / 1000;
//			difference = endDateTime - startDateTime;
			difference = endDate.getTime() - startDate.getTime();
			System.out.println("duration ::"+difference );
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return difference;
	}
	public long getDuration(String startdate,String enddate) 
	{
		long difference=0;
		DateTimeFormatter format = DateTimeFormatter.ofPattern(Constants.DATE_FORMAT);
	   
	    	LocalDateTime start = LocalDateTime.parse(startdate, format);
	    	LocalDateTime end = LocalDateTime.parse(enddate, format);
	    	
	    	difference = ChronoUnit.SECONDS.between(start, end);
	   
		return difference;
	}
	
	
	public long getTimeDifference()
	{
		long intdelayTime=0;
		String delayTime=ApplicationConfig.getTag("tokenValidity");
		if(delayTime.endsWith("m")) {
			delayTime=delayTime.replace("m", "");
			intdelayTime=Integer.parseInt(delayTime)*60;
		}else if(delayTime.endsWith("h")) {
			delayTime=delayTime.replace("h", "");
			intdelayTime=Integer.parseInt(delayTime)*60*60;
		}else if(delayTime.endsWith("s")) {
			delayTime=delayTime.replace("s", "");
			intdelayTime=Integer.parseInt(delayTime);
		} else  {
			intdelayTime=Integer.parseInt(delayTime);
		}
		return intdelayTime;
	}
	
}