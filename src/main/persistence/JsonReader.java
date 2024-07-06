package persistence;

import model.University;
import model.Teacher;
import model.Student;
import model.GradeSheet;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

// Represents a reader that reads University from JSON data that is stored in the file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads University from file and returns it;
    // throws IOException if an error occurs reading data from file
    public University read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseUniversity(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses University from JSON object and returns it
    private University parseUniversity(JSONObject jsonObject) {
        University u = new University(new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        addTeachersToList(u, jsonObject);
        addStudentsToList(u, jsonObject);
        addGradeSheetsToList(u, jsonObject);
        return u;
    }

    // MODIFIES: u
    // EFFECTS: parses list of teachers from JSON object and adds them to University
    private void addTeachersToList(University u, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("teachers");
        for (Object json : jsonArray) {
            JSONObject nextTeacher = (JSONObject) json;
            addTeacher(u, nextTeacher);
        }
    }

    // MODIFIES: u
    // EFFECTS: parses list of students from JSON object and adds them to University
    private void addStudentsToList(University u, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("students");
        for (Object json : jsonArray) {
            JSONObject nextStudent = (JSONObject) json;
            addStudent(u, nextStudent);
        }
    }

    // MODIFIES: u
    // EFFECTS: parses list of gradesheets from JSON object and adds them to University
    private void addGradeSheetsToList(University u, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("gradeSheets");
        for (Object json : jsonArray) {
            JSONObject nextGradeSheet = (JSONObject) json;
            addGradeSheet(u, nextGradeSheet);
        }
    }

    // MODIFIES: u
    // EFFECTS: parses teacher from JSON object and adds it to University
    private void addTeacher(University u, JSONObject jsonObject) {
        Integer id = jsonObject.getInt("teacherID");
        Teacher teacher = new Teacher(id);
        u.addTeacher(teacher);
    }

    // MODIFIES: u
    // EFFECTS: parses student from JSON object and adds it to University
    private void addStudent(University u, JSONObject jsonObject) {
        Integer id = jsonObject.getInt("studentID");
        String name = jsonObject.getString("name");
        String major = jsonObject.getString("major");
        Student student = new Student(id,name, major);
        u.addStudent(student);
    }

    // MODIFIES: u
    // EFFECTS: parses gradesheet from JSON object and adds it to University
    private void addGradeSheet(University u, JSONObject jsonObject) {
        Integer sid = jsonObject.getInt("studentID");
        Double econ101 = jsonObject.getDouble("econ101");
        Double econ102 = jsonObject.getDouble("econ102");
        Double cpsc103 = jsonObject.getDouble("cpsc103");
        Double cpsc121 = jsonObject.getDouble("cpsc121");
        Double math100 = jsonObject.getDouble("math100");
        Double math101 = jsonObject.getDouble("math101");
        Double ling100 = jsonObject.getDouble("ling100");
        Double ling101 = jsonObject.getDouble("ling101");
        Double phys101 = jsonObject.getDouble("phys101");
        Double sci113 = jsonObject.getDouble("sci113");
        GradeSheet gradeSheet = new GradeSheet(sid);
        setGradeSheets(u, econ101, econ102, cpsc103, cpsc121, math100,
                math101, ling100, ling101, phys101, sci113, gradeSheet);
    }

    // MODIFIES: u, econ101, econ102, cpsc103, cpsc121, math100, math101, ling100, ling101, phys101, sci113
    // EFFECTS: sets gradesheet from JSON object
    private void setGradeSheets(University u, Double econ101, Double econ102, Double cpsc103, Double cpsc121,
                                Double math100, Double math101, Double ling100, Double ling101, Double phys101,
                                Double sci113, GradeSheet gradeSheet) {
        gradeSheet.setEcon101(econ101);
        gradeSheet.setEcon102(econ102);
        gradeSheet.setCpsc103(cpsc103);
        gradeSheet.setCpsc121(cpsc121);
        gradeSheet.setMath100(math100);
        gradeSheet.setMath101(math101);
        gradeSheet.setLing100(ling100);
        gradeSheet.setLing101(ling101);
        gradeSheet.setPhys101(phys101);
        gradeSheet.setSci113(sci113);
        u.getGradeSheets().add(gradeSheet);
    }
}
