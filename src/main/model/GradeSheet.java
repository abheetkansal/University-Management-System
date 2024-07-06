package model;

import org.json.JSONObject;
import persistence.Writable;

public class GradeSheet implements Writable {
    private int sid;
    private double econ101;
    private double econ102;
    private double cpsc103;
    private double cpsc121;
    private double math100;
    private double math101;
    private double ling100;
    private double ling101;
    private double phys101;
    private double sci113;
    private double percentage;

    //EFFECTS: Initializes the data needed and constructs an object for GradeSheet
    public GradeSheet(int studentID) {
        this.sid = studentID;
        this.econ101 = 0;
        this.econ102 = 0;
        this.cpsc103 = 0;
        this.cpsc121 = 0;
        this.math100 = 0;
        this.math101 = 0;
        this.ling100 = 0;
        this.ling101 = 0;
        this.phys101 = 0;
        this.sci113 = 0;
        this.percentage = 0;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public int getSid() {
        return sid;
    }

    //MODIFIES: this
    //EFFECTS: collects all the grades for courses and yields the percentage of the student's grade in all courses
    public double getPercentage() {
        percentage = (econ101 + econ102 + cpsc103 + cpsc121 + math100 + math101 + ling100 + ling101 + phys101
                + sci113) / 10;
        return percentage;
    }

    public double getEcon101() {
        return econ101;
    }

    public double getEcon102() {
        return econ102;
    }

    public double getCpsc103() {
        return cpsc103;
    }

    public double getCpsc121() {
        return cpsc121;
    }

    public double getMath100() {
        return math100;
    }

    public double getMath101() {
        return math101;
    }

    public double getLing100() {
        return ling100;
    }

    public double getLing101() {
        return ling101;
    }

    public double getPhys101() {
        return phys101;
    }

    public double getSci113() {
        return sci113;
    }

    public void setEcon101(double econ101) {
        this.econ101 = econ101;
    }

    public void setEcon102(double econ102) {
        this.econ102 = econ102;
    }

    public void setCpsc103(double cpsc103) {
        this.cpsc103 = cpsc103;
    }

    public void setCpsc121(double cpsc121) {
        this.cpsc121 = cpsc121;
    }

    public void setMath100(double math100) {
        this.math100 = math100;
    }

    public void setMath101(double math101) {
        this.math101 = math101;
    }

    public void setLing100(double ling100) {
        this.ling100 = ling100;
    }

    public void setLing101(double ling101) {
        this.ling101 = ling101;
    }

    public void setPhys101(double phys101) {
        this.phys101 = phys101;
    }

    public void setSci113(double sci113) {
        this.sci113 = sci113;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("studentID", sid);
        json.put("econ101", econ101);
        json.put("econ102", econ102);
        json.put("cpsc103", cpsc103);
        json.put("cpsc121", cpsc121);
        json.put("math100", math100);
        json.put("math101", math101);
        json.put("ling100", ling100);
        json.put("ling101", ling101);
        json.put("phys101", phys101);
        json.put("sci113", sci113);
        return json;
    }


}
