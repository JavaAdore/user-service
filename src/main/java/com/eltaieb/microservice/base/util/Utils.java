package com.eltaieb.microservice.base.util;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Utils {

	public static LocalDate toDate(String dateAsString, String format) {
		try {
			LocalDate localDate = LocalDate.parse(dateAsString, DateTimeFormatter.ofPattern(format));
			 return localDate;
		} catch (Exception ex) {
			return null;
		}

	}

	public static boolean isValidUrl(String urlStr) {
 		    try {
		      new URL(urlStr);
		      return true;
		    }
		    catch (MalformedURLException e) {
		        return false;
		    }
	}
}
