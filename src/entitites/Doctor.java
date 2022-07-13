package entitites;

import java.text.ParseException;
import java.util.*;
import java.text.SimpleDateFormat;

public class Doctor {
    public static int counter = 0;
    private int doctorId;

    private String firstName;
    private String lastName;
    private String registrationNumber;
    private int yearsOfExperience;
    private String qualification;
    private String clinicName;
    private String city;
    private int specialization;

    List<BookingSlot> bookingSlots;
    Set<AppointmentSlot> bookedSlots;

    public Doctor(String firstName, String lastName, String registrationNumber, int yearsOfExperience, String qualification, String clinicName, String city, int specialization) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.registrationNumber = registrationNumber;
        this.yearsOfExperience = yearsOfExperience;
        this.qualification = qualification;
        this.clinicName = clinicName;
        this.city = city;
        this.specialization = specialization;

        this.doctorId = counter;
        this.bookingSlots = new ArrayList<>();
        this.bookedSlots = new HashSet<>();
        counter++;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    public String getQualification() {
        return qualification;
    }

    public String getClinicName() {
        return clinicName;
    }

    public String getCity() {
        return city;
    }

    public int getSpecialization() {
        return specialization;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "doctorId=" + doctorId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", registrationNumber='" + registrationNumber + '\'' +
                ", yearsOfExperience=" + yearsOfExperience +
                ", qualification='" + qualification + '\'' +
                ", clinicName='" + clinicName + '\'' +
                ", city='" + city + '\'' +
                ", specialization=" + specialization +
                '}';
    }

    public void addBookingSlot(int startHour, int endHour, int dayOfWeek) throws Exception {
        BookingSlot newSlot = new BookingSlot(startHour, endHour, dayOfWeek);
        if(isValidBookingSlot(newSlot)){
            bookingSlots.add(newSlot);
        }
        else {
            throw new Exception("There's already another overlapping Booking slot added!");
        }
    }

    public void bookAppointment(String date, int hour) throws Exception {
        AppointmentSlot appointmentSlot = new AppointmentSlot(date, hour);
        if(isValidAppointmentSlot(date, hour) &&
                !bookedSlots.contains(appointmentSlot)){
            bookedSlots.add(appointmentSlot);
        }
        else{
            throw new Exception("Slot is not available.");
        }
    }

    // provided
    private boolean isValidBookingSlot(BookingSlot newSlot){
        boolean isValidBookingSlot = true;
        for(BookingSlot slot: bookingSlots){
            if(isOverLappingSlot(slot, newSlot)){
                isValidBookingSlot = false;
                break;
            }
        }

        return isValidBookingSlot;
    }

    // provided
    private boolean isOverLappingSlot(BookingSlot slot1, BookingSlot slot2){
        boolean isOverlapping = false;
        if(slot1.getDayOfWeek() == slot2.getDayOfWeek()) {
            if((slot1.getStartHour() <= slot2.getStartHour() &&
                    slot1.getEndHour() >= slot2.getStartHour()) ||
                (slot1.getStartHour() >= slot2.getStartHour() &&
                    slot1.getStartHour() <= slot2.getEndHour())){
                isOverlapping = true;
            }
        }
        return isOverlapping;
    }

    // Provided
    private boolean isValidAppointmentSlot(String date, int hour) {
        boolean isValidAppointmentSlot = false;
        try {
            int dayOfWeek = getDayOfWeek(date);
            if(dayOfWeek >= 1 && dayOfWeek <= 7){
                for (BookingSlot slot: bookingSlots){
                    if(dayOfWeek == slot.getDayOfWeek()){
                        if(hour >= slot.getStartHour() && hour <= slot.getEndHour()){
                            isValidAppointmentSlot = true;
                            break;
                        }
                    }
                }
            }
        }
        catch(ParseException ex){ }

        return isValidAppointmentSlot;
    }

    // Provided
    private int getDayOfWeek(String date) throws ParseException {
        Date parsedDate = new SimpleDateFormat("dd/MM/yyyy").parse(date);
        Calendar c = Calendar.getInstance();
        c.setTime(parsedDate);
        return c.get(Calendar.DAY_OF_WEEK);
    }
}