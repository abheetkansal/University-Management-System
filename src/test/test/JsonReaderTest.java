package test;

import model.*;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest  {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            University u = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyUniversity() {
        JsonReader reader = new JsonReader("./data/testReaderEmptySchool.json");
        try {
            University u = reader.read();
            assertEquals(0, u.getTeachers().size());
            assertEquals(0, u.getStudents().size());
            assertEquals(0, u.getGradeSheets().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralUniversity() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralUniversity.json");
        try {
            University u = reader.read();
            assertEquals(1, u.getTeachers().size());
            assertEquals(1, u.getStudents().size());
            assertEquals("alex",u.getStudents().get(0).getSname());
            assertEquals(1, u.getGradeSheets().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}