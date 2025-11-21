import java.util.*;

class Main{
    public static void main(String[] args) {
        ResultManager rm = new ResultManager();
        rm.mainMenu();
    }
}

class InvalidMarksException extends Exception {
    public InvalidMarksException(String msg) {
        super(msg);
    }
}

class Student {

    int rollNumber;
    String studentName;
    int[] marks = new int[3];

    public Student(int roll, String name, int[] marks) {
        this.rollNumber = roll;
        this.studentName = name;
        this.marks = marks;
    }

    public void validateMarks() throws InvalidMarksException {
        if (marks == null || marks.length != 3) {
            throw new InvalidMarksException("Marks data is missing!");
        }

        for (int m : marks) {
            if (m == -1) {
                throw new InvalidMarksException("Marks cannot be NULL!");
            }
            if (m < 0 || m > 100) {
                throw new InvalidMarksException("Marks must be between 0 and 100!");
            }
        }
    }

    public double calculateAverage() {
        int sum = 0;
        for (int m : marks) sum += m;
        return sum / 3.0;
    }

    public void displayResult() {
        System.out.println("\n--- Student Result ---");
        System.out.println("Roll No.: " + rollNumber);
        System.out.println("Name: " + studentName);
        System.out.println("Marks:");

        boolean pass = true;
        for (int i = 0; i < 3; i++) {
            System.out.println("Subject " + (i + 1) + ": " + marks[i]);
            if (marks[i] < 40) pass = false; 
        }

        System.out.println("Average: " + calculateAverage());
        System.out.println("Status: " + (pass ? "PASS" : "FAIL"));
    }
}

public class ResultManager {

    Student[] students = new Student[100];
    int count = 0;
    Scanner sc = new Scanner(System.in);

    public void addStudent() {
        try {
            System.out.print("Enter Roll Number: ");
            int roll = sc.nextInt();

            sc.nextLine();
            System.out.print("Enter Student Name: ");
            String name = sc.nextLine();

            if (name == null || name.trim().isEmpty()) {
                throw new NullPointerException("Student name cannot be empty!");
            }

            int[] marks = new int[3];
            System.out.println("Enter marks of 3 subjects:");

            for (int i = 0; i < 3; i++) {
                System.out.print("Subject " + (i + 1) + ": ");
                marks[i] = sc.nextInt();
            }

            Student s = new Student(roll, name, marks);
            s.validateMarks(); 

            students[count++] = s;
            System.out.println("Student Added Successfully!");

        } catch (InvalidMarksException e) {
            System.out.println("Invalid Marks Error: " + e.getMessage());

        } catch (InputMismatchException e) {
            System.out.println("Input Mismatch Error! Please enter numeric values only.");
            sc.nextLine(); 

        } catch (NullPointerException e) {
            System.out.println("Missing Data Error: " + e.getMessage());

        } catch (Exception e) {
            System.out.println("Unexpected Error: " + e.getMessage());
        }
    }

    public void showStudentDetails() {
        try {
            System.out.print("Enter Roll Number to Search: ");
            int roll = sc.nextInt();

            boolean found = false;
            for (int i = 0; i < count; i++) {
                if (students[i].rollNumber==roll ) {
                    students[i].displayResult();
                    found = true;
                    break;
                }
            }
            if (!found) System.out.println("Student Not Found!");
        }
        catch (InputMismatchException e) {
            System.out.println("Input Mismatch! Please enter a valid roll number.");
            sc.nextLine();
        }
    }

    public void mainMenu() {
        try {
            int choice;
            do {
                System.out.println("\n--- Student Result Manager ---");
                System.out.println("1. Add Student");
                System.out.println("2. Show Student Details");
                System.out.println("3. Exit");
                System.out.print("Enter Choice: ");

                choice = sc.nextInt();

                switch (choice) {
                    case 1: addStudent(); break;
                    case 2: showStudentDetails(); break;
                    case 3: System.out.println("Exiting..."); break;
                    default: System.out.println("Invalid Choice!");
                }

            } while (choice != 3);
        }
        finally {
            System.out.println("Thank you for using the Student Result System!");
        }
    }

}

