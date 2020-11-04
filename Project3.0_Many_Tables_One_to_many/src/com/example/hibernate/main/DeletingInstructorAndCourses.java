package com.example.hibernate.main; 

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.example.hibernate.entity.Course;
import com.example.hibernate.entity.Instructor;
import com.example.hibernate.entity.InstructorDetail;

public class DeletingInstructorAndCourses {
	public static void main(String[] args) {
		
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.addAnnotatedClass(Course.class)
								.buildSessionFactory();
		
		// Create entity by using Hibernate's method built-in
		deleteOneInstructorByMethod(factory);
		deleteOneCourseByMethod(factory);

		// close factory
		factory.close();
	}

	private static void deleteOneInstructorByMethod(SessionFactory factory){
		
		Session session = factory.openSession();
		
		try {
			// begin session
			session.beginTransaction();
			
			// session get entity
			int instructorId = 1;
			Instructor instructor = session.get(Instructor.class, instructorId);
			for(Course course: instructor.getCourses()) {
				course.setInstructor(null);
			}
			
			// session delete instructor but not delete course
			session.delete(instructor);
			
			// commit session
			session.getTransaction().commit();
			System.out.println("Delete instructor without course completed!"); 
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			session.close();
		}
	}

	private static void deleteOneCourseByMethod(SessionFactory factory) {

		Session session = factory.openSession();
		
		try {
			// begin session
			session.beginTransaction();
			
			// session get entity
			int courseId = 10;
			Course course = session.get(Course.class, courseId);
			
			// session delete course but not delete instructor
			session.delete(course);
			
			// commit session
			session.getTransaction().commit();
			System.out.println("Delete course without instructor completed!"); 
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			session.close();
		}
	}
}
