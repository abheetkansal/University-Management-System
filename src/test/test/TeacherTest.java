package test;

import model.Teacher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.json.JSONObject;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TeacherTest {
    Teacher t1;

    @BeforeEach
    public void teacherSetUp() {
        t1 = new Teacher(1234);
    }

    @Test
    public void testGetTeacherID() {
        t1.getTeacherID();
        assertEquals(1234, t1.getTeacherID());
    }

    @Test
    public void testToJson() {
        JSONObject t = t1.toJson();
        assertEquals(1234, t.getInt("teacherID"));
    }

}

