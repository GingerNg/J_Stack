package com.ginger.study.utils;
/**
 */

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间类型工具类
 */
public class DateUtil {
	public static enum FORMAT_TYPE {
		DATE, DATETIME
	};

	private static final DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
	private static final DateFormat dateTimeFormat = new SimpleDateFormat("yyyyMMddHHmmss");
	private static final int dateLength = "yyyyMMdd".length();
	private static final int dateTimeLength = "yyyyMMddHHmmss".length();
	private static final int timeLength = "HHmmss".length();

	/**
	 * 格式化日期时间
	 * @param date
	 * @return yyyyMMddHHmmss
	 */
	public static Long formatDateTime(Date date) {
		return format(date, FORMAT_TYPE.DATETIME);
	}

	/**
	 * 格式化日期
	 * @param date
	 * @return yyyyMMdd
	 */
	public static Long formatDate(Date date) {
		return format(date, FORMAT_TYPE.DATE);
	}

	public static String formatDateStr(Date date, String dateformat) {
		DateFormat dateTimeFormat = new SimpleDateFormat(dateformat);
		return dateTimeFormat.format(date);
	}

	/**
	 * 整型日期格式数据转换成指定格式Date
	 * @param dateValue
	 * @param type
	 * @return Date
	 */
	public static Date getDate(Long dateValue, FORMAT_TYPE type) {
		Date date = null;
		String dateStr = dateValue.toString();
		switch (type) {
		case DATE:
			try {
				date = dateFormat.parse(dateStr);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			break;
		case DATETIME:
			try {
				date = dateTimeFormat.parse(dateStr);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			break;
		default:
			break;
		}
		return date;
	}

	public static Date add(Date date, int calendarField, int amount) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(calendarField, amount);
		return calendar.getTime();
	}

	/**
	 * 获取当前时间
	 *
	 * @Title fetchNow
	 * @Description
	 * @param type
	 *            时间格式类型
	 * @return 时间值
	 */
	public static long fetchNow(FORMAT_TYPE type) {
		Calendar calendar = Calendar.getInstance();
		return format(calendar.getTime(), type).longValue();
	}

	private static Long format(Date date, FORMAT_TYPE type) {
		String dateStr = null;
		switch (type) {
		case DATE:
			dateStr = dateFormat.format(date);
			break;
		case DATETIME:
			dateStr = dateTimeFormat.format(date);
			break;
		default:
			break;
		}
		return Long.parseLong(dateStr);
	}

	/**
	 * 获取两个日期之间的时间差 单位是毫秒<br>
	 *
	 * @Title getTimeDelta
	 * @Description
	 * @param maxDate
	 * @param minDate
	 * @return
	 */
	public static long getTimeDelta(Date maxDate, Date minDate) {
		return maxDate.getTime() - minDate.getTime();
	}

	/**
	 * 获取现有日期基础上加一个时间段后的日期
	 *
	 * @Title getTimeWithDelta
	 * @Description
	 * @param date
	 *            现有日期
	 * @param delta
	 *            时间段，单位为毫秒
	 * @return
	 */
	public static Date getTimeWithDelta(Date date, long delta) {
		long time = date.getTime();
		return new Date(time + delta);
	}

	/**
	 * 将yyyyMMdd形式的日期格式化成yyyy-MM-dd形式
	 * @Title formatDate
	 * @Description
	 * @param date
	 * @return
	 */
	public static String formatDate(String date) {
		if (dateLength != date.length()) {
			return date;
		}
		return date.substring(0, 4) + "-" + date.substring(4, 6) + "-" + date.substring(6);
	}

	public static String formatDateTime(String dateTime) {
		if (dateTimeLength != dateTime.length()) {
			return dateTime;
		}
		return dateTime.substring(0, 4) + "-" + dateTime.substring(4, 6) + "-" + dateTime.substring(6, 8) + " "
				+ dateTime.substring(8, 10) + ":" + dateTime.substring(10, 12) + ":" + dateTime.substring(12);
	}

	public static String formatDateByDate(Date date) {
		Long longDate = formatDate(date);
		String dateStr = String.valueOf(longDate);
		if (dateLength != dateStr.length()) {
			return dateStr;
		}
		return dateStr.substring(0, 4) + "-" + dateStr.substring(4, 6) + "-" + dateStr.substring(6);
	}

	/**
	 * 将HHmmss形式的时间格式化成HH:mm:ss形式
	 * @Title formatTime
	 * @Description
	 * @param time
	 * @return
	 */
	public static String formatTime(String time) {
		if (timeLength != time.length()) {
			return time;
		}
		return time.substring(0, 2) + ":" + time.substring(2, 4) + ":" + time.substring(4);
	}

	public static void main(String[] args) {
		Long loadingEndDt = Long.valueOf("20160616");
		Date loadingDt = getDate(loadingEndDt, FORMAT_TYPE.DATE);
		Date sdt = add(loadingDt, Calendar.DAY_OF_YEAR, -2);
		System.out.println(formatDate(sdt));

		//
		System.out.println(fetchNow(FORMAT_TYPE.DATE));

		Date date = new Date();
		// date to long
		System.out.println(formatDate(date));
		System.out.println(formatDateTime(date));

		// add
		Date newDate = add(date, Calendar.HOUR, 1);
		System.out.println(formatDateTime(newDate));

		// sub
		newDate = add(date, Calendar.DATE, -1);
		System.out.println(formatDateTime(newDate));

		// long to date
		Long yyyyMMddHHmmss = Long.valueOf(20160603134530L);
		newDate = getDate(yyyyMMddHHmmss, FORMAT_TYPE.DATETIME);
		System.out.println(formatDateTime(newDate));
		Long yyyyMMdd = Long.valueOf(20160603L);
		newDate = getDate(yyyyMMdd, FORMAT_TYPE.DATE);
		System.out.println(formatDate(newDate));

		System.out.println(formatDate("20160818"));
		System.out.println(formatTime("144400"));
		System.out.println(formatDateTime("20160818144400"));

		String str = new String("2015-05-05");
		System.out.println(str.replace("-", ""));

	}

	/**
	 * 将8位数的日期格式转化为5位
	 * @return
	 */
	public static long convertToYMMDD(Date date) {
		Long time = DateUtil.formatDate(date);
		return Long.parseLong(String.valueOf(time).substring(3, 8));
	}

}
