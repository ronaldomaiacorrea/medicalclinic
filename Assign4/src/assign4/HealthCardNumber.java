package assign4;

import java.io.Serializable;

/* 
 * File Name: HealthCardNumber.java
 * Course Name: Object Oriented Programming 
 * Lab Section: CST8284
 * Student Name: Ronaldo Maia Correa
 * Date: 2018-11-26
 *
 * This Class defines fields and actions about the class HealthCardNumber
 * 
 */
public class HealthCardNumber implements Serializable {
	private String healthCardNumber;

	/* overloaded constructor */
	public HealthCardNumber(String healthCardNumber) {
		setHealthCardNumber(healthCardNumber);
	}

	/* default constructor */
	public HealthCardNumber() {
		this("000000000");
	}

	/* provides healthCardNumber info*/
	public String getHealthCardNumber() {
		return healthCardNumber;
	}
	
	/* set healthCard number*/
	private void setHealthCardNumber(String healthCardNumber) {
		
		if (healthCardNumber.length() != 9 ) 
			throw new MedicalClinicException("Health Card Number must be nine digits in length.");
		
		this.healthCardNumber = healthCardNumber;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((healthCardNumber == null) ? 0 : healthCardNumber.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if ( obj == null ) return false;

		if ( !(obj instanceof HealthCardNumber) ) return false;

		HealthCardNumber hcn = (HealthCardNumber) obj;

		return this.getHealthCardNumber().equals(hcn.healthCardNumber);
	}

	@Override
	public String toString() {
		return healthCardNumber;
	}
}
