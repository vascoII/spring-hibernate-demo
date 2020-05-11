package springdemo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import springdemo.entity.Student;

public class CreateStudent {

	public static void main(String[] args) {
		//create session factory
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Student.class)
									.buildSessionFactory();
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			System.out.println("Create Obj");
			Student student = new Student("Sonny", "Crocket", "s.crocket@vice.com");
			//start Transaction
			System.out.println("BeginTransaction");
			session.beginTransaction();
			//save obj
			System.out.println("Save Obj");
			session.save(student);
			//commit transaction
			System.out.println("Commit transaction");
			session.getTransaction().commit();
		} finally {
			factory.close();
		}
	}

}
