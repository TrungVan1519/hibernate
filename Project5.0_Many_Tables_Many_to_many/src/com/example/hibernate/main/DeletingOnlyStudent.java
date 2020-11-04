package com.example.hibernate.main; 

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.example.hibernate.entity.Course;
import com.example.hibernate.entity.Instructor;
import com.example.hibernate.entity.InstructorDetail;
import com.example.hibernate.entity.Review;
import com.example.hibernate.entity.Student;

public class DeletingOnlyStudent {
	public static void main(String[] args) {
		
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.addAnnotatedClass(Course.class)
								.addAnnotatedClass(Review.class)
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		// Create entity by using Hibernate's method built-in
		deleteOnlyCourse(factory);

		// close factory
		factory.close();
	}


	private static void deleteOnlyCourse(SessionFactory factory) {

		Session session = factory.openSession();
		
		try {
			// begin session
			session.beginTransaction();
			
			// session get entity
			int studentId = 1;
			Student student = session.get(Student.class, studentId);
			
			// session delete course and associated reviews
			session.delete(student);
			
			// commit session
			session.getTransaction().commit();
			System.out.println("Delete only student completed!"); 
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			session.close();
		}
	}
}
