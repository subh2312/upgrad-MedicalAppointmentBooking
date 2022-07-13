package service;

import entitites.Appointment;
import entitites.Doctor;

import java.util.List;

public interface MedicalService {
    List<Doctor> getDoctorsByCity(String city);

    void printSpecializations();

    List<Doctor> getDoctorsBySpecialization(int specializationSequence);

    Appointment bookAppointment(int userId, int doctorId, String date, int bookingHour) throws Exception;

    void registerUser(String firstName, String lastName, String email, String phone, String city, String locality, String state, int pinCode);

    boolean isValidSpecialization(int specializationSequence);

    Doctor registerDoctor(String firstName, String lastName, String registrationNumber, int yearsOfExperience, String qualification, String clinicName, String city, int specializationSequence);
}
