package com.example.hibernate.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.example.hibernate.entity.Course;
import com.example.hibernate.entity.Instructor;
import com.example.hibernate.entity.InstructorDetail;

public class CreatingInstructorAndCourses {
	
	public static void main(String[] args) {
		
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.addAnnotatedClass(Course.class)
								.buildSessionFactory();
		
		// Create entity by using Hibernate's method built-in
		createInstructorAndDetail(factory);
		createCoursesAndLinktoInstructor(factory);
		
		// close factory
		factory.close();
	}

	private static void createInstructorAndDetail(SessionFactory factory){
		
		Session session = factory.openSession();
		
		try {
			// begin session
			session.beginTransaction();
			
			// create entity
			InstructorDetail detail = 
					new InstructorDetail("youtube.com", "coding");
			Instructor instructor = 
						new Instructor("dau", "xanh", "dauxanh@gmail.com");
			instructor.setInstructorDetail(detail);
			
			// link course to instructor
			Course pyCourse = new Course("Python");
			instructor.addCourse(pyCourse);

			// session save instructor
			// session auto save associated detail because of CascadeType.ALL
			//		but not auto save course
			session.save(instructor);
			session.save(pyCourse);
			
			// commit session
			session.getTransaction().commit();
			System.out.println("Create instructor, detail and course completed!");
			
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			
		} finally {
			session.close();
		}
	}

	private static void createCoursesAndLinktoInstructor(SessionFactory factory) {
		
		Session session = factory.openSession();
		
		try {
			// begin session
			session.beginTransaction();
			
			// get instructor
			int theId = 1;
			Instructor instructor = session.get(Instructor.class, theId);
			
			// instructor add course by 2 different method
			// method 1: using traditional way
			Course javaCourse = new Course("Java course");
			javaCourse.setInstructor(instructor);
			instructor.getCourses().add(javaCourse);
			// method 2: using "add" convenient method in Instructor class
			Course jsCourse = new Course("Js course");
			instructor.addCourse(jsCourse);
			
			// session save courses
			// courses will be auto linked to instructor
			session.save(javaCourse);
			session.save(jsCourse);
			
			// commit session
			session.getTransaction().commit();
			System.out.println("Create and link course completed!");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

}
