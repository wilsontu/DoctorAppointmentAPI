package model;

import org.json.JSONObject;
import persistence.Writable;

// class that represents data of patients with their name and appointment day, time and number
public class Patient implements Writable {
    public String name;
    public String appointDay;
    public String appointTime;
    public int appointNum;

    /*
     * Initiates a new patient with name and no time and day appointed with appointNum of 0
     */
    public Patient(String name) {
        this.name = name;
        appointDay = null;
        appointTime = null;
        appointNum = 0;
    }

    /*
     * EFFECTS: saves objects inside a Patient as a json object appointed to string
     * method referenced and modified from: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
     */
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("appointDay", appointDay);
        json.put("appointTime", appointTime);
        json.put("appointNum", appointNum);
        return json;
    }

    /*
     * MODIFIES: this
     * EFFECTS: changes patient's appointment number to num
     */
    public void setAppointNum(int num) {
        appointNum = num;
    }

    /*
     * REQUIRES: day must be one of weekday
     * MODIFIES: this
     * EFFECTS: changes appointment day to day
     */
    public void setAppointDay(String day) {
        appointDay = day;
    }

    /*
     * REQUIRES: time must be one of morning or afternoon
     * MODIFIES: this
     * EFFECTS: changes appointment time to time
     */
    public void setAppointTime(String time) {
        appointTime = time;
    }

    public  String getName() {
        return name;
    }

    public String getAppointDay() {
        return appointDay;
    }

    public String getAppointTime() {
        return appointTime;
    }

    public int getAppointNum() {
        return appointNum;
    }
}
