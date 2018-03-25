package com.Thomas.jsf.jpa;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import org.omg.CORBA.SystemException;

@ManagedBean //register the class student as JSF resource
@ApplicationScoped


public class StudentDbUtil {
	
	private static final String PERSISTENCE_UNIT_NAME = "JSFJPA";
	private static EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	private static EntityManager em;
	
	public List<Student> getStudents() {
		em=factory.createEntityManager() ;
		List<Student> students = new ArrayList<Student>();

		try {
			students = em.createQuery( "SELECT s FROM student AS s").getResultList();
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return students;
	}
	
	public static void addStudent(Student student)
	{
		em=factory.createEntityManager() ;
		StudentEntity newStudentEntity = new StudentEntity();
		newStudentEntity.setFirstname(student.getFirstname());
		newStudentEntity.setLastname(student.getLastname());
		newStudentEntity.setEmail(student.getEmail());
		try{
			em.getTransaction().begin();
			em.persist(newStudentEntity);
			em.getTransaction().commit();
		}
		catch (SystemException e) {
			e.printStackTrace();
		}
	}
	
	public static StudentEntity loadStudent(int id)
	{
		em=factory.createEntityManager() ;
		StudentEntity studentEntity = em.find(StudentEntity.class, id);
		return studentEntity;
	}

	public static void updateStudent(Student student)
	{
		em=factory.createEntityManager() ;
		StudentEntity studentEntity = em.find(StudentEntity.class, student.getId());
		try{
			em.getTransaction().begin();
			studentEntity.setFirstname(student.getFirstname());
			studentEntity.setLastname(student.getLastname());
			studentEntity.setEmail(student.getEmail());
			em.getTransaction().commit();
		}
		catch (SystemException e) {
			e.printStackTrace();
		}
	}

	public static void deleteStudent(int id)
	{
		em=factory.createEntityManager() ;
		StudentEntity studentEntity = em.find(StudentEntity.class, id);
		try{
			em.getTransaction().begin();
			em.remove(studentEntity);
			em.getTransaction().commit();
		}
		catch (SystemException e) {
			e.printStackTrace();
		}
	}
}
