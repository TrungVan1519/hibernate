package com.example.hibernate.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.example.hibernate.entity.Student;

public class RetrievingOneStudent {
	public static void main(String[] args) {
		
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Student.class)
									.buildSessionFactory();
		
		// Create student by using Hibernate's method built-in
		retrieveOneStudent(factory);

		// close factory
		factory.close();
	}

	private static void retrieveOneStudent(SessionFactory factory){
		
		Session session = factory.openSession();
		
		try {
			session.beginTransaction();
			
			int studentId = 1;
			Student student = session.get(Student.class, studentId);
			
			session.getTransaction().commit();
			if(student != null) {
				System.out.println("Retrieve student completed!"); 
				System.out.println("Student info :\n" + student);
			} else {
				System.out.println("Retrieve student fail!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			session.close();
		}
	}
}
