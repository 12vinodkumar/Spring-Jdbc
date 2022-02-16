package com.example.jdbc.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.jdbc.api.Student;
import com.example.jdbc.dao.StudentDaoImpl;

@Service("daoHelper")
public class StudentDaoHelper {

	@Autowired
	private StudentDaoImpl studentDaoImpl;

	public void setUPStudents() {
		Student s1 = new Student();
		s1.setRollNumber(1);
		s1.setStudentName("Vinod");
		s1.setStudentAddress("Manvi");
		Student s2 = new Student();
		s2.setRollNumber(2);
		s2.setStudentName("Sanju");
		s2.setStudentAddress("ArsiKere");
		Student s3 = new Student();
		s3.setRollNumber(3);
		s3.setStudentName("Harish");
		s3.setStudentAddress("Bangalore");
		Student s4 = new Student();
		s4.setRollNumber(4);
		s4.setStudentName("Mahi");
		s4.setStudentAddress("ChikkaBanavara");

		ArrayList<Student> arrayStudent = new ArrayList<>();

		arrayStudent.add(s1);
		arrayStudent.add(s2);
		arrayStudent.add(s3);
		arrayStudent.add(s4);
		
		studentDaoImpl.insert(arrayStudent);
		
	}
	
	public void printAllStudents(List<Student> sList) {
		for (Student s : sList) {
			System.out.println("Student RollNumer : "+s.getRollNumber()+" Student Name : " + s.getStudentName()+" Student Address : "+s.getStudentAddress());
		}
	}
}
