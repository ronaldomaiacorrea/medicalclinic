package assign4;

import java.io.IOException;
import java.util.Scanner;

/* 
 * File Name: MedicalClinicUserInterface.java
 * Course Name: Object Oriented Programming 
 * Lab Section: CST8284
 * Student Name: Ronaldo Maia Correa
 * Date: 2018-11-26
 *
 * This Class is the user interface to gather data for the the Medical Clinic class  
 * 
 */

public class MedicalClinicUserInterface {

	private static final int ADD_PATIENT = 1;
	private static final int ADD_APPOINTMENT = 2;
	private static final int CANCEL_APPOINTMENT = 3;
	private static final int LIST_APPOINTMENTS = 4;
	private static final int WRITEFILE = 5;
	private static final int LOADFILE = 6;
	private static final int QUIT = 7;
	private MedicalClinic clinic;
	private Scanner in = new Scanner(System.in);

	/* default constructor */
	public MedicalClinicUserInterface() {

		this.clinic = new MedicalClinic();

	}

	/* main method - executes the program */
	public static void main(String[] args) throws ClassNotFoundException, IOException {

		MedicalClinicUserInterface medUser = new MedicalClinicUserInterface();
		medUser.menu();		


	}

	/* Prompts the menu to the user to select the respective method*/

	public void menu() throws ClassNotFoundException, IOException {

		int choice;

		do { 

			System.out.println("\nPlease make a choice:");
			System.out.println("1. Enter a new patient");
			System.out.println("2. Make an appointment for a patient");
			System.out.println("3. Cancel an appointment for a patient");
			System.out.println("4. List all appointments");
			System.out.println("5. Write Patient data to a file");
			System.out.println("6. Load Patient data");
			System.out.println("7. Quit");
			// System.out.println("8. List all doctors"); // in case if the client wants to list the doctors
			// System.out.println("9. List all patients"); // in case if the client wants to list the patients

			choice = in.nextInt();
			in.nextLine(); 

			switch (choice) {
			case MedicalClinicUserInterface.ADD_PATIENT: 
				this.addPatient();
				break;

			case MedicalClinicUserInterface.ADD_APPOINTMENT: 
				this.addAppointment();
				break;

			case MedicalClinicUserInterface.CANCEL_APPOINTMENT: 
				this.cancelAppointment();
				break;

			case MedicalClinicUserInterface.LIST_APPOINTMENTS:
				this.listAppointments();
				break;

			case MedicalClinicUserInterface.WRITEFILE:
				this.writePatientsOut();
				break;

			case MedicalClinicUserInterface.LOADFILE:
				this.readPatientsIn();
				break;

			case MedicalClinicUserInterface.QUIT:
				break;

			case 8:
				System.out.println("You have found a secret key. This option list the doctors");
				this.printDoctors();
				break;

				// (optional feature) list all the patients
			case 9: 
				System.out.println("You have found a secret key. This option list the patients");
				this.printPatients();
				break;

			default:
				System.out.println("INVALID SELECTION!");

			}
		} while (choice != MedicalClinicUserInterface.QUIT);

		System.out.println("GoodBye!");
	}

	/* This method send arguments to Medical Clinic Class adding a new patient */
	public void addPatient() {

		Patient patient;
		MaternityPatient matPatient;
		OutPatient outPatient;
		String firstName;
		String lastName;
		HealthCardNumber healthCardNumber;
		String hcn;
		OurDate birthdate=null;
		OurDate dueDate;
		int patientType;
		double distance;
		int mobil;
		int nutrition;
		boolean nutritionTesting;
		boolean mobility;
		boolean isSafe=true;
		boolean registered=false;
		boolean isValid;
		boolean isFormatData;

		/* variables used to convert and split string date into integers */
		String date = null;
		int day = 0;
		int month = 0;
		int year = 0; 
		String dDate;
		int dDay = 0;
		int dMonth = 0;
		int dYear = 0;


		if (clinic.getPatients().size() == MedicalClinic.getMAXPATIENTS()) {

			System.out.println();
			System.out.println("You cannot add more patients!");

		} else { 

			System.out.print("Enter first name: ");
			firstName = in.next();
			System.out.print("Enter last name: ");
			lastName = in.next();

			try {
				System.out.print("Enter health card number: ");
				hcn = in.next();
				healthCardNumber = new HealthCardNumber (hcn);

			} catch (MedicalClinicException e) {
				System.out.println(e.getMessage());
				System.out.println();
				healthCardNumber = null;
				isSafe = false;
			}

			if (isSafe) {

				for (int i=0; i<clinic.getPatients().size();i++)

				{
					if ( clinic.getPatients().get(i).getHealthCardNumber().equals(healthCardNumber) )	

					{ 
						registered=true;
					}
				} 

				if (!registered){

					do {	
						isValid=true;

						do {
							isValid=true;

							do { 

								System.out.print("Enter birth date DDMMYYYY: ");
								date = in.next();
								isFormatData=this.testDateFormat(date);
							} while (!isFormatData);

							try {
								day = Integer.parseInt(date.substring(0, 2));
								month = Integer.parseInt(date.substring(2,4));
								year = Integer.parseInt(date.substring(4, 8));
								birthdate = new OurDate(day, month, year);
							}
							catch (MedicalClinicException e1) {
								System.out.println(e1.getMessage());
								System.out.println();
								birthdate = null;
								isValid=false;
							}

						} while (!isValid);

						try {

							patient = new Patient(firstName, lastName, healthCardNumber, birthdate);

						} catch (MedicalClinicException e2) {
							System.out.println(e2.getMessage());
							System.out.println();
							patient = null;
							isValid=false;
						}

					} while(!isValid);

					do {

						System.out.println("\nEnter type of patient:");
						System.out.println("1. Maternity patient");
						System.out.println("2. Outside patient");
						System.out.println("3. Regular patient");
						patientType = in.nextInt();

					} while ( !(patientType == 1 || patientType ==2 || patientType ==3 ) );

					switch (patientType) {

					case 1: 

						do {	
							isValid=true;

							do {
								isValid=true;

								do { 

									System.out.println("Enter the due date DDMMYYYY: ");
									dDate = in.next();
									isFormatData=this.testDateFormat(dDate);

								} while (!isFormatData);

								try {
									dDay = Integer.parseInt(dDate.substring(0, 2));
									dMonth = Integer.parseInt(dDate.substring(2,4));
									dYear = Integer.parseInt(dDate.substring(4, 8));
									dueDate = new OurDate(dDay, dMonth, dYear);

								} catch (MedicalClinicException e3) {
									System.out.println(e3.getMessage());
									dueDate = null;
									isValid=false;						
								}

							}while(!isValid);

							do {

								System.out.println("Enter nutrition testing: (1 for true, 0 for false): ");
								nutrition = in.nextInt();

							} while (!(nutrition==0 || nutrition ==1));

							nutritionTesting = (nutrition==1);

							try { 

								matPatient = new MaternityPatient(firstName, lastName, healthCardNumber, birthdate, dueDate, nutritionTesting); 
							}

							catch (MedicalClinicException e4) {
								System.out.println(e4.getMessage());
								matPatient = null;
								isValid=false;
							}

						}while(!isValid);

						clinic.addPatient(firstName, lastName, healthCardNumber, birthdate, dueDate, nutritionTesting);
						break;

					case 2: 

						System.out.println("Enter the distance from the clinic: ");
						distance = in.nextDouble();
						do {
							System.out.println("Enter 1: has mobility, or 0: no mobility: ");
							mobil = in.nextInt();
						} while (!(mobil==0 || mobil ==1));

						mobility = (mobil==1);

						clinic.addPatient(firstName, lastName, healthCardNumber, birthdate, distance, mobility);
						break;

					case 3: 

						clinic.addPatient(firstName, lastName, healthCardNumber, birthdate);
						break;
					}
				} else { 
					System.out.println("\nThis patient is already registered.");
				}
			}
		}
	}


	/* This method send arguments to Medical Clinic Class adding a new appointment */
	public void addAppointment() {

		Doctor doctor;
		Patient patient = null;
		OurDate aDate = null;
		Appointment app;
		HealthCardNumber healthCardNumber = null;
		String hcn;
		boolean isMatch = true;
		boolean isFound = false;
		boolean isSafe=true;
		boolean isValid=true;
		boolean isDateFormat;

		int choice = 0;
		int patientIndex;

		// variables to convert and split string date to integer numbers
		String date;
		int day = 0;
		int month = 0;
		int year = 0;

		if (clinic.getNumberAppointments() == MedicalClinic.getMAXAPPOINTMENTS())
		{

			System.out.println();
			System.out.println("You cannot add more appointments.");			

		} else { 

			try {
				System.out.print("Enter health card number: ");
				hcn = in.next();
				healthCardNumber = new HealthCardNumber (hcn);

			} catch (MedicalClinicException e5) {
				System.out.println(e5.getMessage());
				System.out.println();
				healthCardNumber = null;
				isSafe=false;
			}

			if (isSafe) {

				for (patientIndex = 0; patientIndex < clinic.getPatients().size(); patientIndex++) {

					if ((clinic.getPatients().get(patientIndex).getHealthCardNumber().equals(healthCardNumber)))

					{

						System.out.println(clinic.getPatients().get(patientIndex) + "\n");
						patient = clinic.getPatients().get(patientIndex);
						isMatch=true;
						patientIndex=clinic.getPatients().size();						

					} else {

						isMatch=false;	
					}
				}

				if (isMatch) {

					for (int i = 0; i < clinic.getDoctors().size(); i++) {
						System.out.println((i + 1) + ". " + clinic.getDoctors().get(i));
					}

					System.out.print("Enter number for doctor selection: ");
					choice = in.nextInt();
					doctor = clinic.getDoctors().get(choice-1);

					do {
						isValid=true;

						do {
							isValid=true;

							do { 
								System.out.print("Enter desired appointment date DDMMYYYY: ");
								date = in.next();
								isDateFormat=this.testDateFormat(date);

							} while (!isDateFormat);

							day = Integer.parseInt(date.substring(0,2));
							month = Integer.parseInt(date.substring(2,4));
							year = Integer.parseInt(date.substring(4,8));

							try {

								aDate = new OurDate(day, month, year);

							} catch (MedicalClinicException e5) {

								System.out.println(e5.getMessage());
								aDate = null;
								isValid=false;
							}

						} while(!isValid);

						try {

							app = new Appointment(doctor, patient, aDate);

						} catch (MedicalClinicException e6) {

							System.out.println(e6.getMessage());
							app = null;
							isValid=false;
						}

					} while (!isValid);

					for (int appIndex=0; appIndex < clinic.getNumberAppointments();appIndex++)
					{
						if (clinic.getAppointments().get(appIndex).getDoctor().equals(app.getDoctor())
								&& clinic.getAppointments().get(appIndex).getAppointmentDate().equals(app.getAppointmentDate()))

						{
							System.out.println("\nDoctor already has an appointment that day.");
							isFound = true;
							app = null;
							appIndex = clinic.getNumberAppointments();
						} 
					}

					if (!isFound) {
						clinic.addAppointment(doctor, patient, aDate);
					}

				} else {
					System.out.println("\nHealth Card Number is not registered.");
				}
			}
		}
	}



	/* This method send an index to Medical Clinic Class deleting its value */
	public void cancelAppointment() {

		Doctor doctor;
		Patient patient = null;
		OurDate aDate;
		Appointment app;
		HealthCardNumber healthCardNumber=null;
		String hcn;

		// variables to convert and split string date to integer numbers
		String date;
		int day = 0;
		int month = 0;
		int year = 0;

		boolean isSafe=true;		
		boolean isMatch = false;
		boolean isFound=false;		
		boolean isValid=true;
		boolean isDateFormat;

		int choice;
		int appIndex;


		try {

			System.out.print("\nEnter health card number: ");
			hcn = in.next();
			healthCardNumber = new HealthCardNumber(hcn);

		} catch (Exception e9) {
			System.out.println(e9.getMessage());
			hcn = null;
			isSafe=false;
		}

		if (isSafe) {

			for (int i = 0; i < clinic.getNumberAppointments(); i++) {

				if ((clinic.getAppointments().get(i).getPatient().getHealthCardNumber().equals(healthCardNumber))) {

					patient = clinic.getAppointments().get(i).getPatient();
					System.out.println(clinic.getAppointments().get(i).getPatient() + "\n");
					isMatch=true;
					i=clinic.getNumberAppointments();					
				} 
			}

			if (isMatch) {

				for (int i=0; i < clinic.getNumberDoctors(); i++) 

				{
					System.out.println((i+1) + ". "+ clinic.getDoctors().get(i));
				}

				System.out.print("Enter number for doctor selection: ");
				choice = in.nextInt();
				doctor = clinic.getDoctors().get(choice-1);

				do {

					isValid=true;

					do {

						isValid=true;

						do { 
							System.out.print("Enter appointment date DDMMYYYY: ");
							date = in.next();
							isDateFormat=this.testDateFormat(date);

						} while(!isDateFormat);

						day = Integer.parseInt(date.substring(0,2));
						month = Integer.parseInt(date.substring(2,4));
						year = Integer.parseInt(date.substring(4,8));

						try {
							aDate = new OurDate(day, month, year);

						} catch (MedicalClinicException e7) {
							System.out.println(e7.getMessage());
							aDate=null;
							isValid=false;
						}

					} while(!isValid);

					try {

						app = new Appointment (doctor, patient, aDate);

					} catch (MedicalClinicException e8) {
						System.out.println(e8.getMessage());
						app = null;
						isValid=false;
					}

				} while(!isValid);

				for (appIndex = 0; appIndex < clinic.getNumberAppointments(); appIndex++) {

					if (
							clinic.getAppointments().get(appIndex).getAppointmentDate().equals(app.getAppointmentDate()) &&
							clinic.getAppointments().get(appIndex).getDoctor().equals(app.getDoctor()) &&
							clinic.getAppointments().get(appIndex).getPatient().equals(app.getPatient()))
					{	
						clinic.cancelAppointment(appIndex);	
						System.out.println("\nAppointment Cancelled");
						isFound=true;
						appIndex=clinic.getNumberAppointments();
					}
				}	

				if (!isFound) {
					System.out.println("\nThis appointment does not exist");		
				}

			} else {
				System.out.println("This patient has not appointments");
			}
		}
	}

	/* This method lists all the appointments */
	public void listAppointments() { 

		for (int i = 0; i < clinic.getNumberAppointments(); i++) {
			System.out.println((i+1) + ". " + clinic.getAppointments().get(i));
		}
	}

	/* Optional method which displays the patient's list to the user */
	public void printPatients() { 

		for (int i=0; i < clinic.getPatients().size(); i++) 
		{
			System.out.println((i+1) + ". " + clinic.getPatients().get(i));
		}
	}

	/* Optional method which displays the doctor's list to the user */
	public void printDoctors() { 

		for (int i=0; i < clinic.getNumberDoctors(); i++) 
		{
			System.out.println((i+1) + ". "+ clinic.getDoctors().get(i));
		}
	}

	public boolean testDateFormat(String date) {

		if  (date.length()!=8) {

			System.out.println("Invalid date format!");
			System.out.println();
			return false;

		} else { 

			for (int i=0; i<date.length();i++) {
				if ( date.charAt(i) < 48 || date.charAt(i) > 57 ) 
				{ 
					System.out.println("Invalid date format!");
					System.out.println();
					return false;

				}
			}
		}

		return true;
	}

	public void writePatientsOut() throws IOException {

		clinic.filePatientsOut();

	}

	public void readPatientsIn() throws ClassNotFoundException {

		clinic.filePatientsIn();
		this.printPatients();
		
	}



} //End of class




