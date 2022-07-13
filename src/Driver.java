import dao.MedicalDatabase;
import entitites.Appointment;
import entitites.Doctor;
import service.MedicalService;
import service.MedicalServiceImpl;

import java.util.List;
import java.util.Scanner;


public class Driver {
    private static MedicalService app = new MedicalServiceImpl();

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        List<Doctor> availableDoctors;
        int userId, doctorId;

        registerUsers(sc);
        registerDoctors(sc);

        while (true){
            try {
                System.out.println("Please choose the operation you'd like to perform: " +
                        "\n1. Search doctor by city." +
                        "\n2. Search doctor by specialization." +
                        "\n3. Book an appointment.");
                System.out.print("Your choice : ");
                int operationChoice = sc.nextInt();

                switch(operationChoice){
                    case 1:
                        System.out.print("Enter city name: ");
                        String city = sc.next();
                        availableDoctors = app.getDoctorsByCity(city);
                        for (Doctor doc : availableDoctors){
                            System.out.println(doc);
                        }
                        break;

                    case 2:
                        System.out.println("Choose a specialization(Select the sequence number only) : ");
                        app.printSpecializations();
                        int specializationSequence = sc.nextInt();
                        availableDoctors = app.getDoctorsBySpecialization(specializationSequence);
                        for (Doctor doc : availableDoctors){
                            System.out.println(doc);
                        }
                        break;

                    case 3:
                        System.out.print("Patient User ID : ");
                        userId = sc.nextInt();
                        System.out.print("Doctor ID : ");
                        doctorId = sc.nextInt();
                        System.out.print("Please enter the date in \"dd/MM/yyyy\" format : ");
                        String date = sc.next();
                        System.out.print("Please enter booking hour : ");
                        int bookingHour = sc.nextInt();

                        Appointment appointment = app.bookAppointment(userId, doctorId, date, bookingHour);

                        System.out.println("Appointment confirmed. Please find the details below.");
                        System.out.print(appointment.toString());
                        break;

                    default:
                        System.out.println("Please enter a valid choice");
                }
            }
            catch (Exception ex){
                System.out.println(ex.getMessage());
            }
        }

    }

    public static void registerUsers(Scanner sc){
        System.out.println("----------- User registration -----------");
        String addAgain;
        do {
            System.out.println("Please enter user details to add a user.");
            try {
                System.out.print("First Name : ");
                String firstName = sc.next();
                System.out.print("Last Name : ");
                String lastName = sc.next();
                System.out.print("Email : ");
                String email = sc.next();
                System.out.print("Phone No. : ");
                String phone = sc.next();
                System.out.print("City : ");
                String city = sc.next();
                System.out.print("Locality : ");
                String locality = sc.next();
                System.out.print("State : ");
                String state = sc.next();
                System.out.print("Pin code : ");
                int pinCode = sc.nextInt();

                app.registerUser(firstName, lastName, email, phone, city, locality, state, pinCode);
            }
            catch (Exception ex){
                System.out.println(ex.getMessage());
            }
            System.out.println("Would you like to add another user?(Y/n)");
            addAgain = sc.next();
        }while(addAgain.equals("Y"));
    }

    public static void registerDoctors(Scanner sc){
        System.out.println("----------- Doctor registration -----------");
        String addAgain;
        do {
            System.out.println("Please enter user details to add a doctor.");
            try {
                System.out.print("First Name : ");
                String firstName = sc.next();
                System.out.print("Last Name : ");
                String lastName = sc.next();
                System.out.print("Registration Number : ");
                String registrationNumber = sc.next();
                System.out.print("Qualification : ");
                String qualification = sc.next();
                System.out.print("Name of the clinic : ");
                String clinicName = sc.next();
                System.out.print("City : ");
                String city = sc.next();
                System.out.print("Year of experience : ");
                int yearsOfExperience = sc.nextInt();
                System.out.println("Choose a specialization(Select the sequence number only) : ");
                app.printSpecializations();
                int specializationSequence = sc.nextInt();

                while (!app.isValidSpecialization(specializationSequence)) {
                    System.out.print("Please choose correct Specialization : ");
                    specializationSequence = sc.nextInt();
                }

                Doctor doctor = app.registerDoctor(firstName, lastName, registrationNumber, yearsOfExperience, qualification,
                        clinicName, city, specializationSequence);

                addBookingSlotsForDoctor(doctor, sc);
            }
            catch (Exception ex){
                System.out.println(ex.getMessage());
            }
            System.out.println("Would you like to add another doctor?(Y/n)");
            addAgain = sc.next();
        }while(addAgain.equals("Y"));
    }

    public static void addBookingSlotsForDoctor(Doctor doctor, Scanner sc){
        System.out.println("---- Adding Booking Slots. for doctor "+ doctor.getLastName()+" ----");
        String addAgain;
        do{
            try {
                System.out.println("Please enter start and the end hour in 24 hour format. \n " +
                        "For 1pm, it will be 13, for 5am, it should be 5.");
                System.out.print("Start Hour : ");
                int startHour = sc.nextInt();

                System.out.print("End Hour : ");
                int endHour = sc.nextInt();

                System.out.println("Please enter the day of week :"+
                        "\n1 for Sunday\n2 for Monday \n3 for Tuesday \n" +
                                "4 for Wednesday\n5 for Thursday\n6 for Friday\n7 for Saturday");
                System.out.print("Day of week : ");
                int dayOfWeek = sc.nextInt();

                doctor.addBookingSlot(startHour, endHour, dayOfWeek);
            }
            catch(Exception ex){
                System.out.println(ex.getMessage());
            }
            System.out.println("Would you like to add another booking slot?(Y/n)");
            addAgain = sc.next();
        }while(addAgain.equals("Y"));
    }
}
