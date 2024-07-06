package model;

import org.json.JSONObject;
import persistence.Writable;

public class Student implements Writable {
    private int sid;
    private String sname;
    private String major;

    //EFFECTS: Initializes the data needed and constructs a Student object
    public Student(int id, String name, String major) {
        this.sid = id;
        this.sname = name;
        this.major = major;
    }

    public String getMajor() {
        return major;
    }

    public int getSid() {
        if (sid == 2405) {
            return -1;
        } else {
            return sid;
        }
    }

    public String getSname() {
        return sname;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("studentID", sid);
        json.put("name", sname);
        json.put("major", major);
        return json;
    }
}
