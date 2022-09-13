package persistence;

import model.Patient;
import model.PatientAppointments;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

// tests referenced and modified from: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public class JsonWriterTest extends JsonTest {
    Patient brad = new Patient("brad");
    Patient bob = new Patient("bob");
    Patient joe1 = new Patient("joe");
    Patient joe2 = new Patient("joe");
    Patient will = new Patient("will");
    Patient jack = new Patient("jack");

    @Test
    void testWriterInvalidFile() {
        try {
            PatientAppointments pa = new PatientAppointments();
            JsonWriter writer = new JsonWriter("./data/doesnotexist/illegal:FileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // expected
        }
    }

    @Test
    void testWriterEmptyPA() {
        try {
            PatientAppointments pa = new PatientAppointments();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyPA.json");
            writer.open();
            writer.write(pa);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyPa.json");
            pa = reader.read();
            assertEquals(0, pa.mergeAllPatientIntoSingleList().size());
        } catch (IOException e) {
            fail("IOException not expected to be thrown.");
        }
    }

    @Test
    void testWriterGeneralPA() {
        try{
            PatientAppointments pa = new PatientAppointments();
            pa.addPatientToSchedule(brad, "monday", "morning");
            pa.addPatientToSchedule(bob, "monday", "morning");
            pa.addPatientToSchedule(joe1, "monday", "afternoon");
            pa.addPatientToSchedule(joe2, "friday", "morning");
            pa.addPatientToSchedule(will, "tuesday", "afternoon");
            pa.addPatientToSchedule(jack, "wednesday", "morning");
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralPA.json");
            writer.open();
            writer.write(pa);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralPA.json");
            pa = reader.read();
            assertEquals(6, pa.mergeAllPatientIntoSingleList().size());
            checkPatient("brad", "monday", "morning" ,1,
                    pa.mondayMorningPatient.get(0));
            checkPatient("will", "tuesday", "afternoon", 1,
                    pa.tuesdayAfternoonPatient.get(0));
            checkPatient("bob", "monday", "morning" ,2,
                    pa.mondayMorningPatient.get(1));
            checkPatient("joe", "monday", "afternoon" ,1,
                    pa.mondayAfternoonPatient.get(0));
            checkPatient("jack", "wednesday", "morning", 1,
                    pa.wednesdayMorningPatient.get(0));
            checkPatient("joe", "friday", "morning" ,1,
                    pa.fridayMorningPatient.get(0));
        } catch (IOException e) {
            fail("IOException not expected to be thrown.");
        }
    }

}
