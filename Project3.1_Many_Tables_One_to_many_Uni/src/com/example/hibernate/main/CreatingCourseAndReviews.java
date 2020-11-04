package com.example.hibernate.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.example.hibernate.entity.Course;
import com.example.hibernate.entity.Instructor;
import com.example.hibernate.entity.InstructorDetail;
import com.example.hibernate.entity.Review;

public class CreatingCourseAndReviews {
	
	public static void main(String[] args) {
		
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.addAnnotatedClass(Course.class)
								.addAnnotatedClass(Review.class)
								.buildSessionFactory();
		
		// Create entity by using Hibernate's method built-in
		createCourseAndReview(factory);
		createReviewAndLinkToCourse(factory);
		
		// close factory
		factory.close();
	}

	private static void createCourseAndReview(SessionFactory factory){
		
		Session session = factory.openSession();
		
		try {
			// begin session
			session.beginTransaction();
			
			// create entity
			Course pyCourse = new Course("Python");
			
			// link review to course
			Review review = new Review("This course about Python");
			pyCourse.addReview(review);

			// session save instructor
			// session auto save associated detail because of CascadeType.ALL
			//		but not auto save course
//			session.save(review); 
			// > co hay khong co dong nay cung duoc vi ta su dung 
			// 		 CascadeType.ALL cho List<Review> trong class Course roi
			session.save(pyCourse);
			
			// commit session
			session.getTransaction().commit();
			System.out.println("Create course and reviews completed!");
			
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			
		} finally {
			session.close();
		}
	}

	private static void createReviewAndLinkToCourse(SessionFactory factory) {
		
		Session session = factory.openSession();
		
		try {
			// begin session
			session.beginTransaction();
			
			// get course
			int theId = 10;
			Course pyCourse = session.get(Course.class, theId);
			
			// course add review by using "add" convenient method in 
			//		Instructor class
			Review first = new Review("This course is so good");
			pyCourse.addReview(first);
			Review second = new Review("This course sucks");
			pyCourse.addReview(second);
			
			System.out.println("==> " + pyCourse.getReviews());
			
			// session save reviews
			// reviews will be auto linked to instructor
			session.save(first);
			session.save(second);
			
			System.out.println("==> " + pyCourse.getReviews());
			
			// commit session
			session.getTransaction().commit();
			System.out.println("Create and link review to course completed!");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

}
