package com.example.hibernate.main; 

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.example.hibernate.entity.Course;
import com.example.hibernate.entity.Instructor;
import com.example.hibernate.entity.InstructorDetail;
import com.example.hibernate.entity.Review;

public class DeletingCourseAndReviews {
	public static void main(String[] args) {
		
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.addAnnotatedClass(Course.class)
								.addAnnotatedClass(Review.class)
								.buildSessionFactory();
		
		// Create entity by using Hibernate's method built-in
		deleteOneCourseAndAllAssociatedReviewByMethod(factory);

		// close factory
		factory.close();
	}


	private static void deleteOneCourseAndAllAssociatedReviewByMethod(SessionFactory factory) {

		Session session = factory.openSession();
		
		try {
			// begin session
			session.beginTransaction();
			
			// session get entity
			int courseId = 10;
			Course course = session.get(Course.class, courseId);
			
			// session delete course and associated reviews
			session.delete(course);
			
			// commit session
			session.getTransaction().commit();
			System.out.println("Delete course and associated reviews completed!"); 
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			session.close();
		}
	}
}
