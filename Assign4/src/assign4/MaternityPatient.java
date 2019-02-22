package assign4;



/* 
 * File Name: MaternityPatient.java
 * Course Name: Object Oriented Programming 
 * Lab Section: CST8284
 * Student Name: Ronaldo Maia Correa
 * Date: 2018-11-26
 *
 * This Class defines fields and actions about the subclass Maternity patient 
 * which Patient class is the parent (super class).
 */

public class MaternityPatient extends Patient {
	private OurDate dueDate;
	private boolean nutritionTesting;
	
	/* default constructor */
	public MaternityPatient() {
	
		this("unknown", "unknown", new HealthCardNumber(), new OurDate(), new OurDate(), false);
	
	}

	/* overloaded constructor */
	public MaternityPatient(String firstName, String lastName, HealthCardNumber healthCardNumber, OurDate birthDate, 
			OurDate dueDate, boolean nutritionTesting) {
		
		super(firstName, lastName, healthCardNumber, birthDate);
		setDueDate(dueDate);
		setNutritionTesting(nutritionTesting);
				
	}

	/* return newborn due date */
	public OurDate getDueDate() {
		return dueDate;
	}
	
	/* return nutrition test condition */
	public boolean isNutritionTesting() {
		return nutritionTesting;
	}

	/* set the newborn due date */
	private void setDueDate(OurDate dueDate) {
				
		OurDate today = new OurDate();
		
		if ((dueDate.getYear() < today.getYear()) 
				|| (dueDate.getYear() == today.getYear() && dueDate.getMonth() < today.getMonth()) 
				|| (dueDate.getYear() == today.getYear() && dueDate.getMonth() == today.getMonth() && dueDate.getDay() <= today.getDay())) 
			throw new MedicalClinicException("Invalid due date!\nEnter a day after today");
		
		this.dueDate = dueDate;
	}

	/* set the nutrition test condition */
	private void setNutritionTesting(boolean nutritionTesting) {
		this.nutritionTesting = nutritionTesting;
	}

	@Override
	public String toString() {
				
		return super.toString() + ", Due Date: " + dueDate + ", Nutrition Testing: " + nutritionTesting;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((dueDate == null) ? 0 : dueDate.hashCode());
		result = prime * result + (nutritionTesting ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if ( obj == null ) {
			return false;
		}
		
		if ( !(obj instanceof MaternityPatient) ) {
			return false;
		}
		
		MaternityPatient mp = (MaternityPatient) obj;
		
		return super.equals(mp) && this.getDueDate().equals(mp.getDueDate()) 
				&& this.isNutritionTesting() == mp.isNutritionTesting();
	}
}//end of class
