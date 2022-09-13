package model;

import exceptions.NoDoctorAtTimeException;
import exceptions.NotADayException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DoctorScheduleTest {
    DoctorSchedule schedule1;
    Doctor georgeMorning = new Doctor("George", "morning");
    Doctor bradAfternoon = new Doctor("Brad", "afternoon");
    Doctor georgeAfternoon = new Doctor("George", "afternoon");
    Doctor bradMorning = new Doctor("Brad", "morning");


    @BeforeEach
    void runBefore() {
        schedule1 = new DoctorSchedule();
    }

    @Test
    void testFindDoctorAtTimeAllDoctorCanBeFound() {
        try {
            schedule1.addDoctor(georgeMorning, georgeAfternoon, "monday");
            schedule1.addDoctor(bradMorning, georgeAfternoon, "tuesday");
            schedule1.addDoctor(bradMorning, bradAfternoon, "wednesday");
            schedule1.addDoctor(georgeMorning, bradAfternoon, "thursday");
            schedule1.addDoctor(bradMorning, georgeAfternoon, "friday");
        } catch (NotADayException e) {
            fail("Exception should not have been thrown");
        }
        try {
            assertEquals("George", schedule1.findDoctorAtTime("monday", "morning"));
            assertEquals("George", schedule1.findDoctorAtTime("monday", "afternoon"));
            assertEquals("Brad", schedule1.findDoctorAtTime("tuesday", "morning"));
            assertEquals("George", schedule1.findDoctorAtTime("tuesday", "afternoon"));
            assertEquals("Brad", schedule1.findDoctorAtTime("wednesday", "morning"));
            assertEquals("Brad", schedule1.findDoctorAtTime("wednesday", "afternoon"));
            assertEquals("George", schedule1.findDoctorAtTime("thursday", "morning"));
            assertEquals("Brad", schedule1.findDoctorAtTime("thursday", "afternoon"));
            assertEquals("Brad", schedule1.findDoctorAtTime("friday", "morning"));
            assertEquals("George", schedule1.findDoctorAtTime("friday", "afternoon"));
        } catch (NoDoctorAtTimeException e) {
            fail("Exceptions should not have been thrown!");
        }
    }

    @Test
    void testFindDoctorAtTimeExceptionExceptionExpected() {
        try {
            schedule1.findDoctorAtTime("friday", "night");
            fail("Exception should've been thrown");
        } catch (NoDoctorAtTimeException e)  {
            assertEquals("No doctor can be found at this time!", e.toString());
        }
    }

    @Test
    void testAddDoctorReverseNoExceptionThrown() {
        try {
            schedule1.addDoctor(bradAfternoon, georgeMorning, "monday");
            assertEquals("George", ((schedule1.getMonday()).get(0)).getDoctorName());
            assertEquals("Brad", ((schedule1.getMonday()).get(1)).getDoctorName());

            schedule1.addDoctor(bradAfternoon, georgeMorning, "tuesday");
            assertEquals("George", ((schedule1.getTuesday()).get(0)).getDoctorName());
            assertEquals("Brad", ((schedule1.getTuesday()).get(1)).getDoctorName());

            schedule1.addDoctor(bradAfternoon, georgeMorning, "wednesday");
            assertEquals("George", ((schedule1.getWednesday()).get(0)).getDoctorName());
            assertEquals("Brad", ((schedule1.getWednesday()).get(1)).getDoctorName());

            schedule1.addDoctor(georgeAfternoon, bradMorning, "thursday");
            assertEquals("Brad", ((schedule1.getThursday()).get(0)).getDoctorName());
            assertEquals("George", ((schedule1.getThursday()).get(1)).getDoctorName());

            schedule1.addDoctor(georgeAfternoon, bradMorning, "friday");
            assertEquals("Brad", ((schedule1.getFriday()).get(0)).getDoctorName());
            assertEquals("George", ((schedule1.getFriday()).get(1)).getDoctorName());
        } catch (NotADayException e) {
            fail("No exceptions should've been thrown");
        }
    }

    @Test
    void testAddDoctorMonday() {
        try {
            schedule1.addDoctor(georgeMorning, georgeAfternoon, "monday");
            assertEquals("George", ((schedule1.getMonday()).get(0)).getDoctorName());
            assertEquals(9.00, ((schedule1.getMonday()).get(0)).getDoctorStartTime());
            assertEquals(12.00, ((schedule1.getMonday()).get(0)).getDoctorEndTime());
            assertEquals(15, ((schedule1.getMonday()).get(0)).getInterval());

            assertEquals("George", ((schedule1.getMonday()).get(1)).getDoctorName());
            assertEquals(15.00, ((schedule1.getMonday()).get(1)).getDoctorStartTime());
            assertEquals(18.00, ((schedule1.getMonday()).get(1)).getDoctorEndTime());
            assertEquals(15, ((schedule1.getMonday()).get(1)).getInterval());
        } catch (NotADayException e) {
            fail("No exception should've been thrown");
        }
    }

    @Test
    void testAddDoctorTuesday() {
        try {
            schedule1.addDoctor(bradMorning, bradAfternoon, "tuesday");
            assertEquals("Brad", ((schedule1.getTuesday()).get(0)).getDoctorName());
            assertEquals(9.00, ((schedule1.getTuesday()).get(0)).getDoctorStartTime());
            assertEquals(12.00, ((schedule1.getTuesday()).get(0)).getDoctorEndTime());
            assertEquals(15, ((schedule1.getTuesday()).get(0)).getInterval());

            assertEquals("Brad", ((schedule1.getTuesday()).get(1)).getDoctorName());
            assertEquals(15.00, ((schedule1.getTuesday()).get(1)).getDoctorStartTime());
            assertEquals(18.00, ((schedule1.getTuesday()).get(1)).getDoctorEndTime());
            assertEquals(15, ((schedule1.getTuesday()).get(1)).getInterval());
        } catch (NotADayException e) {
            fail("No exception should've been thrown");
        }
    }

    @Test
    void testAddDoctorWednesday() {
        try {
            schedule1.addDoctor(georgeMorning, bradAfternoon, "wednesday");
            assertEquals("George", ((schedule1.getWednesday()).get(0)).getDoctorName());
            assertEquals(9.00, ((schedule1.getWednesday()).get(0)).getDoctorStartTime());
            assertEquals(12.00, ((schedule1.getWednesday()).get(0)).getDoctorEndTime());
            assertEquals(15, ((schedule1.getWednesday()).get(0)).getInterval());

            assertEquals("Brad", ((schedule1.getWednesday()).get(1)).getDoctorName());
            assertEquals(15.00, ((schedule1.getWednesday()).get(1)).getDoctorStartTime());
            assertEquals(18.00, ((schedule1.getWednesday()).get(1)).getDoctorEndTime());
            assertEquals(15, ((schedule1.getWednesday()).get(1)).getInterval());
        } catch (NotADayException e) {
            fail("No exception should've been thrown");
        }
    }

    @Test
    void testAddDoctorThursday() {
        try {
            schedule1.addDoctor(georgeMorning, georgeAfternoon, "thursday");
            assertEquals("George", ((schedule1.getThursday()).get(0)).getDoctorName());
            assertEquals(9.00, ((schedule1.getThursday()).get(0)).getDoctorStartTime());
            assertEquals(12.00, ((schedule1.getThursday()).get(0)).getDoctorEndTime());
            assertEquals(15, ((schedule1.getThursday()).get(0)).getInterval());

            assertEquals("George", ((schedule1.getThursday()).get(1)).getDoctorName());
            assertEquals(15.00, ((schedule1.getThursday()).get(1)).getDoctorStartTime());
            assertEquals(18.00, ((schedule1.getThursday()).get(1)).getDoctorEndTime());
            assertEquals(15, ((schedule1.getThursday()).get(1)).getInterval());
        } catch (NotADayException e) {
            fail("No exception should've been thrown");
        }
    }

    @Test
    void testAddDoctorFriday() {
        try {
            schedule1.addDoctor(bradMorning, georgeAfternoon, "friday");
            assertEquals("Brad", ((schedule1.getFriday()).get(0)).getDoctorName());
            assertEquals(9.00, ((schedule1.getFriday()).get(0)).getDoctorStartTime());
            assertEquals(12.00, ((schedule1.getFriday()).get(0)).getDoctorEndTime());
            assertEquals(15, ((schedule1.getFriday()).get(0)).getInterval());

            assertEquals("George", ((schedule1.getFriday()).get(1)).getDoctorName());
            assertEquals(15.00, ((schedule1.getFriday()).get(1)).getDoctorStartTime());
            assertEquals(18.00, ((schedule1.getFriday()).get(1)).getDoctorEndTime());
            assertEquals(15, ((schedule1.getFriday()).get(1)).getInterval());
        } catch (NotADayException e) {
            fail("No exception should've been thrown");
        }
    }

    @Test
    void testAddDoctorExceptionExcepted() {
        try {
            schedule1.addDoctor(bradMorning, georgeAfternoon, "saturday");
            fail("Exception should've been thrown");
        } catch (NotADayException e) {
            assertEquals("String entered is not a weekday!", e.toString());
        }
    }
}
