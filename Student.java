package com.aditya.student;
public class Student {
    private int id; private String name; private String email; private String course; private double cgpa;
    public Student(int id,String name,String email,String course,double cgpa){this.id=id;this.name=name;this.email=email;this.course=course;this.cgpa=cgpa;}
    public int getId(){return id;} public String getName(){return name;} public String getEmail(){return email;}
    public String getCourse(){return course;} public double getCgpa(){return cgpa;}
    @Override public String toString(){return String.format("%-5d %-22s %-30s %-16s %.2f",id,name,email,course,cgpa);}
}
