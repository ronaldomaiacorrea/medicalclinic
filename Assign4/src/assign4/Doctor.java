package assign4;

import java.io.Serializable;

/* 
 * File Name: Doctor.java
 * Course Name: Object Oriented Programming 
 * Lab Section: CST8284
 * Student Name: Ronaldo Maia Correa
 * Date: 2018-11-26
 *
 * This Class defines fields and actions about the class Doctor.
 */
public class Doctor implements Serializable {
	private String firstName;
	private String lastName;
	private String specialty;

	/* Doctor overloaded constructor */
	public Doctor(String firstName, String lastName, String specialty) {

		setFirstName(firstName);
		setLastName(lastName);
		setSpecialty(specialty);

	}

	/* Doctor default constructor */
	public Doctor() {

		this("unknown" , "unknown" , "unknown");
	}

	/* Provides Doctor First Name */
	public String getFirstName() {

		return firstName;
	}

	/* set the doctor first name */
	private void setFirstName(String firstName) {

		this.firstName = firstName;

	}

	/* Provides Doctor last name */
	public String getLastName() {
		return lastName;
	}

	/* set the doctor last name */
	private void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/* Provides Doctor specialty */
	public String getSpecialty() {
		return specialty;
	}

	/* set the doctor specialty */
	private void setSpecialty(String specialty) {
		this.specialty = specialty;
	}

	@Override
	public String toString() {
		return lastName + " " + firstName + ", " + specialty;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((specialty == null) ? 0 : specialty.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {

		if ( obj == null ) return false;

		if ( !(obj instanceof Doctor) ) return false;

		Doctor doc = (Doctor) obj;

		return this.getFirstName().equals(doc.getFirstName()) && this.getLastName().equals(doc.getLastName()) 
				&& this.getSpecialty().equals(doc.getSpecialty());
	}
}//end of class
