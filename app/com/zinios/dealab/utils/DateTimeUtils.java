package com.zinios.dealab.utils;

import org.joda.time.DateTime;
import org.joda.time.Period;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by vihanga on 10/29/17.
 */
public class DateTimeUtils {
	public int getCurrentYear() {
		return Calendar.getInstance().get(Calendar.YEAR);
	}

	public int getCurrentMonth() {
		return Calendar.getInstance().get(Calendar.MONTH);
	}

	public Date getCurrentDateTime() {
		return Calendar.getInstance().getTime();
	}

	public String getToday() {
		return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
	}

	public long getCurrentDateTimeMilis() {
		return Calendar.getInstance().getTimeInMillis();
	}

	public Date getExpireDate(int daysToExpire) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, daysToExpire);
		return calendar.getTime();
	}

	public boolean expired(Date expireDate) {
		Date current = Calendar.getInstance().getTime();
		return !current.before(expireDate);
	}

	public String getTimeDiff(Date fromTime) {
		DateTime from = new DateTime(fromTime);
		DateTime to = new DateTime();

		Period period = new Period(from, to);
		if (period.getYears() > 0) {
			if (period.getYears() == 1) {
				return period.getYears() + " year ago";
			}
			return period.getYears() + " years ago";
		} else if (period.getMonths() > 0) {
			if (period.getMonths() == 1) {
				return period.getMonths() + " month ago";
			}
			return period.getMonths() + " months ago";
		} else if (period.getWeeks() > 0) {
			if (period.getWeeks() == 1) {
				return period.getWeeks() + " week ago";
			}
			return period.getWeeks() + " weeks ago";
		} else if (period.getDays() > 0) {
			if (period.getDays() == 1) {
				return period.getDays() + " day ago";
			}
			return period.getDays() + " days ago";
		} else if (period.getHours() > 0) {
			if (period.getHours() == 1) {
				return period.getHours() + " hour ago";
			}
			return period.getHours() + " hours ago";
		} else if (period.getMinutes() > 0) {
			if (period.getMinutes() == 1) {
				return period.getMinutes() + " minute ago";
			}
			return period.getMinutes() + " minutes ago";
		} else if (period.getSeconds() > 7) {
			return period.getSeconds() + " seconds ago";
		} else {
			return "Just now";
		}
	}
}
