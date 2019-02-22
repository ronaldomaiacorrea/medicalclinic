package assign4;

import java.io.Serializable;

/* 
 * File Name: Patient.java
 * Course Name: Object Oriented Programming 
 * Lab Section: CST8284
 * Student Name: Ronaldo Maia Correa
 * Date: 2018-11-26
 *
 * This Class defines fields and actions about the class patient. 
 */

public class Patient implements Serializable {
	
	private String firstName;
	private String lastName;
	private HealthCardNumber healthCardNumber;
	private OurDate birthDate;

	/* Patient default constructor */
	public Patient() {

		this("unknown", "unknown", new HealthCardNumber(), new OurDate());

	}

	/* Patient overloaded constructor */
	public Patient(String firstName, String lastName, HealthCardNumber healthCardNumber, OurDate birthDate) {

		setFirstName(firstName);
		setLastName(lastName);
		setHealthCardNumber(healthCardNumber);
		setBirthDate(birthDate);

	}
	
	/* Provides Patient First Name */
	public String getFirstName() {
		return firstName;
	}

	/* Provides Patient Last Name */
	public String getLastName() {
		return lastName;
	}

	/* Provides Patient health card number */
	public HealthCardNumber getHealthCardNumber() {
		return healthCardNumber;
	}

	/* Provides Patient birth date */
	public OurDate getBirthDate() {
		return birthDate;
	}

	/* set the patient first name */
	private void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/* set the patient last name */
	private void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/* set the patient health card number */
	private void setHealthCardNumber(HealthCardNumber healthCardNumber) {
		this.healthCardNumber = healthCardNumber;
	}

	/* set the patient birth date */
	private void setBirthDate(OurDate birthDate) {
		
		OurDate today = new OurDate();
		if (birthDate.getYear() < 1900) 
			throw new MedicalClinicException("CONGRATULATIONS CENTENARIAN! PLEASE SIGN UP WITH GERIATRICS");
		
		if (birthDate.getYear() > today.getYear()) throw new MedicalClinicException("Please, enter a year equal or before " + today.getYear());

		if ((birthDate.getYear() == today.getYear() && birthDate.getMonth() > today.getMonth()) 
				|| (birthDate.getYear() == today.getYear() && birthDate.getMonth() == today.getMonth() 
				&& birthDate.getDay() >= today.getDay())) 
				throw new MedicalClinicException("Invalid birth date!");
		
		this.birthDate = birthDate;				
	}

	@Override
	public String toString() {
		return lastName + ", " + firstName + ", Health Card Number: " + healthCardNumber
				+ ", dob: " + birthDate;
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((birthDate == null) ? 0 : birthDate.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((healthCardNumber == null) ? 0 : healthCardNumber.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if ( obj == null ) return false;

		if ( !(obj instanceof Patient) ) return false;

		Patient p = (Patient) obj;

		return this.getFirstName().equals(p.getFirstName()) && this.getLastName().equals(p.getLastName()) && 
				this.getHealthCardNumber().equals(p.healthCardNumber) && this.getBirthDate().equals(p.getBirthDate());
	}
	
}//end of class







