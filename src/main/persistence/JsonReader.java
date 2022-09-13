package persistence;

import model.Patient;
import model.PatientAppointments;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads patient appointments from json data in file
// methods referenced and modified from: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads patient appointments from file and returns it;
    // throws IOException if an error occurs reading data from file
    public PatientAppointments read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parsePatientSchedule(jsonObject);
    }

    // EFFECTS: reads file as string then returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    /*
     * EFFECTS: parses patient schedule from json object and returns it
     */
    private PatientAppointments parsePatientSchedule(JSONObject object) {
        PatientAppointments pa = new PatientAppointments();
        addPatients(pa, object);
        return pa;
    }

    /*
     * MODIFIES: pa
     * EFFECTS: parses patients from json object and adds then to pa
     */
    public void addPatients(PatientAppointments pa, JSONObject object) {
        JSONArray jsonArray = object.getJSONArray("PatientSchedule");
        for (Object json : jsonArray) {
            JSONObject nextPatient = (JSONObject) json;
            addPatient(pa, nextPatient);
        }
    }

    /*
     * MODIFIES: pa
     * EFFECTS: parses information held in json object to Patients and adds patient to pa according to parsed
     *          data
     */
    public void addPatient(PatientAppointments pa, JSONObject object) {
        String name = object.getString("name");
        String appointDay = object.getString("appointDay");
        String appointTime = object.getString("appointTime");
        int appointNum = object.getInt("appointNum");
        Patient p = new Patient(name);
        p.setAppointDay(appointDay);
        p.setAppointTime(appointTime);
        p.setAppointNum(appointNum);
        pa.addPatientToSchedule(p, appointDay, appointTime);
    }

}
