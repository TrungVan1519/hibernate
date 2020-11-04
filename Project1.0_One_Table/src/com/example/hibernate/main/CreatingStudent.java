package com.example.hibernate.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.example.hibernate.entity.Student;

public class CreatingStudent {
	
	public static void main(String[] args) {
		
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Student.class)
									.buildSessionFactory();
		
		// Create student by using Hibernate's method built-in
		createStudent(factory);
		
		// close factory
		factory.close();
	}

	private static void createStudent(SessionFactory factory){
		
		Session session = factory.openSession();
		
		try {
			session.beginTransaction();
			session.save(new Student("dau", "xanh", "dauxanh@gmail.com"));
			session.save(new Student("rau", "muong", "raumuong@gmail.com"));
			session.getTransaction().commit();
			System.out.println("Create student completed!");
			
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			
		} finally {
			session.close();
		}
	}
}
