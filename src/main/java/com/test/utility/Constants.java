package com.test.utility;

public class Constants {
	
	
	public static final String DATE_12TIME_WITH_ENGLISH_MONTH = "dd-MMM-yyyy hh:mm:ss aa";
	public static final String DATE_FORMAT = "yyyy-MM-dd hh:mm:ss";
//    public static final String CONFIGPATH = System.getProperty("catalina.base").concat("\\ApplicationConfiguration\\QR\\Application.properties");
//    public static final String LOG4J_CONFIG_PATH = System.getProperty("catalina.base").concat("\\ApplicationConfiguration\\QR\\log4j.xml");
 
   public static final String CONFIGPATH = "C:\\SpringPractice\\ApplicationConfiguration\\ApplicationConfiguration.properties";
    public static final String LOG4J_CONFIG_PATH = "C:\\SpringPractice\\ApplicationConfiguration\\log4j.xml";
 
    public static final String APIREQUESTID = "apirequestid";
    public static final String TOKEN = "Authorization";

    public static final String SUCCESS_CODE ="000";
	public static final String NOTFOUND_CODE="001";
	public static final String MANDATORY_PARAM_CODE="002";
	public static final String INVALID_DATA_CODE="003";
	public static final String GENERAL_FAILURE_CODE="004";
	public static final String DB_FAILURE_CODE="005"; 
	
	public static final String SUCCESS="Success";
	public static final String FAILURE ="Failure";
	
	public static final String SUCCESS_FOUND ="Token Found";
	public static final String SUCCESS_INSERT ="Token Saved";
	public static final String NOTFOUND="Not Found";
	public static final String MANDATORY_PARAM="Mandatory Parameter Missing";
	public static final String INVALID_DATA="Invalid Request";
	public static final String GENERAL_FAILURE="General Failure";
	public static final String DB_FAILURE="Unable to connect database";
	
	
}
