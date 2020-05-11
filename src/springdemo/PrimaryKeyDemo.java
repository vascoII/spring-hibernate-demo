package springdemo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import springdemo.entity.Student;

public class PrimaryKeyDemo {

	public static void main(String[] args) {
		//create session factory
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Student.class)
									.buildSessionFactory();
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			System.out.println("Create 3 Obj");
			Student student1 = new Student("Ricardo", "Tubs", "r.tubs@vice.com");
			Student student2 = new Student("Nuggi", "Nugman", "n.nugman@vice.com");
			Student student3 = new Student("Izzy", "Moreno", "i.moreno@vice.com");
			//start Transaction
			System.out.println("BeginTransaction");
			session.beginTransaction();
			//save obj
			System.out.println("Save Objs");
			session.save(student1);
			session.save(student2);
			session.save(student3);
			//commit transaction
			System.out.println("Commit transaction");
			session.getTransaction().commit();
		} finally {
			factory.close();
		}
	}

}
