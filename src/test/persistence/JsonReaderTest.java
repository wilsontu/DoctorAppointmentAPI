package persistence;

import model.PatientAppointments;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.*;

// tests referenced and modified from: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public class JsonReaderTest extends JsonTest{

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFIle.json");
        try {
            PatientAppointments pa = reader.read();
            fail("IOException expected.");
        } catch (IOException e) {
            // expected
        }
    }

    @Test
    void testReaderEmptyPA() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyPA.json");
        try {
            PatientAppointments pa = reader.read();
            assertEquals(0, pa.mergeAllPatientIntoSingleList().size());
        } catch (IOException e) {
            fail("Unexpected IOException thrown.");
        }
    }

    @Test
    void testReaderGeneralPA() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralPA.json");
        try {
            PatientAppointments pa = reader.read();
            assertEquals(6, pa.mergeAllPatientIntoSingleList().size());
            checkPatient("brad", "monday", "morning" ,1,
                    pa.mondayMorningPatient.get(0));
            checkPatient("will", "tuesday", "afternoon", 1,
                    pa.tuesdayAfternoonPatient.get(0));
            checkPatient("bob", "monday", "morning" ,2,
                    pa.mondayMorningPatient.get(1));
        } catch (IOException e) {
            fail("Unexpected IOException thrown.");
        }
    }
}
