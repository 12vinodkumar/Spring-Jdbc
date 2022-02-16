package com.example.jdbc.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;

import com.example.jdbc.api.Student;
import com.example.jdbc.service.StudentDaoHelper;
import com.example.jdbc.service.StudentRSforGroup;
import com.example.jdbc.service.StudentResultSetExtractor;
import com.example.jdbc.service.StudentRowMapper;


@Repository("studentDao")
public class StudentDaoImpl implements IStudentDao {
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void insert(Student student) {
		// TODO Auto-generated method stub
		String sql = "insert into student values(?,?,?)";
	
		Object[] arg = {student.getRollNumber(), student.getStudentName(), student.getStudentAddress()};
		int iRowsAreEffected = jdbcTemplate.update(sql,arg);
		System.out.println("the number of rows Effected "+iRowsAreEffected);
	}

	private DataSource getDataSource() {
		
		String url = "jdbc:mysql://127.0.0.1:3306/school?useSSL=false";
		String userName = "vinod";
		String password = "vinod123";
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl(url);
        dataSource.setUsername(userName);
        dataSource.setPassword(password);
		
		return dataSource;
	}

	@Override
	public boolean deleteByRollNumer(int rollNumber) {
		String sql = "DELETE FROM student WHERE RollNumber = ?";
		
		int deletedRow = jdbcTemplate.update(sql, rollNumber);
		
		if (deletedRow == 1) {
			return true;
		}
		else {
			return false;
		}
		
	}

	@Override
	public boolean deleteByNameORAddress(String name, String address) {
		String sql = "DELETE FROM student WHERE studentName = ? OR studentAddress = ?";
		
		Object[] arguemnets = {name, address};
		
		int deletedRow = jdbcTemplate.update(sql, arguemnets);
		
		if (deletedRow == 1) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean cleanUp(String tableName) {
		String sql = "TRUNCATE TABLE student";
		int isTableDeleted = jdbcTemplate.update(sql );		
		if (isTableDeleted == 1) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public void insert(List<Student> sList) {
		
		String sql = "insert into student values(?,?,?)";
		ArrayList<Object[]> studentList = new ArrayList<>();
		for (Student s : sList) {
			System.out.println("student "+s.getRollNumber());
			Object[] sAraay = {s.getRollNumber(), s.getStudentName(), s.getStudentAddress()};
			studentList.add(sAraay);
		}
		
		jdbcTemplate.batchUpdate(sql, studentList);
		System.out.println("batchUpdate is completed ");
		
	}

	@Override
	public List<Student> findAllStudents() {
		
		String query = "SELECT * FROM student";
		
		List<Student> studentList =  jdbcTemplate.query(query, new StudentRowMapper());
		
		return studentList;
	}

	@Override
	public Student findStudentByRollNumber(int rollNumber) {
		
		String query = "SELECT RollNumber as rollNumber, studentName, studentAddress FROM student WHERE RollNumber = ?";
		
		Student  student = jdbcTemplate.queryForObject(query, new BeanPropertyRowMapper<Student>(Student.class), rollNumber);
		return student;
	}

	@Override
	public List<Student> findStudentsByName(String name) {
			String query = "SELECT * FROM student WHERE studentName = ?";
		List<Student> sList	= jdbcTemplate.query(query, new StudentResultSetExtractor(), name);
		return sList;
	}

	@Override
	public Map<String, List<String>> groupStudentsByAddress() {
		String query = "SELECT * FROM student";
		Map<String, List<String>> sMap = jdbcTemplate.query(query, new StudentRSforGroup());
		return sMap;
	}

	@Override
	public int updateStuden(Student s) {
		String query = "UPDATE school.student set studentAddress = ? WHERE RollNumber = ?";
		
		Object[] args = {s.getStudentAddress(),s.getRollNumber()};
		
		int iValue = jdbcTemplate.update(query, args);
		System.out.println("updated value is " +iValue);
		return iValue;
	}

	@Override
	public int batchUpdateForStudent(List<Student> sList) {
		String query = "UPDATE school.student set studentAddress = ? WHERE RollNumber = ?";
		int updatedRowCount = 0;
		
		int[] updatedrows = jdbcTemplate.batchUpdate(query, new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setString(1, sList.get(i).getStudentAddress());
				ps.setInt(2, sList.get(i).getRollNumber());
				
			}
			
			@Override
			public int getBatchSize() {
				// TODO Auto-generated method stub
				return sList.size();
			}
		});
		
		for (int i = 0; i < updatedrows.length ; i++) {
			if (updatedrows[i] == 1)
			{
				updatedRowCount++;
			}
		}
		return updatedRowCount;
	}
}
