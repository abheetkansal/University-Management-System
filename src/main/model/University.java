package model;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

public class University implements Writable {
    private List<Teacher> teachers;
    private List<Student> students;
    private List<GradeSheet> gradeSheets;

    //EFFECTS: Initializes the data needed as lists of teachers, students, and grade sheets and constructs a University
    // object
    public University(List<Teacher> teachers, List<Student> students, List<GradeSheet> gradeSheets) {
        this.teachers = teachers;
        this.students = students;
        this.gradeSheets = gradeSheets;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public List<Student> getStudents() {
        return students;
    }

    public List<GradeSheet> getGradeSheets() {
        return gradeSheets;
    }

    //MODIFIES: this
    //EFFECTS: adds a new teacher to the list of teachers
    public void addTeacher(Teacher teacher) {
        teachers.add(teacher);
    }

    //MODIFIES: this
    //EFFECTS: adds a new student to the list of students
    public void addStudent(Student student) {
        students.add(student);
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("teachers", teachersToJson());
        json.put("students", studentsToJson());
        json.put("gradeSheets", gradeSheetsToJson());
        return json;
    }

    // EFFECTS: returns things in this workroom as a JSON array
    private JSONArray teachersToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Teacher t : teachers) {
            jsonArray.put(t.toJson());
        }

        return jsonArray;
    }

    // EFFECTS: returns things in this workroom as a JSON array
    private JSONArray studentsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Student s : students) {
            jsonArray.put(s.toJson());
        }

        return jsonArray;
    }

    // EFFECTS: returns things in this workroom as a JSON array
    private JSONArray gradeSheetsToJson() {
        JSONArray jsonArray = new JSONArray();
        return jsonArray;
    }
}





