package com.example.hibernate.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.example.hibernate.entity.Course;
import com.example.hibernate.entity.Instructor;
import com.example.hibernate.entity.InstructorDetail;
import com.example.hibernate.entity.Review;

public class RetrievingOneCourseAndReviews {
	public static void main(String[] args) {
		
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.addAnnotatedClass(Course.class)
								.addAnnotatedClass(Review.class)
								.buildSessionFactory();
		
		// Create student by using Hibernate's method built-in
		retrieveOneCourseAndReviews(factory);

		// close factory
		factory.close();
	}

	private static void retrieveOneCourseAndReviews(SessionFactory factory){
		
		Session session = factory.openSession();
		
		try {
			// begin session
			session.beginTransaction();
			
			// session get entity
			int theId = 10;
			Course course = session.get(Course.class, theId);
			
			// commit session
			session.getTransaction().commit();
			
			if(course != null) {
				System.out.println("Retrieve course and reviews completed!"); 
				System.out.println("List courses: " + course.getReviews());
			} else {
				System.out.println("Retrieve course and reviews fail!");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			session.close();
		}
	}
}
