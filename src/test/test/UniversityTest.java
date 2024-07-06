package test;

import model.GradeSheet;
import model.Student;
import model.Teacher;
import model.University;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UniversityTest {
    University u1;

    @BeforeEach
    public void universitySetUp() {
        u1 = new University(new ArrayList<>(), new ArrayList<>(),
                new ArrayList<>());
    }

    @Test
    public void testAddTeacher() {
        Teacher t1 = new Teacher(1234);
        u1.addTeacher(t1);
        assertTrue(u1.getTeachers().contains(t1));
    }

    @Test
    public void testAddStudent() {
        Student s1 = new Student(2000, "alex", "Computer Science");
        u1.addStudent(s1);
        assertTrue(u1.getStudents().contains(s1));
    }

    @Test
    public void testGetGradeSheets() {
        assertEquals(0, u1.getGradeSheets().size());
    }

    @Test
    public void testGetGradesOccupied() {
        u1.getGradeSheets().add(new GradeSheet(1234));
        assertEquals(1, u1.getGradeSheets().size());
    }


}

