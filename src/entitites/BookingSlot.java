package entitites;

public class BookingSlot {
    private int startHour;
    private int endHour;
    private int dayOfWeek;

    public BookingSlot(int startHour, int endHour, int dayOfWeek) {
        this.startHour = startHour;
        this.endHour = endHour;
        this.dayOfWeek = dayOfWeek;
    }

    public int getStartHour() {
        return startHour;
    }

    public int getEndHour() {
        return endHour;
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }
}
