package com.example.jdbc.dao;

import java.util.List;
import java.util.Map;

import com.example.jdbc.api.Student;

public interface IStudentDao {
	
	public void insert(Student student);
	public boolean deleteByRollNumer(int rollNumber);
	public boolean deleteByNameORAddress(String name, String address);
	public boolean cleanUp(String tableName);
	public void insert(List<Student> sList);
	public List<Student> findAllStudents();
	public Student findStudentByRollNumber(int rollNumber);
	public List<Student> findStudentsByName(String name);
	
	public Map<String, List<String>> groupStudentsByAddress(); 
	
	public int updateStuden(Student s);
	
	public int batchUpdateForStudent(List<Student> sList);

}
