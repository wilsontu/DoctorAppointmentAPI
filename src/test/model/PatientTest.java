package model;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.Writable;

import static org.junit.jupiter.api.Assertions.*;

public class PatientTest {
    private Patient bob;
    private Patient joe;

    @BeforeEach
    void runBefore() {
        bob = new Patient("Bob");
        joe = new Patient("Joe");
    }

    @Test
    void testConstructor() {
        assertEquals("Bob", bob.getName());
        assertEquals(0, bob.getAppointNum());

        assertEquals("Joe", joe.getName());
        assertEquals(0, joe.getAppointNum());
    }

    @Test
    void testSetAppointNum() {
        bob.setAppointNum(1);
        assertEquals(1, bob.getAppointNum());

        joe.setAppointNum(2);
        assertEquals(2, joe.getAppointNum());
    }

    @Test
    void testSetAppointDay() {
        bob.setAppointDay("monday");
        assertEquals("monday", bob.getAppointDay());

        joe.setAppointDay("tuesday");
        assertEquals("tuesday", joe.getAppointDay());
    }

    @Test
    void testSetAppointTime() {
        bob.setAppointTime("morning");
        assertEquals("morning", bob.getAppointTime());

        joe.setAppointTime("afternoon");
        assertEquals("afternoon", joe.getAppointTime());
    }

    @Test
    void testToJson() {
        bob.setAppointNum(1);
        bob.setAppointTime("morning");
        bob.setAppointDay("monday");
        JSONObject bobJson = bob.toJson();
        assertEquals("Bob", bobJson.getString("name"));
        assertEquals("monday", bobJson.getString("appointDay"));
        assertEquals("morning", bobJson.getString("appointTime"));
        assertEquals(1, bobJson.getInt("appointNum"));

        joe.setAppointDay("friday");
        joe.setAppointTime("afternoon");
        joe.setAppointNum(5);
        JSONObject joeJson = joe.toJson();
        assertEquals("Joe", joeJson.getString("name"));
        assertEquals("friday", joeJson.getString("appointDay"));
        assertEquals("afternoon", joeJson.getString("appointTime"));
        assertEquals(5, joeJson.getInt("appointNum"));

        Patient brad = new Patient("Brad");
        brad.setAppointDay("wednesday");
        brad.setAppointTime("morning");
        brad.setAppointNum(3);
        Writable bradTestOverride = brad;
        JSONObject bradJson = bradTestOverride.toJson();
        assertEquals("Brad", bradJson.getString("name"));
        assertEquals("wednesday", bradJson.getString("appointDay"));
        assertEquals("morning", bradJson.getString("appointTime"));
        assertEquals(3, bradJson.getInt("appointNum"));
    }
}
