package model;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class PatientAppointmentsTest {
    PatientAppointments pa;
    Patient bob;
    Patient joe;
    Patient jack;
    JsonWriter writer;
    JsonReader reader;

    @BeforeEach
    void runBefore() {
        pa = new PatientAppointments();
        bob = new Patient("Bob");
        joe = new Patient("Joe");
        jack = new Patient("Jack");
        writer = new JsonWriter("./data/PatientAppointmentTest.json");
        reader = new JsonReader("./data/PatientAppointmentTest.json");
    }

    @Test
    void testToJson() {
        pa.addPatientToSchedule(bob, "monday", "morning");
        pa.addPatientToSchedule(joe, "tuesday", "afternoon");
        pa.addPatientToSchedule(jack, "wednesday", "morning");
        ArrayList<Patient> allPatient = pa.mergeAllPatientIntoSingleList();
        JSONObject testObject = pa.toJson();
        String objectString = testObject.toString();
        JSONObject object = new JSONObject();
        object.put("PatientSchedule", pa.weekDayListToJson(allPatient));
        assertEquals(objectString, object.toString());
    }

    @Test
    void testWeekDayListToJson() {
        pa.addPatientToSchedule(bob, "monday", "morning");
        pa.addPatientToSchedule(joe, "tuesday", "afternoon");
        pa.addPatientToSchedule(jack, "wednesday", "morning");
        ArrayList<Patient> allPatient = pa.mergeAllPatientIntoSingleList();
        JSONArray paJson = pa.weekDayListToJson(allPatient);
        int count = 0;
        for (Patient p : allPatient) {
            JSONObject object = paJson.getJSONObject(count);
            assertEquals(p.toJson().get("name"), object.getString("name"));
            assertEquals(p.toJson().get("appointDay"), object.getString("appointDay"));
            assertEquals(p.toJson().get("appointTime"), object.getString("appointTime"));
            assertEquals(p.toJson().get("appointNum"), object.getInt("appointNum"));
            count++;
        }
    }

    @Test
    void testMergeAllPatientIntoSingleList() {
        Patient bob1 = new Patient("Bob");
        pa.addPatientToSchedule(bob, "monday", "morning");
        pa.addPatientToSchedule(bob1, "monday", "afternoon");
        pa.addPatientToSchedule(jack, "friday", "morning");
        pa.addPatientToSchedule(joe, "tuesday", "afternoon");
        ArrayList<Patient> paTest = pa.mergeAllPatientIntoSingleList();

        assertEquals(bob, paTest.get(0));
        assertEquals(bob1, paTest.get(1));
        assertEquals(jack, paTest.get(3));
        assertEquals(joe, paTest.get(2));
    }

    @Test
    void testAddPatientToScheduleMonday() {
        pa.addPatientToSchedule(bob, "monday", "morning");
        assertEquals("Bob", (pa.mondayMorningPatient.get(0)).getName());
        assertEquals("monday", (pa.mondayMorningPatient.get(0)).getAppointDay());
        assertEquals("morning", (pa.mondayMorningPatient.get(0)).getAppointTime());

        pa.addPatientToSchedule(joe, "monday", "afternoon");
        assertEquals("Joe", (pa.mondayAfternoonPatient.get(0)).getName());
        assertEquals("monday", (pa.mondayAfternoonPatient.get(0)).getAppointDay());
        assertEquals("afternoon", (pa.mondayAfternoonPatient.get(0)).getAppointTime());
    }

    @Test
    void testAddPatientToScheduleTuesday() {
        pa.addPatientToSchedule(bob, "tuesday", "morning");
        assertEquals("Bob", (pa.tuesdayMorningPatient.get(0)).getName());
        assertEquals("tuesday", (pa.tuesdayMorningPatient.get(0)).getAppointDay());
        assertEquals("morning", (pa.tuesdayMorningPatient.get(0)).getAppointTime());

        pa.addPatientToSchedule(joe, "tuesday", "afternoon");
        assertEquals("Joe", (pa.tuesdayAfternoonPatient.get(0)).getName());
        assertEquals("tuesday", (pa.tuesdayAfternoonPatient.get(0)).getAppointDay());
        assertEquals("afternoon", (pa.tuesdayAfternoonPatient.get(0)).getAppointTime());
    }

    @Test
    void testAddPatientToScheduleWednesday() {
        pa.addPatientToSchedule(bob, "wednesday", "morning");
        assertEquals("Bob", (pa.wednesdayMorningPatient.get(0)).getName());
        assertEquals("wednesday", (pa.wednesdayMorningPatient.get(0)).getAppointDay());
        assertEquals("morning", (pa.wednesdayMorningPatient.get(0)).getAppointTime());

        pa.addPatientToSchedule(joe, "wednesday", "afternoon");
        assertEquals("Joe", (pa.wednesdayAfternoonPatient.get(0)).getName());
        assertEquals("wednesday", (pa.wednesdayAfternoonPatient.get(0)).getAppointDay());
        assertEquals("afternoon", (pa.wednesdayAfternoonPatient.get(0)).getAppointTime());
    }

    @Test
    void testAddPatientToScheduleThursday() {
        pa.addPatientToSchedule(bob, "thursday", "morning");
        assertEquals("Bob", (pa.thursdayMorningPatient.get(0)).getName());
        assertEquals("thursday", (pa.thursdayMorningPatient.get(0)).getAppointDay());
        assertEquals("morning", (pa.thursdayMorningPatient.get(0)).getAppointTime());

        pa.addPatientToSchedule(joe, "thursday", "afternoon");
        assertEquals("Joe", (pa.thursdayAfternoonPatient.get(0)).getName());
        assertEquals("thursday", (pa.thursdayAfternoonPatient.get(0)).getAppointDay());
        assertEquals("afternoon", (pa.thursdayAfternoonPatient.get(0)).getAppointTime());
    }

    @Test
    void testAddPatientToScheduleFriday() {
        pa.addPatientToSchedule(bob, "friday", "morning");
        assertEquals("Bob", (pa.fridayMorningPatient.get(0)).getName());
        assertEquals("friday", (pa.fridayMorningPatient.get(0)).getAppointDay());
        assertEquals("morning", (pa.fridayMorningPatient.get(0)).getAppointTime());

        pa.addPatientToSchedule(joe, "friday", "afternoon");
        assertEquals("Joe", (pa.fridayAfternoonPatient.get(0)).getName());
        assertEquals("friday", (pa.fridayAfternoonPatient.get(0)).getAppointDay());
        assertEquals("afternoon", (pa.fridayAfternoonPatient.get(0)).getAppointTime());
    }
}
