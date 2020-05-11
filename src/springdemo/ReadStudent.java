package springdemo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import springdemo.entity.Student;

public class ReadStudent {

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
			Student student = new Student("Martin", "Castillo", "m.castillo@vice.com");
			//start Transaction
			System.out.println("BeginTransaction");
			session.beginTransaction();
			//save obj
			System.out.println("Save Obj");
			System.out.println(student);
			session.save(student);
			//commit transaction
			System.out.println("Commit transaction");
			session.getTransaction().commit();
			
			//student generated id
			System.out.println("student id: " + student.getId());
			
			//get new session and start transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			//read student data based on id
			Student castillo = session.get(Student.class, student.getId());
			System.out.println("castillo student: " + castillo);
			//commit transaction
			session.getTransaction().commit();
		} finally {
			factory.close();
		}
	}

}
