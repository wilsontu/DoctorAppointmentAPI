package model;

import exceptions.NoDoctorAtTimeException;
import exceptions.NotADayException;

import java.util.ArrayList;

// Represents a weekdays schedule for doctors to add their available time in terms of days and time
public class DoctorSchedule {
    public static ArrayList<Doctor> monday;
    public static ArrayList<Doctor> tuesday;
    public static ArrayList<Doctor> wednesday;
    public static ArrayList<Doctor> thursday;
    public static ArrayList<Doctor> friday;

    /*
     * Initiates new schedule with empty lists of weekdays
     */
    public DoctorSchedule() {
        monday = new ArrayList<>();
        tuesday = new ArrayList<>();
        wednesday = new ArrayList<>();
        thursday = new ArrayList<>();
        friday = new ArrayList<>();
    }


    /*
     * REQUIRES: Arraylist of monday, tuesday, wednesday, thursday, friday must all be empty, doc1
     *           must not be the same as doc2, day must be one of: monday, tuesday, wednesday
     *           thursday, or friday
     * MODIFIES: this
     * EFFECTS: adds doctors to schedule, assigns doctors into order in terms of start time, early at front
     *          of arraylist, assigns doctor to one of weekday arraylists according to day
     */
    public static void addDoctor(Doctor doc1, Doctor doc2, String day) throws NotADayException {
        if (day.equals("monday")) {
            addDoctorToMonday(doc1, doc2);
        } else if (day.equals("tuesday")) {
            addDoctorToTuesday(doc1, doc2);
        } else if (day.equals("wednesday")) {
            addDoctorToWednesday(doc1, doc2);
        } else if (day.equals("thursday")) {
            addDoctorToThursday(doc1, doc2);
        } else if (day.equals("friday")) {
            addDoctorToFriday(doc1, doc2);
        } else {
            throw new NotADayException("String entered is not a weekday!");
        }
    }

    /*
     * REQUIRES: day has to be one of monday, tuesday, wednesday, thursday, friday
     * EFFECTS: returns the name of doctor on schedule at given time and day, otherwise returns No Doctors can be
     *          found at given time
     */
    public static String findDoctorAtTime(String day, String time) throws NoDoctorAtTimeException {
        if (time.equals("morning")) {
            return ((findDoctorAtDay(day)).get(0)).getDoctorName();
        } else if (time.equals("afternoon")) {
            return ((findDoctorAtDay(day)).get(1)).getDoctorName();
        } else {
            throw new NoDoctorAtTimeException("No doctor can be found at this time!");
        }
    }

    /*
     * REQUIRES: day must be one of week days
     * EFFECTS: returns the list of doctors working on given day of the week
     */
    private static ArrayList<Doctor> findDoctorAtDay(String day) {
        day.toLowerCase();
        switch (day) {
            case "monday":
                return monday;
            case "tuesday":
                return tuesday;
            case "wednesday":
                return wednesday;
            case "thursday":
                return thursday;
            default:
                return friday;
        }
    }

    /*
     * REQUIRES: doc1 and doc2 must be doctors
     * MODIFIES: this
     * EFFECTS: adds doc1 and doc2 to monday arraylist and add them in sequence according to their start time
     *          early at front and late at rear of the arraylist
     */
    private static void addDoctorToMonday(Doctor doc1, Doctor doc2) {
        if (doc1.startTime < doc2.startTime) {
            monday.add(doc1);
            monday.add(doc2);
        } else {
            monday.add(doc2);
            monday.add(doc1);
        }
    }

    /*
     * REQUIRES: doc1 and doc2 must be doctors
     * MODIFIES: this
     * EFFECTS: adds doc1 and doc2 to tuesday arraylist and add them in sequence according to their start time
     *          early at front and late at rear of the arraylist
     */
    private static void addDoctorToTuesday(Doctor doc1, Doctor doc2) {
        if (doc1.startTime < doc2.startTime) {
            tuesday.add(doc1);
            tuesday.add(doc2);
        } else {
            tuesday.add(doc2);
            tuesday.add(doc1);
        }
    }

    /*
     * REQUIRES: doc1 and doc2 must be doctors
     * MODIFIES: this
     * EFFECTS: adds doc1 and doc2 to wednesday arraylist and add them in sequence according to their start time
     *          early at front and late at rear of the arraylist
     */
    private static void addDoctorToWednesday(Doctor doc1, Doctor doc2) {
        if (doc1.startTime < doc2.startTime) {
            wednesday.add(doc1);
            wednesday.add(doc2);
        } else {
            wednesday.add(doc2);
            wednesday.add(doc1);
        }
    }

    /*
     * REQUIRES: doc1 and doc2 must be doctors
     * MODIFIES: this
     * EFFECTS: adds doc1 and doc2 to thursday arraylist and add them in sequence according to their start time
     *          early at front and late at rear of the arraylist
     */
    private static void addDoctorToThursday(Doctor doc1, Doctor doc2) {
        if (doc1.startTime < doc2.startTime) {
            thursday.add(doc1);
            thursday.add(doc2);
        } else {
            thursday.add(doc2);
            thursday.add(doc1);
        }
    }

    /*
     * REQUIRES: doc1 and doc2 must be doctors
     * MODIFIES: this
     * EFFECTS: adds doc1 and doc2 to friday arraylist and add them in sequence according to their start time
     *          early at front and late at rear of the arraylist
     */
    private static void addDoctorToFriday(Doctor doc1, Doctor doc2) {
        if (doc1.startTime < doc2.startTime) {
            friday.add(doc1);
            friday.add(doc2);
        } else {
            friday.add(doc2);
            friday.add(doc1);
        }
    }

    public static ArrayList<Doctor> getMonday() {
        return monday;
    }

    public static ArrayList<Doctor> getTuesday() {
        return tuesday;
    }

    public static ArrayList<Doctor> getWednesday() {
        return wednesday;
    }

    public static ArrayList<Doctor> getThursday() {
        return thursday;
    }

    public static ArrayList<Doctor> getFriday() {
        return friday;
    }
}