package com.example.jdbc.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.example.jdbc.api.Student;
import com.example.jdbc.dao.IStudentDao;
import com.example.jdbc.dao.StudentDaoImpl;
import com.example.jdbc.service.StudentDaoHelper;

public class Test {

	
	public static void main(String[] args) {
		Student student = new Student();
		student.setRollNumber(5);
		student.setStudentName("Harish");
		student.setStudentAddress("Bangalore");
	
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		StudentDaoImpl studentDAO = context.getBean("studentDao",StudentDaoImpl.class);
		
		
		StudentDaoHelper studentDaoHelper = context.getBean("daoHelper",StudentDaoHelper.class);
//		studentDaoHelper.setUPStudents();
//		studentDAO.insert(student);
		
//		boolean isDeleted = studentDAO.deleteByRollNumer(3);
		
		
//		boolean isDeleted = studentDAO.deleteByNameORAddress("Ashish", null);
//		boolean isDeleted = studentDAO.deleteByNameORAddress(null, "Bangalore");
//		if (isDeleted)
//		{
//			System.out.println("Row is dleted Successful;y....");
//		}
//		else {
//			System.out.println("Failed to delete a row........");
//		}
		
//		IStudentDao studentDao = new StudentDaoImpl();
//		studentDao.insert(student);
		
//		boolean isTableDleted = studentDAO.cleanUp("student");
//		if (isTableDleted)
//		{
//			System.out.println("Table deleted Successful;y....");
//		}
//		else {
//			System.out.println("Failed to delete a row........");
//		}
		List<Student> sList = studentDAO.findAllStudents();
		studentDaoHelper.printAllStudents(sList);
		
		Student studentObj = studentDAO.findStudentByRollNumber(1);
		System.out.println("Student RollNumer : "+studentObj.getRollNumber()+" Name : " + studentObj.getStudentName()+" Address : "+studentObj.getStudentAddress());
		
		List<Student> sListObj = studentDAO.findStudentsByName("Sanju");
		studentDaoHelper.printAllStudents(sListObj);
		
		Map<String, List<String>> sMapList = studentDAO.groupStudentsByAddress();
		
		if (sMapList == null) {
			System.out.println("sMapList is null");
		}
		else {
			for (String sKey : sMapList.keySet()) {
				
				System.out.print("address : "+sKey);
				List<String> sNameList = sMapList.get(sKey);
				for (String sName : sNameList) {
					System.out.print(" \t  " +sName);
				}
				System.out.println();
				System.out.println("*****************");
			}
		}
		
		Student s = new Student();
		s.setRollNumber(1);
		s.setStudentAddress("Pothnal");
//		studentDAO.updateStuden(s);
		
		Student s1 = new Student();
		s1.setRollNumber(1);
		s1.setStudentAddress("Pothnal");
		Student s2 = new Student();
		s2.setRollNumber(2);
		s2.setStudentAddress("Hassan");
		Student s3 = new Student();
		s3.setRollNumber(3);
		s3.setStudentAddress("Dharvad");
		

		List<Student> arrayStudent = new ArrayList<>();

		arrayStudent.add(s1);
		arrayStudent.add(s2);
		arrayStudent.add(s3);
		
		studentDAO.batchUpdateForStudent(arrayStudent);
		
	}

}
