package com.example.hibernate.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "course")
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "title")
	private String title;
	
	// 1 Instructor co the co nhieu Course nen phai su dung @ManyToOne
	// Khong su dung CascadeType.REMOVE
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
							CascadeType.DETACH, CascadeType.REFRESH })
	// Course trong DB co FOREIGN KEY toi Instructor nen phai su dung 
	//		@JoinColumn
	@JoinColumn(name = "instructor_id")
	private Instructor instructor;
	
	@OneToMany( fetch = FetchType.LAZY,
				cascade = CascadeType.ALL)
	// Do day la co che @OneToMany-UniDirection nen ta phai them @JoinColumn
	//		o day (Entity dich) thay vi them @JoinColumn o Entity nguon
	//		nhu co che @OneToMany-BiDirection
	@JoinColumn(name = "course_id")
	private List<Review> reviews;
	
	
	@ManyToMany(fetch = FetchType.LAZY,
				cascade = { CascadeType.PERSIST, CascadeType.MERGE,
							CascadeType.DETACH, CascadeType.REFRESH })
	@JoinTable( name = "course_student",
				joinColumns = {@JoinColumn(name="course_id")},
				inverseJoinColumns = {@JoinColumn(name="student_id")})
	private List<Student> students;

	public Course() {
	}

	public Course(String title) {
		this.title = title;
	}
	
	// getter, setter ...

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Instructor getInstructor() {
		return instructor;
	}

	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}
	
	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", title=" + title + "]";
	}
	
	// convenient method
	
	public void addReview(Review review) {
		if(this.reviews == null) {
			this.reviews = new ArrayList<Review>();
		}
		
		this.reviews.add(review);
	}
	
	public void addStudent(Student student) {
		if(this.students == null) {
			this.students = new ArrayList<Student>();
		}
		
		this.students.add(student);
	}
}
