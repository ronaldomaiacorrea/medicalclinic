package assign4;
/* 
 * File Name: MedicalClinicException.java
 * Course Name: Object Oriented Programming 
 * Lab Section: CST8284
 * Student Name: Ronaldo Maia Correa
 * Date: 2018-11-26
 *
 * This Class extends RuntimeException class
 * 
 */
public class MedicalClinicException extends RuntimeException {
	
	private String message;

	public MedicalClinicException(String message) {
		super(message);
		
	}

	public MedicalClinicException() {
		super();
	}	

}
