package com.example.jdbc.service;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;

import com.example.jdbc.api.Student;

public class StudentRowMapper implements RowMapper<Student> {

	@Override
	public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
			
		
		System.out.println(" <--- map row is called ---> ");
		Student student = new Student();
		student.setRollNumber(rs.getInt("RollNumber"));
		student.setStudentAddress(rs.getString("studentAddress"));
		student.setStudentName(rs.getString("studentName"));
		return student;
	}
}
