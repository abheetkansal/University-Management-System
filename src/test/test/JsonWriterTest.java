package test;

import model.*;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest {

    @Test
    void testWriterInvalidFile() {
        try {
            University u = new University(new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyUniversity() {
        try {
            University u = new University(new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyWorkroom.json");
            writer.open();
            writer.write(u);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyWorkroom.json");
            u = reader.read();
            assertEquals(0, u.getTeachers().size());
            assertEquals(0, u.getStudents().size());
            assertEquals(0, u.getGradeSheets().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralUniversity() {
        try {
            University u = new University(new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
            u.addTeacher(new Teacher(2405));
            u.addStudent(new Student(1234, "alex", "Statistics"));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralWorkroom.json");
            writer.open();
            writer.write(u);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralWorkroom.json");
            u = reader.read();
            assertEquals(1, u.getTeachers().size());
            assertEquals(1, u.getStudents().size());
            assertEquals("alex",u.getStudents().get(0).getSname());
            assertEquals(1234,u.getStudents().get(0).getSid());
            assertEquals(0, u.getGradeSheets().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}