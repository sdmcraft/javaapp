package ejb.session.stateless;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ejb.persistance.entities.Employee;


@Stateless
public class SessionBean implements SessionBeanLocal {

	@PersistenceContext(name="EnterpriseApp")
	EntityManager em;

	public void method() {
		System.out.println("Hello from session bean");

	}

	public void createEmployee() {				
		Employee emp = new Employee();
		emp.setName("Satya Deep");
		em.persist(emp);
		
	}
}
