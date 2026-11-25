package com.aditya.student;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
public class Main {
    private static final Scanner sc=new Scanner(System.in);
    private static final StudentDAO dao=new StudentDAO();
    public static void main(String[] args) {
        try{dao.createTable();}catch(SQLException e){System.out.println("Database connection failed: "+e.getMessage());return;}
        if(!login())return;
        while(true){
            System.out.println("\n=== STUDENT MANAGEMENT SYSTEM ===");
            System.out.println("1. Add student\n2. View students\n3. Search students\n4. Update student\n5. Delete student\n0. Exit");
            int choice=readInt("Choose: ");
            try{switch(choice){case 1->add();case 2->view();case 3->search();case 4->update();case 5->delete();case 0->{System.out.println("Goodbye.");return;}default->System.out.println("Choose a listed option.");}}
            catch(SQLException e){System.out.println("Database operation failed: "+e.getMessage());}
        }
    }
    private static boolean login(){
        System.out.println("Demo login (change credentials before using beyond a local demo).");
        System.out.print("Username: ");String u=sc.nextLine().trim();
        System.out.print("Password: ");String p=sc.nextLine().trim();
        if(u.equals("admin")&&p.equals("admin123")){System.out.println("Login successful.");return true;}
        System.out.println("Invalid demo credentials.");return false;
    }
    private static void add() throws SQLException {
        int id=readInt("Student ID: ");String name=readText("Name: "),email=readText("Email: "),course=readText("Course: ");double cgpa=readCgpa();
        System.out.println(dao.add(new Student(id,name,email,course,cgpa))?"Student added.":"Student not added.");
    }
    private static void view() throws SQLException {
        System.out.print("Sort by (id/name/cgpa): ");String sort=sc.nextLine().trim().toLowerCase();
        print(dao.all(sort));
    }
    private static void search() throws SQLException {print(dao.search(readText("Search name, email or course: ")));}
    private static void update() throws SQLException {
        int id=readInt("ID to update: ");String name=readText("New name: "),email=readText("New email: "),course=readText("New course: ");double cgpa=readCgpa();
        System.out.println(dao.update(new Student(id,name,email,course,cgpa))?"Student updated.":"No student found with that ID.");
    }
    private static void delete() throws SQLException {int id=readInt("ID to delete: ");System.out.println(dao.delete(id)?"Student deleted.":"No student found with that ID.");}
    private static void print(List<Student> students){
        if(students.isEmpty()){System.out.println("No records found.");return;}
        System.out.printf("%-5s %-22s %-30s %-16s %s%n","ID","NAME","EMAIL","COURSE","CGPA");
        students.forEach(System.out::println);
    }
    private static String readText(String label){while(true){System.out.print(label);String s=sc.nextLine().trim();if(!s.isBlank())return s;System.out.println("This field cannot be empty.");}}
    private static int readInt(String label){while(true){System.out.print(label);try{int n=Integer.parseInt(sc.nextLine().trim());if(n>0)return n;}catch(NumberFormatException ignored){}System.out.println("Enter a positive whole number.");}}
    private static double readCgpa(){while(true){System.out.print("CGPA (0.00-10.00): ");try{double n=Double.parseDouble(sc.nextLine().trim());if(n>=0&&n<=10)return n;}catch(NumberFormatException ignored){}System.out.println("Enter a number from 0 to 10.");}}
}
