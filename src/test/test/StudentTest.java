package test;

import model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.json.JSONObject;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StudentTest {
    Student s1, s2;

    @BeforeEach
    public void studentSetUp() {
        s1 = new Student(4321, "Abhinav", "Computer Science");
        s2 = new Student(2405, "R", "Mathematics");
    }

    @Test
    public void testGetStudentID() {
        assertEquals(4321, s1.getSid());
    }

    @Test
    public void testGetStudentIDSameTeacherID() {
        assertEquals(-1, s2.getSid());
    }

    @Test
    public void testGetStudentName() {
        assertEquals("Abhinav", s1.getSname());
    }

    @Test
    public void testGetMajor() {
        assertEquals("Computer Science", s1.getMajor());
    }

    @Test
    public void testToJson() {
        JSONObject s = s1.toJson();
        assertEquals("Abhinav", s.getString("name"));
        assertEquals(4321, s.getInt("studentID"));
        assertEquals("Computer Science", s.getString("major"));
    }
}



