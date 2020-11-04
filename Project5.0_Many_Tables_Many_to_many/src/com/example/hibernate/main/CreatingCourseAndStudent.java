package com.example.hibernate.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.example.hibernate.entity.Course;
import com.example.hibernate.entity.Instructor;
import com.example.hibernate.entity.InstructorDetail;
import com.example.hibernate.entity.Review;
import com.example.hibernate.entity.Student;

public class CreatingCourseAndStudent {
	
	public static void main(String[] args) {
		
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.addAnnotatedClass(Course.class)
								.addAnnotatedClass(Review.class)
								.addAnnotatedClass(Student.class)
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		// Create entity by using Hibernate's method built-in
		createCourseAndStudent(factory);
		createReviewAndLinkToCourse(factory);
		
		// close factory
		factory.close();
	}

	private static void createCourseAndStudent(SessionFactory factory){
		
		Session session = factory.openSession();
		
		try {
			// begin session
			session.beginTransaction();
			
			// session save course
			Course pyCourse = new Course("Python");
			session.save(pyCourse);
			
			// session save student
			Student student1 = new Student("dau", "xanh", "dauxanh@gmail.com");
			Student student2 = new Student("rau", "muong", "raumuong@gmail.com");
			session.save(student1);
			session.save(student2);
			
			// add student to the course
			pyCourse.addStudent(student1);
			pyCourse.addStudent(student2);
			
			// commit session
			session.getTransaction().commit();
			System.out.println("Create course and students completed!");
			
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
			int studentId = 1;
			Student student = session.get(Student.class, studentId);
			
			// course add review by using "add" convenient method in 
			//		Course class
			Course javaCourse = new Course("Java Course");
			Course jsCourse = new Course("Js Course");
			javaCourse.addStudent(student);
			jsCourse.addStudent(student);
			
			// session save course
			session.save(javaCourse);
			session.save(jsCourse);
			
			// commit session
			session.getTransaction().commit();
			System.out.println("Create and link student to course completed!");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

}
