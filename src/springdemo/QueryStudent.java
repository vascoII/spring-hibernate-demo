package springdemo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import springdemo.entity.Student;

public class QueryStudent {

	public static void main(String[] args) {
		//create session factory
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Student.class)
									.buildSessionFactory();
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			//start Transaction
			System.out.println("BeginTransaction");
			session.beginTransaction();
			
			//List
			List<Student> allStudents = session
					.createQuery("from Student")
					.getResultList();
			System.out.println("allStudents: ");
			displayStudents(allStudents);
			
			//List with where
			List<Student> whereStudents = session
					.createQuery("from Student s where s.lastName LIKE '%o%'")
					.getResultList();
			System.out.println("whereStudents: ");
			displayStudents(whereStudents);

			//List with where, or
			List<Student> whereOrStudents = session
					.createQuery("from Student s where s.lastName LIKE '%o%'"
							+ "OR s.firstName LIKE '%o%'")
					.getResultList();
			System.out.println("whereOrStudents: ");
			displayStudents(whereOrStudents);
			
			//commit transaction
			session.getTransaction().commit();
		} finally {
			factory.close();
		}
	}

	private static void displayStudents(List<Student> students) {
		for (Student tempStudent : students) {
			System.out.println(tempStudent);
		}
	}

}
