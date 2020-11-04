package com.example.hibernate.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.example.hibernate.entity.Instructor;
import com.example.hibernate.entity.InstructorDetail;

public class CreatingInstructorAndDetail {
	
	public static void main(String[] args) {
		
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.buildSessionFactory();
		
		// Create entity by using Hibernate's method built-in
		createInstructorAndDetail(factory);
		
		// close factory
		factory.close();
	}

	private static void createInstructorAndDetail(SessionFactory factory){
		
		Session session = factory.openSession();
		
		try {
			// begin session
			session.beginTransaction();
			
			// create entity
			InstructorDetail detail = 
						new InstructorDetail("youtube1.com", "coding");
			Instructor instructor = 
						new Instructor("dau", "xanh", "dauxanh@gmail.com");
			instructor.setInstructorDetail(detail);

			// session save instructor
			// session auto save associated detail because of CascadeType.ALL
//			session.save(detail); co hay khong co dong nay cung duoc
			session.save(instructor);
			
			// commit session
			session.getTransaction().commit();
			System.out.println("Create instructor and detail completed!");
			
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			
		} finally {
			session.close();
		}
	}
}
