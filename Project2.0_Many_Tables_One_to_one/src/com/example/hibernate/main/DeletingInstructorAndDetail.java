package com.example.hibernate.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.example.hibernate.entity.Instructor;
import com.example.hibernate.entity.InstructorDetail;

public class DeletingInstructorAndDetail {
	public static void main(String[] args) {
		
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.buildSessionFactory();
		
		// Create entity by using Hibernate's method built-in
		deleteOneInstructorAndDetailByMethod(factory);

		// close factory
		factory.close();
	}

	private static void deleteOneInstructorAndDetailByMethod(SessionFactory factory){
		
		Session session = factory.openSession();
		
		try {
			// begin session
			session.beginTransaction();
			
			// session get entity
			// method 1: delete instructor and detail by deleting instructor
			int theId = 1;
			Instructor instructor = session.get(Instructor.class, theId);
			
			// method 2: delete instructor and detail by deleting detail
			int theId2 = 2;
			InstructorDetail detail = session.get(InstructorDetail.class, theId2);
			
			// session delete instructor/detail
			// session auto delete associated detail/associated instructor
			//		because of CascadeType.ALL
			session.delete(instructor);
			session.delete(detail);
			
			// commit session
			session.getTransaction().commit();
			System.out.println("Delete instructor and detail completed!"); 
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			session.close();
		}
	}
}
