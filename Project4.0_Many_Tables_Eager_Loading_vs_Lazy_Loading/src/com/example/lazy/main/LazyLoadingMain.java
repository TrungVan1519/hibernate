package com.example.lazy.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.example.eager.entity.Course;
import com.example.eager.entity.Instructor;
import com.example.eager.entity.InstructorDetail;

public class LazyLoadingMain {
	public static void main(String[] args) {
		
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.addAnnotatedClass(Course.class)
								.buildSessionFactory();
		
		// Create student by using Hibernate's method built-in
		retrieveOneInstructorAndCourses(factory);

		// close factory
		factory.close();
	}

	private static void retrieveOneInstructorAndCourses(SessionFactory factory){
		
		Session session = factory.openSession();
		
		try {
			// begin session
			session.beginTransaction();
			
			// session get entity
			int theId = 1;
			Instructor instructor = session.get(Instructor.class, theId);
			
			// commit session
			session.getTransaction().commit();
			
			session.close();
			System.out.println(instructor.getCourses());
//			if(instructor != null) {
//				System.out.println("Retrieve instructor and course completed!"); 
//				System.out.println("Instructor info :\n" + instructor + 
//						"| " + instructor.getInstructorDetail());
//				System.out.println("List courses:" + instructor.getCourses());
//			} else {
//				System.out.println("Retrieve instructor and course fail!");
//			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			session.close();
		}
	}
}
