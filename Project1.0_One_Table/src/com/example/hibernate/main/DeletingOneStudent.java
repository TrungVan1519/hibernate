package com.example.hibernate.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.example.hibernate.entity.Student;

public class DeletingOneStudent {
	public static void main(String[] args) {
		
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Student.class)
									.buildSessionFactory();
		
		// Create student by using Hibernate's method built-in
		deleteOneStudentByMethod(factory);
		System.out.println("\n-------");
		deleteOneStudentByHQL(factory);

		// close factory
		factory.close();
	}

	private static void deleteOneStudentByMethod(SessionFactory factory){
		
		Session session = factory.openSession();
		
		try {
			session.beginTransaction();
			
			// get student
			int studentId = 1;
			Student student = session.get(Student.class, studentId);
			
			// delete student
			session.delete(student);
			
			session.getTransaction().commit();
			System.out.println("Delete student completed!"); 
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			session.close();
		}
	}

	private static void deleteOneStudentByHQL(SessionFactory factory) {

		Session session = factory.openSession();
		
		try {
			session.beginTransaction();
			
			// delete student
			int studentId = 2;
			session
				.createQuery("DELETE FROM Student WHERE id = " + studentId)
				.executeUpdate();
			
			session.getTransaction().commit();
			System.out.println("Update student completed!"); 
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			session.close();
		}
	}
}
