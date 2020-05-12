package springdemo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import springdemo.entity.Student;

public class UpdateStudent {

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
			System.out.println("Update Obj");
			int studentId = 3;
			
			//start Transaction
			System.out.println("BeginTransaction");
			session.beginTransaction();
			//get obj
			Student student = session.get(Student.class,  studentId);
			System.out.println(student);
			//update student
			System.out.println("Update");
			student.setFirstName("Nuggi");
			//commit transaction
			System.out.println("Commit transaction");
			session.getTransaction().commit();
			
			//get new session and start transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			//get updated obj
			student = session.get(Student.class,  studentId);
			System.out.println(student);
			//commit transaction
			System.out.println("Commit transaction");
			session.getTransaction().commit();
			
			//UPDATE MANY ENTITIES
			//get new session and start transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			session.createQuery("update Student set email='secret@vice.com'")
				.executeUpdate();
			student = session.get(Student.class,  studentId);
			System.out.println(student);
			//commit transaction
			System.out.println("Commit transaction");
			session.getTransaction().commit();
		} finally {
			factory.close();
		}
	}

}
