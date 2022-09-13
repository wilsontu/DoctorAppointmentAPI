package model;

// a class that represents the data of doctors with their name and working time
public class Doctor {
    String name;
    String morning = "morning";
    String afternoon = "afternoon";
    double startTime;
    double endTime;
    int interval;

    /*
     * REQUIRES: time must be one of morning or afternoon in string
     * EFFECTS: doctor is set to a period of time on the schedule between startTime and endTime
     *          according to given time, the interval is set to 15 minutes
     */
    public Doctor(String name, String time) {
        this.name = name;
        if (time.equals(morning)) {
            startTime = 9.00;
            endTime = 12.00;
        } else {
            startTime = 15.00;
            endTime = 18.00;
        }
        this.interval = 15;
    }

    /*
     * EFFECTS: finds number of appointments available for doctor's time period
     */
    public int findAvailableNumAppointment() {
        return toMin(startTime, endTime) / interval;
    }

    /*
     * REQUIRES: end must be > than start
     * MODIFIES: this
     * EFFECTS: finds the number of minutes between given time interval in 24 hour format
     */
    public static int toMin(double start, double end) {
        int hours = (int) end - (int) start;
        int hourOfMin = (int) ((end - (int) end) * 100);
        return (hours * 60 + hourOfMin);
    }

    public double getDoctorStartTime() {
        return startTime;
    }

    public double getDoctorEndTime() {
        return endTime;
    }

    public String getDoctorName() {
        return name;
    }

    public int getInterval() {
        return interval;
    }
}
