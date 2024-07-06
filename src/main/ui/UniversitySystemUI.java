package ui;

import model.GradeSheet;
import model.Teacher;
import model.University;
import model.Student;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;

//EFFECTS: initialize the objects and variables required in the system

public class UniversitySystemUI {
    private static final String JSON_STORE = "./data/university.json";
    private University us;
    int temp = 0;

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    //EFFECTS: helps to run the program and continues to run if the user enters true and if the user enters false the
    // program stops and quit

    public UniversitySystemUI() throws FileNotFoundException {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        us = new University(new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        Scanner scan = new Scanner(System.in);
        boolean reply = true;
        loadUniversity();

        while (reply) {
            showMainMenu();
            Integer option = Integer.parseInt(scan.nextLine());
            switch (option) {
                case 1:
                    studentOption(scan);
                    break;

                case 2:
                    teacherOption(scan);
                    break;

                default:
                    throw new IllegalStateException("Unexpected value: " + option);
            }
            System.out.println("Type true to login again or false to logout of system");
            reply = Boolean.parseBoolean(scan.nextLine());
        }
        saveUniversity();
    }

    private void studentOption(Scanner scan) {
        System.out.println("Hello Student");
        System.out.println("Enter your Student ID to begin: ");
        Integer sid = Integer.parseInt(scan.nextLine());
        for (int i = 0; i < us.getStudents().size(); i++) {
            if (us.getStudents().get(i).getSid() == sid) {
                temp = 1;
                studentMenuViewer();
                Integer choice = Integer.parseInt(scan.nextLine());
                studentChoice(choice, i);
            }
        }
        if (temp != 1) {
            System.out.println("Incorrect Student ID");
            System.out.println("Authorization Revoked");
        }
    }

    //EFFECTS: takes the value of the desired option present in the switch
    private void studentChoice(Integer choice, Integer i) {
        switch (choice) {
            case 1:
                studentDetailViewer(i);
                break;

            case 2:
                calculatePercentage(i);
                break;

            case 3:
                viewGradeSheet(i);
                break;

            case 4:
                majorDeclare(i);
                break;
        }
    }

    //EFFECTS: declares the major to a student based on their percentage obtained
    private void majorDeclare(Integer i) {
        if (us.getGradeSheets().get(i).getPercentage() >= 90) {
            System.out.println("Computer Science");
        } else if (us.getGradeSheets().get(i).getPercentage() > 80 && us.getGradeSheets().get(i).getPercentage() < 90) {
            System.out.println("Statistics");
        } else {
            System.out.println("Mathematics");
        }
    }

    //EFFECTS: displays the grade sheet of a student
    private void viewGradeSheet(Integer i) {
        System.out.println("Your Grade Summary :- ");
        System.out.println("ECON 101 " + us.getGradeSheets().get(i).getEcon101());
        System.out.println("ECON 102 " + us.getGradeSheets().get(i).getEcon102());
        System.out.println("CPSC 103 " + us.getGradeSheets().get(i).getCpsc103());
        System.out.println("CPSC 121 " + us.getGradeSheets().get(i).getCpsc121());
        System.out.println("MATH 100 " + us.getGradeSheets().get(i).getMath100());
        System.out.println("MATH 101 " + us.getGradeSheets().get(i).getMath101());
        System.out.println("LING 100 " + us.getGradeSheets().get(i).getLing100());
        System.out.println("LING 101 " + us.getGradeSheets().get(i).getLing101());
        System.out.println("PHYS 101 " + us.getGradeSheets().get(i).getPhys101());
        System.out.println("SCI 113 " + us.getGradeSheets().get(i).getSci113());
    }

    //EFFECTS: displays the final percentage of a student based on their grades in the grade sheet
    private void calculatePercentage(Integer i) {
        System.out.println("Your Final Percentage is :- " + us.getGradeSheets().get(i)
                .getPercentage());
    }

    //EFFECTS: displays the details of a student including their name and student ID
    private void studentDetailViewer(Integer i) {
        System.out.println("Your name is :- " + us.getStudents().get(i)
                .getSname());
        System.out.println("Your Student ID is :- " + us.getStudents().get(i)
                .getSid());
    }

    //EFFECTS: displays the options available to a student
    private void studentMenuViewer() {
        System.out.println("Press 1 to view your details");
        System.out.println("Press 2 to calculate percentage");
        System.out.println("Press 3 to view Grade Sheet");
        System.out.println("Press 4 to check my major");
    }

    //EFFECTS: checks if the teacher has entered their teacher ID correctly to access the system and if the teacher
    // has entered their teacher id incorrect, then they will be stopped to access the system further
    private void teacherOption(Scanner scan) {
        System.out.println("Hello Teacher");
        System.out.println("Enter your Teacher ID to begin: ");
        Integer teacherID = Integer.parseInt(scan.nextLine());
        if (teacherID == 2405) {
            temp = 1;
            teacherMenuViewer();
            Integer choice = Integer.parseInt(scan.nextLine());
            teacherChoice(scan, choice, 2405);
        }

        if (temp != 1) {
            System.out.println("Incorrect Teacher ID");
            System.out.println("Authorization Revoked");
        }
    }

    //EFFECTS: displays various choices to a teacher that are present in the switch
    private void teacherChoice(Scanner scan, Integer choice, int i) {
        switch (choice) {
            case 1:
                addStudentToUniversity(scan);
                break;

            case 2:
                calculatePercentageStudent(scan);
                break;

            case 3:
                viewGradeSheetStudent(scan);
                break;

            case 4:
                enterGradeStudent(scan);
                break;

        }
    }

    //EFFECTS: adds a new student to the university
    private void addStudentToUniversity(Scanner scan) {
        System.out.println("Enter the Student ID of new student");
        Integer sid = Integer.parseInt(scan.nextLine());
        System.out.println("Enter name of the new Student");
        String sname = scan.nextLine();
        us.addStudent(new Student(sid, sname, "undeclared"));
        us.getGradeSheets().add(new GradeSheet(sid));
    }

    //EFFECTS: gives permission to the user to add the grades of a student with student ID
    private void enterGradeStudent(Scanner scan) {
        System.out.println("Enter Student ID of student for grade entry");
        Integer sid = Integer.parseInt(scan.nextLine());
        for (GradeSheet gradeSheet : us.getGradeSheets()) {
            if (gradeSheet.getSid() == sid) {
                for (int l = 0; l < us.getStudents().size(); l++) {
                    if (us.getStudents().get(l).getSid() == sid) {
                        gradeEntry(scan, l);
                    }
                }
            }
        }
    }

    //EFFECTS: enters grades of a student in their grade sheet
    private void gradeEntry(Scanner scan, int l) {
        System.out.println("ECON 101 ");
        us.getGradeSheets().get(l).setEcon101(Integer.parseInt(scan.nextLine()));
        System.out.println("ECON 102 ");
        us.getGradeSheets().get(l).setEcon102(Integer.parseInt(scan.nextLine()));
        System.out.println("CPSC 103 ");
        us.getGradeSheets().get(l).setCpsc103(Integer.parseInt(scan.nextLine()));
        System.out.println("CPSC 121 ");
        us.getGradeSheets().get(l).setCpsc121(Integer.parseInt(scan.nextLine()));
        System.out.println("MATH 100 ");
        us.getGradeSheets().get(l).setMath100(Integer.parseInt(scan.nextLine()));
        System.out.println("MATH 101 ");
        us.getGradeSheets().get(l).setMath101(Integer.parseInt(scan.nextLine()));
        System.out.println("LING 100 ");
        us.getGradeSheets().get(l).setLing100(Integer.parseInt(scan.nextLine()));
        System.out.println("LING 101 ");
        us.getGradeSheets().get(l).setLing101(Integer.parseInt(scan.nextLine()));
        System.out.println("PHYS 101 ");
        us.getGradeSheets().get(l).setPhys101(Integer.parseInt(scan.nextLine()));
        System.out.println("SCI 113 ");
        us.getGradeSheets().get(l).setSci113(Integer.parseInt(scan.nextLine()));
    }

    //EFFECTS: gives permission to the user to view the grade sheet of a student with student ID
    private void viewGradeSheetStudent(Scanner scan) {
        System.out.println("Enter Student ID of Student whose grade sheet you want to view?");
        Integer sid = Integer.parseInt(scan.nextLine());
        for (GradeSheet gradeSheet : us.getGradeSheets()) {
            if (gradeSheet.getSid() == sid) {
                for (int l = 0; l < us.getStudents().size(); l++) {
                    if (us.getStudents().get(l).getSid() == sid) {
                        gradeSheetViewerStudent(l);
                    }
                }
            }
        }
    }

    //EFFECTS: displays the grade sheet of a student with student ID
    private void gradeSheetViewerStudent(int i) {
        System.out.println("ECON 101 " + us.getGradeSheets().get(i).getEcon101());
        System.out.println("ECON 102 " + us.getGradeSheets().get(i).getEcon102());
        System.out.println("CPSC 103 " + us.getGradeSheets().get(i).getCpsc103());
        System.out.println("CPSC 121 " + us.getGradeSheets().get(i).getCpsc121());
        System.out.println("MATH 100 " + us.getGradeSheets().get(i).getMath100());
        System.out.println("MATH 101 " + us.getGradeSheets().get(i).getMath101());
        System.out.println("LING 100 " + us.getGradeSheets().get(i).getLing100());
        System.out.println("LING 101 " + us.getGradeSheets().get(i).getLing101());
        System.out.println("PHYS 101 " + us.getGradeSheets().get(i).getPhys101());
        System.out.println("SCI 113 " + us.getGradeSheets().get(i).getSci113());
    }

    //EFFECTS: calculates and displays the percentage obtained by a student with the student ID
    private void calculatePercentageStudent(Scanner scan) {
        System.out.println("Enter Student ID of Student whose grade sheet you want to view?");
        Integer sid = Integer.parseInt(scan.nextLine());
        for (GradeSheet gradeSheet : us.getGradeSheets()) {
            if (gradeSheet.getSid() == sid) {
                for (int l = 0; l < us.getStudents().size(); l++) {
                    if (us.getStudents().get(l).getSid() == sid) {
                        System.out.println(us.getGradeSheets().get(l).getPercentage());
                    }
                }
            }
        }
    }

    //EFFECTS: displays the details of a teacher including their teacher ID
    private void teacherDetailViewer(int i) {
        System.out.println("Your Teacher ID is :- " + us.getTeachers().get(i)
                .getTeacherID());
    }

    //EFFECT: displays the various options available to the teacher
    private void teacherMenuViewer() {
        System.out.println("Press 1 to add student");
        System.out.println("Press 2 to calculate student percentage");
        System.out.println("Press 3 to view student Grade Sheet");
        System.out.println("Press 4 to enter student's grades");
    }

    //EFFECT: creates and displays the main menu for console
    private void showMainMenu() {
        System.out.println("Welcome to University of Techno Lords");
        System.out.println("Choose one of the following option");
        System.out.println("Press 1 if you are a Student");
        System.out.println("Press 2 if you are a Teacher");
    }

    // EFFECTS: saves the university to file
    private void saveUniversity() {
        try {
            jsonWriter.open();
            jsonWriter.write(us);
            jsonWriter.close();
            System.out.println("Saved the University's data to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads university from file
    private void loadUniversity() {
        try {
            us = jsonReader.read();
            System.out.println("Loaded the University's data from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}