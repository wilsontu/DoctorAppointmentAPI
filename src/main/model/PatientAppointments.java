package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

// Represents all the patients having made appointments according to weekday and time
public class PatientAppointments implements Writable {
    public ArrayList<Patient> mondayMorningPatient;
    public ArrayList<Patient> tuesdayMorningPatient;
    public ArrayList<Patient> wednesdayMorningPatient;
    public ArrayList<Patient> thursdayMorningPatient;
    public ArrayList<Patient> fridayMorningPatient;
    public ArrayList<Patient> mondayAfternoonPatient;
    public ArrayList<Patient> tuesdayAfternoonPatient;
    public ArrayList<Patient> wednesdayAfternoonPatient;
    public ArrayList<Patient> thursdayAfternoonPatient;
    public ArrayList<Patient> fridayAfternoonPatient;


    /*
     * EFFECTS: Initiates new empty lists of appointment lists for patients according to weekday and time
     */
    public PatientAppointments() {
        mondayMorningPatient = new ArrayList<>();
        tuesdayMorningPatient = new ArrayList<>();
        wednesdayMorningPatient = new ArrayList<>();
        thursdayMorningPatient = new ArrayList<>();
        fridayMorningPatient = new ArrayList<>();
        mondayAfternoonPatient = new ArrayList<>();
        tuesdayAfternoonPatient = new ArrayList<>();
        wednesdayAfternoonPatient = new ArrayList<>();
        thursdayAfternoonPatient = new ArrayList<>();
        fridayAfternoonPatient = new ArrayList<>();
    }

    /*
     * REQUIRES: day and time must be one of weekdays and one of morning or afternoon all in lowercase
     * MODIFIES: this
     * EFFECTS: adds patients to their appointed list in terms of given day and time and updates the patient's
     *          appointment number according to their position in arraylist
     */
    public void addPatientToSchedule(Patient p, String day, String time) {
        if (day.equals("monday")) {
            addToMonday(p, time);
        } else if (day.equals("tuesday")) {
            addToTuesday(p, time);
        } else if (day.equals("wednesday")) {
            addToWednesday(p, time);
        } else if (day.equals("thursday")) {
            addToThursday(p, time);
        } else {
            addToFriday(p, time);
        }
    }

    /*
     * REQUIRES: time must be one of morning or afternoon
     * MODIFIES: this
     * EFFECTS: adds patient to list according to time, and given choices for morning and afternoon patient
     *          list
     */
    private void addToDayList(Patient p, String time, ArrayList<Patient> morning,
                              ArrayList<Patient> afternoon) {
        if (time.equals("morning")) {
            morning.add(p);
            p.setAppointTime("morning");
            p.setAppointNum(morning.size());
        } else {
            afternoon.add(p);
            p.setAppointTime("afternoon");
            p.setAppointNum(afternoon.size());
        }
    }

    /*
     * REQUIRES: p must be a patient and time must be one of morning or afternoon
     * MODIFIES: this
     * EFFECTS: adds patient p with given time to either Friday morning or afternoon arraylist according
     *          to given time
     */
    private void addToFriday(Patient p, String time) {
        p.setAppointDay("friday");
        addToDayList(p, time, fridayMorningPatient, fridayAfternoonPatient);
    }

    /*
     * REQUIRES: p must be a patient and time must be one of morning or afternoon
     * MODIFIES: this
     * EFFECTS: adds patient p with given time to thursday arraylist
     */
    private void addToThursday(Patient p, String time) {
        p.setAppointDay("thursday");
        addToDayList(p, time, thursdayMorningPatient, thursdayAfternoonPatient);
    }

    /*
     * REQUIRES: p must be a patient and time must be one of morning or afternoon
     * MODIFIES: this
     * EFFECTS: adds patient p with given time to wednesday arraylist
     */
    private void addToWednesday(Patient p, String time) {
        p.setAppointDay("wednesday");
        addToDayList(p, time, wednesdayMorningPatient, wednesdayAfternoonPatient);
    }

    /*
     * REQUIRES: p must be a patient and time must be one of morning or afternoon
     * MODIFIES: this
     * EFFECTS: adds patient p with given time to either Tuesday morning or afternoon arraylist according
     *          to given time
     */
    private void addToTuesday(Patient p, String time) {
        p.setAppointDay("tuesday");
        addToDayList(p, time, tuesdayMorningPatient, tuesdayAfternoonPatient);
    }

    /*
     * REQUIRES: p must be a patient and time must be one of morning or afternoon
     * MODIFIES: this
     * EFFECTS: adds patient p with given time to either monday morning or afternoon arraylist according
     *          to given time
     */
    private void addToMonday(Patient p, String time) {
        p.setAppointDay("monday");
        addToDayList(p, time, mondayMorningPatient, mondayAfternoonPatient);
    }

    /*
     * MODIFIES: this
     * EFFECTS: adds all the patients in PatientAppointments into a single array list
     */
    public ArrayList<Patient> mergeAllPatientIntoSingleList() {
        ArrayList<Patient> mergedList = new ArrayList<>();
        mergedList.addAll(mondayMorningPatient);
        mergedList.addAll(mondayAfternoonPatient);
        mergedList.addAll(tuesdayMorningPatient);
        mergedList.addAll(tuesdayAfternoonPatient);
        mergedList.addAll(wednesdayMorningPatient);
        mergedList.addAll(wednesdayAfternoonPatient);
        mergedList.addAll(thursdayMorningPatient);
        mergedList.addAll(thursdayAfternoonPatient);
        mergedList.addAll(fridayMorningPatient);
        mergedList.addAll(fridayAfternoonPatient);
        return mergedList;
    }

    /*
     * EFFECTS: overrides toJson method in writable, initializes a new json object and saves
     *          the entire list of patients from patient appointment as a json object
     * method referenced and modified from: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
     */
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("PatientSchedule", weekDayListToJson(mergeAllPatientIntoSingleList()));
        return json;
    }

    /*
     * EFFECTS: adds each Patient in list as a json object into a jsonArray
     * method referenced and modified from: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
     */
    protected JSONArray weekDayListToJson(ArrayList<Patient> list) {
        JSONArray jsonArray = new JSONArray();
        for (Patient p : list) {
            jsonArray.put(p.toJson());
        }
        return jsonArray;
    }
}
