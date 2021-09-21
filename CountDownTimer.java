package project1;

import java.io.*;
import java.util.*;

/**
 * A timer with various controls over the length in hours, minutes, and seconds
 *
 * @author Torian Hemphill, Getrude Muthiani
 * @version September 21, 2021
 */

public class CountDownTimer {
	/** current number of hours */
	int hours;

	/** current number of minutes */
	int minutes;

	/** current number of seconds */
	int seconds;

	/** whether the timer is suspended or not */
	static boolean isSuspended;

	/** returns the current hours */
	public int getHours() {
		return hours;
	}

	/*****************************************************************
	 * set the number of hours to the specified amount
	 * 
	 * @param hours the desired number of hours
	 * @throws IllegalArgumentException invalid number for hours
	 *****************************************************************/
	public void setHours(int hours) {
		if (!isSuspended) {
			if (hours >= 0 && hours <= 24)
				this.hours = hours;
			else
				throw new IllegalArgumentException();
		}
	}

	/** returns the current minutes */
	public int getMinutes() {
		return minutes;
	}

	/*****************************************************************
	 * set the number of minutes to the specified amount
	 * 
	 * @param minutes the desired number of minutes
	 * @throws IllegalArgumentException invalid number for minutes
	 *****************************************************************/
	public void setMinutes(int minutes) {
		if (!isSuspended) {
			if (minutes >= 0 && minutes < 60)
				this.minutes = minutes;
			else
				throw new IllegalArgumentException();
		}
	}

	/** returns the current seconds */
	public int getSeconds() {
		return seconds;
	}

	/*****************************************************************
	 * set the number of seconds to the specified amount
	 * 
	 * @param seconds the desired number of seconds
	 * @throws IllegalArgumentException invalid number for seconds
	 *****************************************************************/
	public void setSeconds(int seconds) {
		if (!isSuspended) {
			if (seconds >= 0 && seconds < 60)
				this.seconds = seconds;
			else
				throw new IllegalArgumentException();
		}
	}

	/*****************************************************************
	 * Constructor creates a CountDownTimer set to zero
	 *****************************************************************/
	public CountDownTimer() {
		this.hours = 0;
		this.minutes = 0;
		this.minutes = 0;
	}

	/*****************************************************************
	 * Constructor creates a CountDownTimer of specified size
	 * 
	 * @param hours   the number of hours
	 * @param minutes the number of minutes
	 * @param seconds the number of seconds
	 * @throws IllegalArgumentException invalid number for hours, minutes, or
	 *                                  seconds
	 *****************************************************************/
	public CountDownTimer(int hours, int minutes, int seconds) {
		if (hours >= 0 && hours <= 24)
			this.hours = hours;
		else
			throw new IllegalArgumentException();

		if (minutes >= 0 && minutes < 60)
			this.minutes = minutes;
		else
			throw new IllegalArgumentException();

		if (seconds >= 0 && seconds < 60)
			this.seconds = seconds;
		else
			throw new IllegalArgumentException();
	}

	/*****************************************************************
	 * Constructor creates a CountDownTimer of specified size
	 * 
	 * @param minutes the number of minutes
	 * @param seconds the number of seconds
	 * @throws IllegalArgumentException invalid number for minutes or seconds
	 *****************************************************************/
	public CountDownTimer(int minutes, int seconds) {
		this.hours = 0;

		if (minutes >= 0 && minutes < 60)
			this.minutes = minutes;
		else
			throw new IllegalArgumentException();

		if (seconds >= 0 && seconds < 60)
			this.seconds = seconds;
		else
			throw new IllegalArgumentException();

	}

	/*****************************************************************
	 * Constructor creates a CountDownTimer of specified size
	 * 
	 * @param seconds the number of seconds
	 * @throws IllegalArgumentException invalid number for seconds
	 *****************************************************************/
	public CountDownTimer(int seconds) {
		this.hours = 0;
		this.minutes = 0;
		if (seconds >= 0 && seconds < 60)
			this.seconds = seconds;
		else
			throw new IllegalArgumentException();
	}

	/*****************************************************************
	 * Constructor creates a CountDownTimer of the same size as another
	 * CountDownTimer
	 * 
	 * @param other a separate CountDownTimer
	 *****************************************************************/
	public CountDownTimer(CountDownTimer other) {
		this.hours = other.hours;
		this.minutes = other.minutes;
		this.seconds = other.seconds;
	}

	/*****************************************************************
	 * Constructor creates a CountDownTimer of size specified by a string
	 * 
	 * @param startTime a string representing the desired length of time
	 * @throws IllegalArgumentException invalid string for startTime
	 *****************************************************************/
	public CountDownTimer(String startTime) {

		// turns the given string into an array containing the values
		String[] startTimeArr = startTime.split(":");

		// if the string specifies all three values
		if (startTimeArr.length == 3) {
			this.hours = Integer.parseInt(startTimeArr[0]);
			this.minutes = Integer.parseInt(startTimeArr[1]);
			this.seconds = Integer.parseInt(startTimeArr[2]);
		}
		// if the string specifies only minutes and seconds
		else if (startTimeArr.length == 2) {
			this.hours = 0;
			this.minutes = Integer.parseInt(startTimeArr[0]);
			this.seconds = Integer.parseInt(startTimeArr[1]);
		}
		// if the string specifies only seconds
		else if (startTimeArr.length == 1) {
			this.hours = 0;
			this.minutes = 0;
			this.seconds = Integer.parseInt(startTimeArr[0]);
		} else
			throw new IllegalArgumentException();
	}

	/*****************************************************************
	 * A method that returns whether the given objects is equal to the current
	 * CountDownTimer
	 * 
	 * @param other an object to compare to the current CountDownTimer
	 * @throws IllegalArgumentException invalid value or type for other
	 *****************************************************************/
	@Override
	public boolean equals(Object other) {
		// Throws an exception if the given object is null
		if (other == null)
			throw new IllegalArgumentException();
		// if the objects is a CountDownTimer, typecast and compare to current
		// CountDownTimer
		else if (other instanceof CountDownTimer) {
			CountDownTimer given = (CountDownTimer) other;
			return ((this.hours == given.hours) && (this.minutes == given.minutes) && (this.seconds == given.seconds));
		}
		// Throws an exception if the object is any other type
		else
			throw new IllegalArgumentException();
	}

	/*****************************************************************
	 * A method that returns whether two CountDownTimers are equal to each other
	 * 
	 * @param t1 the first CountDownTimer
	 * @param t2 the second CountDownTimer
	 *****************************************************************/
	public static boolean equals(CountDownTimer t1, CountDownTimer t2) {
		if ((t1.hours == t2.hours) && (t1.minutes == t2.minutes) && (t1.seconds == t2.seconds)) {
			return true;
		} else
			return false;
	}

	/*****************************************************************
	 * A method that compares a given CountDownTimer to the current CountDownTimer
	 * 
	 * @param other a CountDownTimer to compare to the current CountDownTimer
	 * @return 1 if the current CountDownTimer is bigger than the given
	 *         CountDownTimer, -1 if the current CountDownTimer is smaller than the
	 *         given CountDownTimer, 0 if the two CountDownTimers are equal
	 *****************************************************************/
	public int compareTo(CountDownTimer other) {
		// the total time in the other CountDownTimer in seconds
		int otherTotal = (other.hours * 3600) + (other.minutes * 60) + other.seconds;

		// the total time in the current CountDownTimer in seconds
		int thisTotal = (this.hours * 3600) + (this.minutes * 60) + this.seconds;

		if (thisTotal > otherTotal) {
			return 1;
		}
		if (thisTotal < otherTotal) {
			return -1;
		} else
			return 0;
	}

	/*****************************************************************
	 * A method that compares two CountDownTimers to each other
	 * 
	 * @param other a CountDownTimer to compare to the current CountDownTimer
	 * @return 1 if the first CountDownTimer is bigger than the second, -1 if the
	 *         first CountDownTimer is smaller than the second, 0 if the two
	 *         CountDownTimers are equal
	 *****************************************************************/
	public static int compareTo(CountDownTimer t1, CountDownTimer t2) {
		// the total time in the first CountDownTimer in seconds
		int t1Total = (t1.hours * 3600) + (t1.minutes * 60) + t1.seconds;
		// the total time in the second CountDownTimer in seconds
		int t2Total = (t2.hours * 3600) + (t2.minutes * 60) + t2.seconds;

		if (t1Total > t2Total) {
			return 1;
		}
		if (t1Total < t2Total) {
			return -1;
		} else
			return 0;
	}

	/** Decrements the current CountDownTimer by 1 second */
	public void dec() {
		if (!isSuspended) {
			int thisTotal = (this.hours * 3600) + (this.minutes * 60) + this.seconds;
			thisTotal -= 1;
			this.hours = (thisTotal / 3600);
			thisTotal %= 3600;
			this.minutes = (thisTotal / 60);
			thisTotal %= 60;
			this.seconds = (thisTotal);
		}
	}

	/*****************************************************************
	 * A method that subtracts a given number of seconds from the current
	 * CountDownTimer
	 * 
	 * @param seconds the number of seconds to be subtracted
	 * @throws IllegalArgumentException the amount of seconds to be subtracted is
	 *                                  greater than the total time in the current
	 *                                  CountDownTimer
	 *****************************************************************/
	public void sub(int seconds) {
		if (!isSuspended) {
			int thisTotal = (this.hours * 3600) + (this.minutes * 60) + this.seconds;
			if (seconds < thisTotal) {
				thisTotal -= seconds;
				this.hours = (thisTotal / 3600);
				thisTotal %= 3600;
				this.minutes = (thisTotal / 60);
				thisTotal %= 60;
				this.seconds = (thisTotal);
			}
			// given seconds is greater than total time in current CountDownTimer
			else {
				throw new IllegalArgumentException();
			}
		}
	}

	/*****************************************************************
	 * A method that subtracts a given CountDownTimer from the current
	 * CountDownTimer
	 * 
	 * @param other the given CountDownTimer to be subtracted
	 *****************************************************************/
	public void sub(CountDownTimer other) {
		if (!isSuspended) {
			int otherTotal = (other.hours * 3600) + (other.minutes * 60) + other.seconds;
			sub(otherTotal);
		}
	}

	/** Increments the current CountDownTimer by 1 second */
	public void inc() {
		if (!isSuspended) {
			int thisTotal = (this.hours * 3600) + (this.minutes * 60) + this.seconds;
			thisTotal += 1;
			this.hours = (thisTotal / 3600);
			thisTotal %= 3600;
			this.minutes = (thisTotal / 60);
			thisTotal %= 60;
			this.seconds = (thisTotal);
		}
	}

	/*****************************************************************
	 * A method that adds a given number of seconds from the current CountDownTimer
	 * 
	 * @param seconds the number of seconds to be added
	 * @throws IllegalArgumentException the amount of seconds to be added is less
	 *                                  than zero
	 *****************************************************************/
	public void add(int seconds) {
		if (!isSuspended) {
			if (seconds > 0) {
				int thisTotal = (this.hours * 3600) + (this.minutes * 60) + this.seconds;

				// converts the total seconds back into hours, minutes, and seconds
				thisTotal += seconds;
				this.hours = (thisTotal / 3600);
				thisTotal %= 3600;
				this.minutes = (thisTotal / 60);
				thisTotal %= 60;
				this.seconds = (thisTotal);
			}
			// seconds is less than zero
			else
				throw new IllegalArgumentException();

		}
	}

	/*****************************************************************
	 * A method that adds a given CountDownTimer from the current CountDownTimer
	 * 
	 * @param other the given CountDownTimer to be added
	 *****************************************************************/
	public void add(CountDownTimer other) {
		if (!isSuspended) {
			int otherTotal = (other.hours * 3600) + (other.minutes * 60) + other.seconds;
			add(otherTotal);
		}
	}

	/*****************************************************************
	 * A method that return a string version of the current CountDownTimer
	 * 
	 * @return A string equivalent of the current CountDownTimer
	 *****************************************************************/
	public String toString() {
		String countdownString = Integer.toString(this.hours) + ":";

		// adds a zero before the minutes value if it is below ten
		if (this.minutes < 10) {
			countdownString = countdownString.concat("0" + Integer.toString(this.minutes) + ":");
		} else
			countdownString = countdownString.concat(Integer.toString(this.minutes) + ":");

		// adds a zero before the seconds value if it is below ten
		if (this.seconds < 10) {
			countdownString = countdownString.concat("0" + Integer.toString(this.seconds));
		} else
			countdownString = countdownString.concat(Integer.toString(this.seconds));

		return countdownString;
	}

	/*****************************************************************
	 * A method that saves the current CountDownTimer to a file
	 * 
	 * @param fileName the name the file should be saved as
	 * @throws IllegalArgumentException the file cannot be saved properly
	 *****************************************************************/
	public void save(String fileName) {
		PrintWriter out = null;
		try {
			out = new PrintWriter(new BufferedWriter(new FileWriter(fileName)));
			// prints each variable to a line in the file
			out.println(this.hours);
			out.println(this.minutes);
			out.println(this.seconds);
		}
		// problem reading the file
		catch (IOException e) {
			throw new IllegalArgumentException();
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}

	/*****************************************************************
	 * A method that loads information from the file into the current CountDownTimer
	 * 
	 * @param fileName the name of the file to be loaded
	 * @throws IllegalArgumentException the file cannot be loaded properly
	 *****************************************************************/
	public void load(String fileName) {
		try {
			// open the data file
			Scanner fileReader = new Scanner(new File(fileName));
			// sets each variable to the values on the corresponding line of the file
			setHours(fileReader.nextInt());
			setMinutes(fileReader.nextInt());
			setSeconds(fileReader.nextInt());
		}
		// problem reading the file
		catch (Exception error) {
			throw new IllegalArgumentException();
		}
	}

	/*****************************************************************
	 * A method that sets whether or not the timer is suspended
	 * 
	 * @param suspend whether or not the timer is suspended
	 *****************************************************************/
	public static void setSuspend(boolean suspend) {
		isSuspended = suspend;
	}

	/** returns whether or not the timer is suspended */
	public static boolean isSuspended() {
		if (isSuspended)
			return true;
		else
			return false;

	}
}
