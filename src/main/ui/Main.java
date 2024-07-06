package ui;

import model.Student;
import model.Teacher;
import model.University;
import model.GradeSheet;

import java.util.Scanner;
import java.util.*;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        try {
            UniversitySystemUI universitySystemUI = new UniversitySystemUI();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }
    }
}
