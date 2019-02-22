package assign4;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/* 
 * File Name: OurDate.java
 * Course Name: Object Oriented Programming 
 * Lab Section: CST8284
 * Student Name: Ronaldo Maia Correa
 * Date: 2018-11-26
 *
 * MedicalClinic is a class which keeps track of appointments 
 * for doctors and respective patients.
 */

public class MedicalClinic {

	private static int MAXAPPOINTMENTS;
	private static int MAXPATIENTS;
	private static int MAXDOCTORS;
	private int numberAppointments;
	private int numberPatients;
	private int numberDoctors;

	private ArrayList<Appointment> appointments = new ArrayList<Appointment>();
	private ArrayList<Patient> patients = new ArrayList<Patient>();
	private ArrayList<Patient> patientsFile;
	private ArrayList<Doctor> doctors = new ArrayList<Doctor>();

	/* default constructor */
	public MedicalClinic() {
		MAXAPPOINTMENTS = 5;
		MAXDOCTORS = 5;
		MAXPATIENTS = 5;

		doctors.add(new Doctor("Singh", "Vikash", "endocrinologist"));
		doctors.add(new Doctor("Miller", "Susan", "cardiologist"));
		doctors.add(new Doctor("Do", "Thanh", "neurologist"));
		doctors.add(new Doctor("DaSilva", "Francois", "internist"));
		doctors.add(new Doctor("Chin", "Judy", "Family Physician"));

		numberAppointments = 0;
		numberPatients = 0;
		numberDoctors = 5;		
		
	}

	/* This method adds a Regular Patient into patients List */
	public void addPatient(String firstName, String lastName, HealthCardNumber healthCardNumber, OurDate date) {

		Patient p = new Patient(firstName, lastName, healthCardNumber, date);
		patients.add(p);
		numberPatients ++;
	}
	
	/* This method adds a Outside Patient into patients List */
	public void addPatient(String firstName, String lastName, HealthCardNumber healthCardNumber, 
			OurDate date, double distance, boolean mobility) {

		OutPatient op = new OutPatient(firstName, lastName, healthCardNumber, date, distance, mobility);
		patients.add(op);
		numberPatients ++;
	}

	/* This method adds a Maternity Patient into patients List */
	public void addPatient(String firstName, String lastName, HealthCardNumber healthCardNumber, OurDate birthDate, 
			OurDate dueDate, boolean nutritionTesting) {

		MaternityPatient mp = new MaternityPatient(firstName, lastName, healthCardNumber, birthDate, 
				dueDate,nutritionTesting);
		patients.add(mp);
		numberPatients ++;
	}


	/* This method adds an appointment into appointments List */
	public void addAppointment(Doctor doctor, Patient patient, OurDate date) {

		appointments.add(new Appointment(doctor, patient, date));
		numberAppointments++;

	}

	/* This method deletes an appointment of appointments List */
	public void cancelAppointment(int index) {

		appointments.remove(index);
		numberAppointments--;

	}
	
	public void filePatientsOut() throws IOException {
		try {
			
			FileOutputStream outputData = new FileOutputStream("PatientData.ser");
			
			ObjectOutputStream writePatientobject = new ObjectOutputStream(outputData);
			
			writePatientobject.writeObject(patients);
			
			writePatientobject.flush();
			
			writePatientobject.close();
			
			outputData.flush();
			
			outputData.close();	
			
			System.out.println("Successfully saved!\n");
			
		}
		
		catch (IOException e) {
			System.out.println("Problem saving file!\n");
			e.printStackTrace();			
		}
	}
	
	public void filePatientsIn() throws ClassNotFoundException  {
		
		try {
			
			FileInputStream inputData = new FileInputStream("PatientData.ser");
			
			ObjectInputStream inputObject = new ObjectInputStream(inputData);
			
			patientsFile = (ArrayList<Patient>) inputObject.readObject();
						
			inputObject.close();
			
			inputData.close();
								
			patients = patientsFile;	
			
			System.out.println("Successfully loaded");
			
		} 
		
		catch (IOException e) {
			System.out.println("Could not read the file! ");
			e.printStackTrace();
	
		}
		
			
	}
	
	
	
	/* provides list of appointments */
	public ArrayList<Appointment> getAppointments() {
		return appointments;
	}

	/* provides list of patients */
	public ArrayList<Patient> getPatients() {
		return patients;
	}

	/* provides list of doctors */
	public ArrayList<Doctor> getDoctors() {
		return doctors;
	}

	/* provides the number maximum of appointments */
	public static int getMAXAPPOINTMENTS() {
		return MAXAPPOINTMENTS;
	}

	/* provides the number maximum of patients */
	public static int getMAXPATIENTS() {
		return MAXPATIENTS;
	}

	/* provides the number maximum of doctors */
	public static int getMAXDOCTORS() {
		return MAXDOCTORS;
	}

	/* provides the actual number of appointments */
	public int getNumberAppointments() {
		return numberAppointments;
	}

	/* provides the actual number of patients */
	public int getNumberPatients() {
		return numberPatients;
	}

	/* provides the actual number of doctors */
	public int getNumberDoctors() {
		return numberDoctors;
	}
	
	
} //end of class























