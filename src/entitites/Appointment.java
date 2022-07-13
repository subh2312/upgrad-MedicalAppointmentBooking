package entitites;

public class Appointment {
    public static int counter = 0;
    private int appointmentId;

    private String userName;
    private String doctorName;
    private String city;
    private String clinicName;
    private String appointmentDate;
    private int appointmentHour;

    public Appointment(String userName, String doctorName, String city, String clinicName, String appointmentDate, int appointmentHour) {
        this.userName = userName;
        this.doctorName = doctorName;
        this.city = city;
        this.clinicName = clinicName;
        this.appointmentDate = appointmentDate;
        this.appointmentHour = appointmentHour;
        this.appointmentId = counter;
        counter++;
    }

    public String getUserName() {
        return userName;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public String getCity() {
        return city;
    }

    public String getClinicName() {
        return clinicName;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public int getAppointmentHour() {
        return appointmentHour;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "userName='" + userName + '\'' +
                ", doctorName='" + doctorName + '\'' +
                ", city='" + city + '\'' +
                ", clinicName='" + clinicName + '\'' +
                ", appointmentDate='" + appointmentDate + '\'' +
                ", appointmentHour='" + appointmentHour + '\'' +
                '}';
    }
}
