package test;

import model.GradeSheet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.json.JSONObject;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class GradeSheetTest {
    GradeSheet t1;

    @BeforeEach
    public void gradeSheetSetUp() {
        t1 = new GradeSheet(4321);
    }

    @Test
    public void testSetStudentID() {
        t1.setSid(4321);
        assertEquals(4321, t1.getSid());
    }

    @Test
    public void testSetEcon101() {
        t1.setEcon101(90);
        assertEquals(90, t1.getEcon101());
    }

    @Test
    public void testSetEcon102() {
        t1.setEcon102(100);
        assertEquals(100, t1.getEcon102());
    }

    @Test
    public void testSetCpsc103() {
        t1.setCpsc103(95);
        assertEquals(95, t1.getCpsc103());
    }

    @Test
    public void testSetCpsc121() {
        t1.setCpsc121(100);
        assertEquals(100, t1.getCpsc121());
    }

    @Test
    public void testSetMath100() {
        t1.setMath100(84);
        assertEquals(84, t1.getMath100());
    }

    @Test
    public void testSetMath101() {
        t1.setMath101(96);
        assertEquals(96, t1.getMath101());
    }

    @Test
    public void testSetLing100() {
        t1.setLing100(67);
        assertEquals(67, t1.getLing100());
    }

    @Test
    public void testSetLing101() {
        t1.setLing101(73);
        assertEquals(73, t1.getLing101());
    }

    @Test
    public void testSetPhys101() {
        t1.setPhys101(0);
        assertEquals(0, t1.getPhys101());
    }

    @Test
    public void testSetSci113() {
        t1.setSci113(69);
        assertEquals(69, t1.getSci113());
    }

    @Test
    public void testGetPercentage() {
        t1.setEcon101(90);
        t1.setEcon102(100);
        t1.setCpsc103(95);
        t1.setCpsc121(100);
        t1.setMath100(84);
        t1.setMath101(96);
        t1.setLing100(67);
        t1.setLing101(73);
        t1.setPhys101(0);
        t1.setSci113(69);
        assertEquals(77.4, t1.getPercentage());
    }

    @Test
    public void testGetPercentage0() {
        t1.setEcon101(0);
        t1.setEcon102(0);
        t1.setCpsc103(0);
        t1.setCpsc121(0);
        t1.setMath100(0);
        t1.setMath101(0);
        t1.setLing100(0);
        t1.setLing101(0);
        t1.setPhys101(0);
        t1.setSci113(0);
        assertEquals(0, t1.getPercentage());
    }

    @Test
    public void testGetPercentage50() {
        t1.setEcon101(50);
        t1.setEcon102(50);
        t1.setCpsc103(50);
        t1.setCpsc121(50);
        t1.setMath100(50);
        t1.setMath101(50);
        t1.setLing100(50);
        t1.setLing101(50);
        t1.setPhys101(50);
        t1.setSci113(50);
        assertEquals(50, t1.getPercentage());
    }

    @Test
    public void testToJson() {
        JSONObject t = t1.toJson();
        assertEquals(4321, t.getInt("studentID"));
        assertEquals(0, t.getInt("econ101"));
        assertEquals(0, t.getInt("econ102"));
        assertEquals(0, t.getInt("cpsc103"));
        assertEquals(0, t.getInt("cpsc121"));
        assertEquals(0, t.getInt("math100"));
        assertEquals(0, t.getInt("math101"));
        assertEquals(0, t.getInt("ling100"));
        assertEquals(0, t.getInt("ling101"));
        assertEquals(0, t.getInt("phys101"));
        assertEquals(0, t.getInt("sci113"));
    }
}

