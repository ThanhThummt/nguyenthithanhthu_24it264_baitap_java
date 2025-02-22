package c3_5;

import java.util.Calendar;

public class MyDate {
	private int year;
	private int month;
	private int day;

	private static final String[] MONTHS = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct",
			"Nov", "Dec" };

	private static final String[] DAYS = { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday",
			"Saturday" };

	private static final int[] DAYS_IN_MONTHS = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

	// Constructor
	public MyDate(int year, int month, int day) {
		if (isValidDate(year, month, day)) {
			this.year = year;
			this.month = month;
			this.day = day;
		} else {
			throw new IllegalArgumentException("Invalid date!");
		}
	}

	public boolean isLeapYear(int year) {
		return (year % 400 == 0) || (year % 4 == 0 && year % 100 != 0);
	}

	public boolean isValidDate(int year, int month, int day) {
		if (year < 1 || year > 9999 || month < 1 || month > 12 || day < 1) {
			return false;
		}

		int maxDay = DAYS_IN_MONTHS[month - 1];
		if (month == 2 && isLeapYear(year)) {
			maxDay = 29;
		}

		return day <= maxDay;
	}

	public static int getDayOfWeek(int year, int month, int day) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month - 1, day);
		return calendar.get(Calendar.DAY_OF_WEEK) - 1;
	}

	public void setDate(int year, int month, int day) {
		if (isValidDate(year, month, day)) {
			this.year = year;
			this.month = month;
			this.day = day;
		} else {
			throw new IllegalArgumentException("Invalid date!");
		}
	}

	public int getYear() {
		return year;
	}

	public int getMonth() {
		return month;
	}

	public int getDay() {
		return day;
	}

	public void setYear(int year) {
		if (year >= 1 && year <= 9999) {
			this.year = year;
		} else {
			throw new IllegalArgumentException("Invalid year!");
		}
	}

	public void setMonth(int month) {
		if (month >= 1 && month <= 12) {
			this.month = month;
		} else {
			throw new IllegalArgumentException("Invalid month!");
		}
	}

	public void setDay(int day) {
		if (isValidDate(this.year, this.month, day)) {
			this.day = day;
		} else {
			throw new IllegalArgumentException("Invalid day!");
		}
	}

	public String toString() {
		return DAYS[getDayOfWeek(year, month, day)] + " " + day + " " + MONTHS[month - 1] + " " + year;
	}

	public MyDate nextDay() {
		if (isValidDate(year, month, day + 1)) {
			return new MyDate(year, month, day + 1);
		} else if (month == 12) {
			return new MyDate(year + 1, 1, 1);
		} else {
			return new MyDate(year, month + 1, 1);
		}
	}

	public MyDate nextMonth() {
		if (month == 12) {
			return new MyDate(year + 1, 1, day);
		} else {
			return new MyDate(year, month + 1, day);
		}
	}

	public MyDate nextYear() {
		if (year == 9999) {
			throw new IllegalArgumentException("Year out of range!");
		}
		return new MyDate(year + 1, month, day);
	}

	public MyDate previousDay() {
		if (isValidDate(year, month, day - 1)) {
			return new MyDate(year, month, day - 1);
		} else if (month == 1) {
			return new MyDate(year - 1, 12, 31);
		} else {
			int prevMonthDays = DAYS_IN_MONTHS[month - 2];
			if (month == 3 && isLeapYear(year)) {
				prevMonthDays = 29;
			}
			return new MyDate(year, month - 1, prevMonthDays);
		}
	}

	public MyDate previousMonth() {
		if (month == 1) {
			return new MyDate(year - 1, 12, day);
		} else {
			return new MyDate(year, month - 1, day);
		}
	}

	public MyDate previousYear() {
		if (year == 1) {
			throw new IllegalArgumentException("Year out of range!");
		}
		return new MyDate(year - 1, month, day);
	}
}