package com.example.hibernate.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.example.hibernate.entity.Student;

public class UpdatingOneStudent {
	public static void main(String[] args) {
		
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Student.class)
									.buildSessionFactory();
		
		// Create student by using Hibernate's method built-in
		updateOneStudent(factory);

		// close factory
		factory.close();
	}

	private static void updateOneStudent(SessionFactory factory){
		
		Session session = factory.openSession();
		
		try {
			session.beginTransaction();
			
			// get student
			int studentId = 1;
			Student student = session.get(Student.class, studentId);
			
			// update student
			student.setFirstName("khoai");
			student.setLastName("san");
			student.setEmail("khoaisan@gmail.com");
			
			session.getTransaction().commit();
			System.out.println("Update student completed!"); 
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			session.close();
		}
	}
}
