package com.example.jdbc.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowCallbackHandler;

import com.example.jdbc.api.Student;

public class StudentResultSetExtractor implements ResultSetExtractor<List<Student>> {

	@Override
	public List<Student> extractData(ResultSet rs) throws SQLException, DataAccessException {
		
		List<Student> sList =  new ArrayList<>();
		
		while(rs.next()) {
			Student student = new Student();
			student.setRollNumber(rs.getInt("RollNumber"));
			student.setStudentName(rs.getString("studentName"));
			student.setStudentAddress(rs.getString("studentAddress"));
			sList.add(student);
		}
		return sList;
	}



}
