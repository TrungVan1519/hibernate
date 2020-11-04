package com.example.eager.entity;   

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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "instructor")
public class Instructor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "email")
	private String email;
	
	// 1 Instructor chi co duy nhat 1 InstructorDetail nen su dung @OneToOne
	@OneToOne(cascade = CascadeType.ALL)
	// Instructor trong DB co FOREIGN KEY toi InstructorDetail nen phai 
	//		su dung @JoinColumn
	@JoinColumn(name = "instructor_detail_id")
	private InstructorDetail instructorDetail;
	
	// 1 Instructor co the co nhieu Course nen su dung @ManyToOne
	// Course co FOREIGN KEY toi Instructor nen su dung "mappedBy"
	@OneToMany( fetch = FetchType.EAGER,
				mappedBy = "instructor", 
				cascade = { CascadeType.PERSIST, CascadeType.MERGE,
							CascadeType.DETACH, CascadeType.REFRESH })
	private List<Course> courses;
	
	public Instructor() {
	}

	public Instructor(String firstName, String lastName, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		
		this.courses = new ArrayList<Course>();
	}

	// getter, setter ...
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public InstructorDetail getInstructorDetail() {
		return instructorDetail;
	}

	public void setInstructorDetail(InstructorDetail instructorDetail) {
		this.instructorDetail = instructorDetail;
	}
	
	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	@Override
	public String toString() {
		return "Instructor [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ "]";
	}
	
	public boolean addCourse(Course course) {
		if(this.courses == null) {
			this.courses = new ArrayList<Course>();
		}
		this.courses.add(course);
		course.setInstructor(this);
		
		return true;
	}
}
