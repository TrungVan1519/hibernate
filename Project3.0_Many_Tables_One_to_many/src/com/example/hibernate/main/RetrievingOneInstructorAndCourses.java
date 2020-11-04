package com.example.hibernate.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.example.hibernate.entity.Course;
import com.example.hibernate.entity.Instructor;
import com.example.hibernate.entity.InstructorDetail;

public class RetrievingOneInstructorAndCourses {
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
			// method 1: get instructor and courses by getting instructor
			Instructor instructor = session.get(Instructor.class, theId);
			// method 2: get instructor and course by getting course
			Course course = session.get(Course.class, theId + 10);
			
			// commit session
			session.getTransaction().commit();
			if(instructor != null && course != null) {
				System.out.println("Retrieve instructor and course completed!"); 
				System.out.println("Instructor info :\n" + instructor + 
						"| " + instructor.getInstructorDetail());
				System.out.println("List courses:");
				for(Course item: instructor.getCourses()) {
					System.out.println(item);
				}
				System.out.println("---");
				System.out.println("Course info:\n" + course + "| " + course.getInstructor());
			} else {
				System.out.println("Retrieve instructor and course fail!");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			session.close();
		}
	}
}
