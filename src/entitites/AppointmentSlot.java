package entitites;

import java.util.Objects;

public class AppointmentSlot {
    private String date;
    private int hour;

    public AppointmentSlot(String date, int hour) {
        this.date = date;
        this.hour = hour;
    }

    public String getDate() {
        return date;
    }

    public int getHour() {
        return hour;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AppointmentSlot)) return false;
        AppointmentSlot that = (AppointmentSlot) o;
        return hour == that.hour && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, hour);
    }
}
