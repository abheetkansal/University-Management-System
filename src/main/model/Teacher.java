package model;


import org.json.JSONObject;
import persistence.Writable;

public class Teacher implements Writable {

    private int teacherID;

    //EFFECTS: Initializes the data needed and constructs a Teacher object
    public Teacher(int id) {
        this.teacherID = id;
    }

    public int getTeacherID() {
        return teacherID;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("teacherID", teacherID);
        return json;
    }

}