package persistence;

import model.Patient;

import static org.junit.jupiter.api.Assertions.assertEquals;

// methods referenced and modified from: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public class JsonTest {
    protected void checkPatient(String name, String appointDay, String appointTime,
                                int appointNum, Patient p) {
        assertEquals(name, p.getName());
        assertEquals(appointDay, p.getAppointDay());
        assertEquals(appointTime, p.getAppointTime());
        assertEquals(appointNum, p.getAppointNum());
    }
}
