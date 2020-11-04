package com.example.hibernate.main;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.example.hibernate.entity.Student;

public class RetrievingListStudent {
	public static void main(String[] args) {
		
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Student.class)
									.buildSessionFactory();
		
		// Create student by using Hibernate's method built-in
		retrieveAllStudent(factory);
		System.out.println("\n-------");
		retrieveListStudent(factory);
		
		// close factory
		factory.close();
	}

	private static void retrieveAllStudent(SessionFactory factory){
		
		Session session = factory.openSession();
		
		try {
			session.beginTransaction();
			
			List<Student> students = session
									.createQuery("FROM Student")
									.list();
			// > Student la ten class Java Object khong phai ten bang trong DB
			
			session.getTransaction().commit();
			displayList(students);
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			session.close();
		}
	}
	
	private static void retrieveListStudent(SessionFactory factory){
		
		Session session = factory.openSession();
		
		try {
			session.beginTransaction();
			
			List<Student> students = session
									.createQuery("FROM Student sTable "
											+ "WHERE sTable.lastName LIKE 'xanh' "
											+ "OR sTable.firstName LIKE 'rau'")
									.list();
			// > Student la ten class Java Object khong phai ten bang trong DB
			// > lastName la ten prop lastName trong class Java Object khong phai ten
			//		cot trong DB
			
			session.getTransaction().commit();
			displayList(students);
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			session.close();
		}
	}
	
	private static void displayList(List<Student> students) {
		if(students != null) {
			System.out.println("Retrieve all/list student completed!");
			for(Student student: students) {
				System.out.println(student);
			}
			
		} else {
			System.out.println("Retrieve list student fail");
		}
	}
}
