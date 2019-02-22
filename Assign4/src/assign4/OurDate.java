package assign4;

import java.io.Serializable;

/* 
 * File Name: OurDate.java
 * Course Name: Object Oriented Programming 
 * Lab Section: CST8284
 * Student Name: Ronaldo Maia Correa
 * Date: 2018-11-26
 *
 * This Class defines a date which is used as reference to patient birth date and appointment date
 * 
 */

import java.util.Calendar;

public class OurDate implements Serializable {
	private static final Calendar CAL = Calendar.getInstance();
	private int day;
	private int month;
	private int year;

	/* default constructor */
	public OurDate() {

		this( CAL.get(Calendar.DATE), CAL.get(Calendar.MONTH)+1, CAL.get(Calendar.YEAR));

	}

	/* overloaded constructor */
	public OurDate(int day, int month, int year) {
		setDay(day);
		setMonth(month);
		setYear(year);
	}

	/* Return the day(integer) */
	public int getDay() {
		return day;
	}

	/* set the day(integer) */
	private void setDay(int day) {
		this.day = day;
	}

	/* Return the month(integer) */
	public int getMonth() {
		return month;
	}

	/* set the month(integer) */
	private void setMonth(int month) {

		if (month > 12 || month < 1) 
			throw new MedicalClinicException("Please, enter a valid month (between 1 and 12)");

		if ((month == 1 || month == 3 || month == 5 
				|| month == 7 || month == 8 || month == 10 || month == 12) && (this.getDay() < 1 || this.getDay() > 31))
			throw new MedicalClinicException("Please, enter a valid day (between 1 and 31)");

		if ((month == 4 || month == 6 || month == 9 
				|| month == 11) && (this.getDay() < 1 || this.getDay() > 30))
			throw new MedicalClinicException("Please, enter a valid day (between 1 and 30)");

		this.month = month;

	}

	/* Return the year(integer) */
	public int getYear() {
		return year;

	}

	/* set the year(integer) */
	private void setYear(int year) {
		// Begin of a reference code used to check the Leap Year 
		// Reference: https://www.programiz.com/java-programming/examples/leap-year
		boolean leap = false;		
		if( year % 4 == 0 )
		{
			if( year % 100 == 0 ) 
			{
				// year is divisible by 400, hence the year is a leap year
				if ( year % 400 == 0 )
					leap = true;
				else
					leap = false;
			}
			else leap = true;
		}
		else leap = false; // End of the reference code

		if (leap) {
			if ( this.getMonth() == 2 && this.getDay() > 29 ) throw new MedicalClinicException ("Please, enter a valid day (between 1 and 29)"); 
		} else {
			if ( this.getMonth() == 2 && this.getDay() > 28 ) throw new MedicalClinicException ("Please, enter a valid day (between 1 and 28)");
		}

		this.year = year;
	}

	@Override
	public String toString() {
		return day + "/" + month + "/" + year;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + day;
		result = prime * result + month;
		result = prime * result + year;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if ( obj == null ) return false;

		if ( !(obj instanceof OurDate) ) return false;

		OurDate od = (OurDate) obj;

		return this.getDay() == od.getDay() && this.getMonth() == od.getMonth() 
				&& this.getYear() == od.getYear();

	}
}//end of class
