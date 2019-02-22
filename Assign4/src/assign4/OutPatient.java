package assign4;



/* 
 * File Name: OutPatient.java
 * Course Name: Object Oriented Programming 
 * Lab Section: CST8284
 * Student Name: Ronaldo Maia Correa
 * Date: 2018-11-26
 *
 * This Class defines fields and actions about the subclass Outside patient 
 * which Patient class is the parent (super class).
 */

public class OutPatient extends Patient {
	private double distanceFromClinic;
	private boolean mobility;

	/* default constructor */
	public OutPatient() {

		this("unknown", "unknown", new HealthCardNumber(), new OurDate(), -1, false);
		
	}

	/* overloaded constructor */	
	public OutPatient(String firstName, String lastName, HealthCardNumber healthCardNumber, OurDate birthDate, 
			double distanceFromClinic, boolean mobility) {

		super(firstName, lastName, healthCardNumber, birthDate);
		setDistanceFromClinic(distanceFromClinic);
		setMobility(mobility);

	}

	/* return the distance from clinic */
	public double getDistanceFromClinic() {
		return distanceFromClinic;
	}

	/* return the outside patient mobility condition */ 
	public boolean isMobility() {
		return mobility;
	}

	/* set the outside patient distance from clinic*/
	private void setDistanceFromClinic(double distanceFromClinic) {
		this.distanceFromClinic = distanceFromClinic;
	}

	/* set the outside patient mobility condition*/
	private void setMobility(boolean mobility) {
		this.mobility = mobility;
	}

	@Override
	public String toString() {
		return super.toString() + ", distanceFromClinic: " + distanceFromClinic + ", mobility: " + mobility;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		long temp;
		temp = Double.doubleToLongBits(distanceFromClinic);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + (mobility ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {

		if ( obj == null ) return false;

		if ( !(obj instanceof OutPatient) ) return false;

		OutPatient op = (OutPatient) obj;

		return super.equals(op) && this.getDistanceFromClinic() == op.getDistanceFromClinic() && this.isMobility() == op.isMobility();
	}
}//end of class	

