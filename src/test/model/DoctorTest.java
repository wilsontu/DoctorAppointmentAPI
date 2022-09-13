package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DoctorTest {
    private Doctor georgeMorning;
    private Doctor georgeAfternoon;

    @BeforeEach
    void runBefore() {
        georgeMorning = new Doctor("George", "morning");
        georgeAfternoon = new Doctor("George", "afternoon");
    }

    @Test
    void testConstructor() {
        assertEquals("George", georgeMorning.getDoctorName());
        assertEquals(9.00, georgeMorning.getDoctorStartTime());
        assertEquals(12.00, georgeMorning.getDoctorEndTime());
        assertEquals(15, georgeMorning.getInterval());

        assertEquals("George", georgeAfternoon.getDoctorName());
        assertEquals(15.00, georgeAfternoon.getDoctorStartTime());
        assertEquals(18.00, georgeAfternoon.getDoctorEndTime());
        assertEquals(15, georgeAfternoon.getInterval());
    }

    @Test
    void testToMin() {
        assertEquals(180, georgeMorning.toMin(georgeMorning.startTime, georgeMorning.endTime));
        assertEquals(180, georgeAfternoon.toMin(georgeAfternoon.startTime, georgeAfternoon.endTime));
    }

    @Test
    void testFindAvailableNumAppointment() {
        assertEquals(12, georgeMorning.findAvailableNumAppointment());
        assertEquals(12, georgeAfternoon.findAvailableNumAppointment());
    }

}