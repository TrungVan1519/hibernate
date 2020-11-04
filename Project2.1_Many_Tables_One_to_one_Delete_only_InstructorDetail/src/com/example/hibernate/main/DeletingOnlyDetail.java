package com.example.hibernate.main; 

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.example.hibernate.entity.Instructor;
import com.example.hibernate.entity.InstructorDetail;

public class DeletingOnlyDetail {
	public static void main(String[] args) {
		
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.buildSessionFactory();
		
		// Create entity by using Hibernate's method built-in
		deleteOnlyDetailByMethod(factory);

		// close factory
		factory.close();
	}

	private static void deleteOnlyDetailByMethod(SessionFactory factory){
		
		Session session = factory.openSession();
		
		try {
			// begin session
			session.beginTransaction();
			
			// session get detail and break link from detail to instructor
			int theId = 1;
			InstructorDetail detail = session.get(InstructorDetail.class, theId);
			detail.getInstructor().setInstructorDetail(null);
			
			// session delete only detail and keep instructor
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
