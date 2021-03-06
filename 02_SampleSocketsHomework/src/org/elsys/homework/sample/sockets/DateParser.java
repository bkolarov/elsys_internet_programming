package org.elsys.homework.sample.sockets;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.Days;

public class DateParser extends SimpleDateFormat {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DateParser(String pattern) {
		super(pattern);
	}

	public int getDaysBetweenTodayAndDate(String date) throws ParseException {
		final Date today = new Date();
		final Date secondDate = parseDate(date);
		
		int days = Days.daysBetween(new DateTime(today), new DateTime(secondDate)).getDays();
		
		return days;
	}
	
	private Date parseDate(String source) throws ParseException {
		return super.parse(source);
	}
}
