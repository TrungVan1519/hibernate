package com.example.hibernate.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.example.hibernate.entity.Course;
import com.example.hibernate.entity.Instructor;
import com.example.hibernate.entity.InstructorDetail;
import com.example.hibernate.entity.Review;
import com.example.hibernate.entity.Student;

public class RetrievingOneCourseAndStudents {
	public static void main(String[] args) {
		
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.addAnnotatedClass(Course.class)
								.addAnnotatedClass(Review.class)
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		// Create student by using Hibernate's method built-in
		retrieveOneCourseAndStudents(factory);
		System.out.println("---");
		retrieveOneStudentAndCourse(factory);

		// close factory
		factory.close();
	}

	private static void retrieveOneCourseAndStudents(SessionFactory factory){
		
		Session session = factory.openSession();
		
		try {
			// begin session
			session.beginTransaction();
			
			// session get entity
			int courseId = 10;
			Course course = session.get(Course.class, courseId);
			
			// commit session
			session.getTransaction().commit();
			
			if(course != null) {
				System.out.println("Retrieve course and student completed!"); 
				System.out.println("List students: " + course.getStudents());
			} else {
				System.out.println("Retrieve course and student fail!");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			session.close();
		}
	}

	private static void retrieveOneStudentAndCourse(SessionFactory factory) {

		Session session = factory.openSession();
		
		try {
			// begin session
			session.beginTransaction();
			
			// session get entity
			int studentId = 1;
			Student student = session.get(Student.class, studentId);
			
			// commit session
			session.getTransaction().commit();
			
			if(student != null) {
				System.out.println("Retrieve course and student completed!"); 
				System.out.println("List courses: " + student.getCourses());
			} else {
				System.out.println("Retrieve course and student fail!");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			session.close();
		}
	}
}
