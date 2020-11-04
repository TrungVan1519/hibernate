package com.example.hibernate.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.example.hibernate.entity.Student;

public class UpdatingListStudent {
	public static void main(String[] args) {
		
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Student.class)
									.buildSessionFactory();
		
		// Create student by using Hibernate's method built-in
		updateAllStudent(factory);
		System.out.println("\n-------");
		updateListStudent(factory);

		// close factory
		factory.close();
	}

	private static void updateAllStudent(SessionFactory factory) {

		Session session = factory.openSession();
		
		try {
			session.beginTransaction();
			
			session
				.createQuery("UPDATE Student SET email='all@gmail.com'")
				.executeUpdate();
			
			session.getTransaction().commit();
			System.out.println("Update all student completed!"); 
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			session.close();
		}
	}

	private static void updateListStudent(SessionFactory factory){
		
		Session session = factory.openSession();
		
		try {
			session.beginTransaction();
			
			int startPos = 2;
			int endPos = 3;
			session
				.createQuery("UPDATE Student SET email='list@gmail.com' "
						+ "WHERE id BETWEEN " + startPos + " AND " + endPos)
				.executeUpdate();
			
			session.getTransaction().commit();
			System.out.println("Update list student completed!"); 
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			session.close();
		}
	}
}
