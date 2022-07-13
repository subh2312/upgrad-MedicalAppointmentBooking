package dao;

import entitites.*;

import java.util.*;

public class MedicalDatabase {
    private static Map<Integer, String> specializations = new HashMap<>();
    static {
        specializations.put(1, "Ayurveda");
        specializations.put(2, "Dentist");
        specializations.put(3, "Dermatologist");
        specializations.put(4, "ENT");
        specializations.put(5, "General");
        specializations.put(6, "Gynecologist");
        specializations.put(7, "Homeopath");
    }

    private Map<String, List<Doctor>> cityWiseDoctors = new HashMap<>();
    private Map<Integer, List<Doctor>> specializationWiseDoctors = new HashMap<>();
    private Map<Integer, User> users = new HashMap<>();
    private Map<Integer, Doctor> doctors = new HashMap<>();
    private List<Appointment> appointments = new ArrayList<>();

    public User registerUser(String firstName, String lastName, String email, String phone,
                             String city, String locality,String state, int pinCode){
        User newUser = new User(firstName, lastName,
                new User.Address(city, locality, state, pinCode),
                new User.Contact(email, phone));
        users.put(newUser.getUserId(), newUser);
        return newUser;
    }

    public Doctor registerDoctor(String firstName, String lastName, String registrationNumber, int yearsOfExperience, String qualification, String clinicName, String city, int specialization){
        Doctor newDoctor = new Doctor(firstName, lastName, registrationNumber, yearsOfExperience, qualification, clinicName, city, specialization);
        doctors.put(newDoctor.getDoctorId(), newDoctor);

        // Update city wise doctor list
        List<Doctor> currentCityDoctors = new ArrayList<>();
        if(cityWiseDoctors.containsKey(city)){
            currentCityDoctors = cityWiseDoctors.get(city);
        }
        currentCityDoctors.add(newDoctor);
        cityWiseDoctors.put(city, currentCityDoctors);

        // Update specialization wise doctor list
        List<Doctor> currentSpecializedDoctors = new ArrayList<>();
        if(specializationWiseDoctors.containsKey(specialization)){
            currentSpecializedDoctors = specializationWiseDoctors.get(specialization);
        }
        currentSpecializedDoctors.add(newDoctor);
        specializationWiseDoctors.put(specialization, currentSpecializedDoctors);

        return newDoctor;
    }

    public List<Doctor> getDoctorsBySpecialization(int specialization){
        List<Doctor> doctors = new ArrayList<Doctor>();
        if(specializationWiseDoctors.containsKey(specialization)){
            doctors = specializationWiseDoctors.get(specialization);
        }
        return doctors;
    }

    public List<Doctor> getDoctorsByCity(String city){
        List<Doctor> doctors = new ArrayList<Doctor>();
        if(cityWiseDoctors.containsKey(city)){
            doctors = cityWiseDoctors.get(city);
        }
        return doctors;
    }

    public Appointment bookAppointment(int userId, int doctorId, String date, int hour) throws Exception {
        if(!isValidUserId(userId)){
            throw new Exception("Patient User Id is invalid!");
        }
        if(!isValidDoctorId(doctorId)){
            throw new Exception("Doctor Id is invalid!");
        }
        User user = users.get(userId);
        Doctor doctor = doctors.get(doctorId);
        String userName = user.getFirstName() + " " + user.getLastName();
        String doctorName = doctor.getFirstName() + " " + doctor.getLastName();

        doctor.bookAppointment(date, hour);
        Appointment appointment = new Appointment(userName, doctorName, doctor.getCity(), doctor.getClinicName(), date, hour);
        appointments.add(appointment);

        return  appointment;
    }

    public void printSpecializations(){
        for (Map.Entry<Integer, String> entry : specializations.entrySet()) {
            System.out.println(entry.getKey() + ". " + entry.getValue());
        }
    }

    public boolean isValidSpecialization(int sequence){
        return specializations.containsKey(sequence);
    }

    private boolean isValidUserId(int userId){
        return users.containsKey(userId);
    }

    private boolean isValidDoctorId(int doctorId){
        return doctors.containsKey(doctorId);
    }
}
