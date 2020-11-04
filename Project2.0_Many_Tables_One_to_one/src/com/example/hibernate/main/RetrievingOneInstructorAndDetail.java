package com.example.hibernate.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.example.hibernate.entity.Instructor;
import com.example.hibernate.entity.InstructorDetail;

public class RetrievingOneInstructorAndDetail {
	public static void main(String[] args) {
		
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.buildSessionFactory();
		
		// Create student by using Hibernate's method built-in
		retrieveOneInstructorAndDetail(factory);

		// close factory
		factory.close();
	}

	private static void retrieveOneInstructorAndDetail(SessionFactory factory){
		
		Session session = factory.openSession();
		
		try {
			// begin session
			session.beginTransaction();
			
			// session get entity
			int theId = 1;
			// method 1: get instructor and detail by getting instructor
			Instructor instructor = session.get(Instructor.class, theId);
			// method 2: get instructor and detail by getting detail
			InstructorDetail detail = session.get(InstructorDetail.class, theId);
			
			// commit session
			session.getTransaction().commit();
			if(instructor != null && detail != null) {
				System.out.println("Retrieve instructor and detail completed!"); 
				System.out.println("Instructor info :\n" + instructor + "| " + instructor.getInstructorDetail());
				System.out.println("---");
				System.out.println("Detail:\n" + detail + "| " + detail.getInstructor());
			} else {
				System.out.println("Retrieve instructor and detail fail!");
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			session.close();
		}
	}
}
