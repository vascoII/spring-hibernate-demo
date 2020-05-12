package springdemo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import springdemo.entity.Student;

public class DeleteStudent {

	public static void main(String[] args) {
		//create session factory
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Student.class)
									.buildSessionFactory();
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			//UPDATE ONE ENTITY
			System.out.println("Delete Obj");
			int studentId3 = 3;
			int studentId4 = 4;
			
			//start Transaction
			System.out.println("BeginTransaction");
			session.beginTransaction();
			//get obj
			Student student = session.get(Student.class,  studentId3);
			System.out.println(student);
			//delete student
			session.delete(student);
			//commit transaction
			System.out.println("Commit transaction");
			session.getTransaction().commit();
			
			//get new session and start transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			//get deleted obj
			student = session.get(Student.class,  studentId3);
			System.out.println(student);
			//commit transaction
			System.out.println("Commit transaction");
			session.getTransaction().commit();
			
			//DELETE MANY ENTITIES
			//get new session and start transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			//get obj
			student = session.get(Student.class,  studentId4);
			System.out.println(student);
			//delete query
			session.createQuery("delete Student where firstName='Izzy'")
				.executeUpdate();
			student = session.get(Student.class,  studentId4);
			System.out.println(student);
			//commit transaction
			System.out.println("Commit transaction");
			session.getTransaction().commit();
		} finally {
			factory.close();
		}
	}

}
