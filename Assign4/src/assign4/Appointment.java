package assign4;

import java.io.Serializable;

/* 
 * File Name: Appointment.java
 * Course Name: Object Oriented Programming 
 * Lab Section: CST8284
 * Student Name: Ronaldo Maia Correa
 * Date: 2018-11-26
 *
 * This Class defines fields and actions about the class Appointment.
 */

public class Appointment implements Serializable {
	private Doctor doctor;
	private Patient patient;
	private OurDate appointmentDate;

	/* default constructor */
	public Appointment() {

		this(new Doctor(), new Patient(), new OurDate());
	}

	/* overloaded constructor */
	public Appointment(Doctor doctor, Patient patient, OurDate appointmentDate) {

		setDoctor(doctor);
		setPatient(patient);
		setAppointmentDate(appointmentDate);
	}

	/* return doctor object */
	public Doctor getDoctor() {
		return doctor;
	}

	/* return patient object */
	public Patient getPatient() {
		return patient;
	}

	/* return appointment date object */
	public OurDate getAppointmentDate() {
		return appointmentDate;
	}

	/* set doctor data */
	private void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	/* set patient data */ 
	private void setPatient(Patient patient) {
		this.patient = patient;
	}

	/* set appointment date data */ 
	private void setAppointmentDate(OurDate appointmentDate) {
		
		OurDate today = new OurDate();
		
		if ((appointmentDate.getYear() < today.getYear()) 
				|| (appointmentDate.getYear() == today.getYear() 
				&& appointmentDate.getMonth() < today.getMonth()) 
				|| (appointmentDate.getYear() == today.getYear() 
				&& appointmentDate.getMonth() == today.getMonth() 
				&& appointmentDate.getDay() <= today.getDay())) 
			throw new MedicalClinicException("Invalid appointment date!\nEnter a day after today");
				
		this.appointmentDate = appointmentDate;
	}

	@Override
	public String toString() {
		return "Appointment Date: " + appointmentDate + ", Dr. " + doctor.toString() + ", " + patient.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((appointmentDate == null) ? 0 : appointmentDate.hashCode());
		result = prime * result + ((doctor == null) ? 0 : doctor.hashCode());
		result = prime * result + ((patient == null) ? 0 : patient.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {

		if ( obj == null ) return false;

		if ( !(obj instanceof Appointment) ) return false;

		Appointment ap = (Appointment) obj;

		return this.getDoctor().equals(ap.getDoctor()) && this.getPatient().equals(ap.getPatient()) 
				&& this.getAppointmentDate().equals(ap.getAppointmentDate());
	}	
}//end of class
