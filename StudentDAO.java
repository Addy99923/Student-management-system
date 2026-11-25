package com.aditya.student;
import java.sql.*;
import java.util.*;
public class StudentDAO {
    public void createTable() throws SQLException {
        String sql="CREATE TABLE IF NOT EXISTS students (id INT PRIMARY KEY, name VARCHAR(100) NOT NULL, email VARCHAR(150) NOT NULL UNIQUE, course VARCHAR(100) NOT NULL, cgpa DECIMAL(3,2) NOT NULL)";
        try(Connection c=DBConnection.getConnection();Statement s=c.createStatement()){s.execute(sql);}
    }
    public boolean add(Student st) throws SQLException {
        String sql="INSERT INTO students(id,name,email,course,cgpa) VALUES(?,?,?,?,?)";
        try(Connection c=DBConnection.getConnection();PreparedStatement p=c.prepareStatement(sql)){
            p.setInt(1,st.getId());p.setString(2,st.getName());p.setString(3,st.getEmail());p.setString(4,st.getCourse());p.setDouble(5,st.getCgpa());return p.executeUpdate()==1;
        }
    }
    public List<Student> all(String sort) throws SQLException {
        String order=switch(sort){case "name"->"name";case "cgpa"->"cgpa DESC";default->"id";};
        List<Student> list=new ArrayList<>();String sql="SELECT * FROM students ORDER BY "+order;
        try(Connection c=DBConnection.getConnection();PreparedStatement p=c.prepareStatement(sql);ResultSet r=p.executeQuery()){while(r.next())list.add(map(r));}return list;
    }
    public List<Student> search(String term) throws SQLException {
        List<Student> list=new ArrayList<>();String sql="SELECT * FROM students WHERE name LIKE ? OR email LIKE ? OR course LIKE ? ORDER BY id";
        try(Connection c=DBConnection.getConnection();PreparedStatement p=c.prepareStatement(sql)){String q="%"+term+"%";p.setString(1,q);p.setString(2,q);p.setString(3,q);try(ResultSet r=p.executeQuery()){while(r.next())list.add(map(r));}}return list;
    }
    public boolean update(Student st) throws SQLException {
        String sql="UPDATE students SET name=?,email=?,course=?,cgpa=? WHERE id=?";
        try(Connection c=DBConnection.getConnection();PreparedStatement p=c.prepareStatement(sql)){p.setString(1,st.getName());p.setString(2,st.getEmail());p.setString(3,st.getCourse());p.setDouble(4,st.getCgpa());p.setInt(5,st.getId());return p.executeUpdate()==1;}
    }
    public boolean delete(int id) throws SQLException {
        try(Connection c=DBConnection.getConnection();PreparedStatement p=c.prepareStatement("DELETE FROM students WHERE id=?")){p.setInt(1,id);return p.executeUpdate()==1;}
    }
    private Student map(ResultSet r) throws SQLException{return new Student(r.getInt("id"),r.getString("name"),r.getString("email"),r.getString("course"),r.getDouble("cgpa"));}
}
