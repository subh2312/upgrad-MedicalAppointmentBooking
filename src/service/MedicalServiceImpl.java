package service;

import dao.MedicalDatabase;
import entitites.Appointment;
import entitites.Doctor;

import java.util.List;

public class MedicalServiceImpl implements MedicalService{
    private MedicalDatabase database;

    public MedicalServiceImpl() {
        this.database = new MedicalDatabase();
    }


    @Override
    public List<Doctor> getDoctorsByCity(String city) {
       return database.getDoctorsByCity(city);
    }

    @Override
    public void printSpecializations() {
     database.printSpecializations();
    }

    @Override
    public List<Doctor> getDoctorsBySpecialization(int specializationSequence) {
        return database.getDoctorsBySpecialization(specializationSequence);
    }

    @Override
    public Appointment bookAppointment(int userId, int doctorId, String date, int bookingHour) throws Exception {
        return database.bookAppointment(userId, doctorId, date, bookingHour);
    }

    @Override
    public void registerUser(String firstName, String lastName, String email, String phone, String city, String locality, String state, int pinCode) {
        database.registerUser(firstName, lastName, email, phone, city, locality, state, pinCode);
    }

    @Override
    public boolean isValidSpecialization(int specializationSequence) {
        return database.isValidSpecialization(specializationSequence);
    }

    @Override
    public Doctor registerDoctor(String firstName, String lastName, String registrationNumber, int yearsOfExperience, String qualification, String clinicName, String city, int specializationSequence) {
        return database.registerDoctor(firstName, lastName, registrationNumber, yearsOfExperience, qualification, clinicName, city, specializationSequence);
    }
}
