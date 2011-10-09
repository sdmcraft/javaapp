package ejb.session.stateless;

import java.io.Serializable;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ejb.persistance.entities.Employee;

@Stateless
public class SessionBean implements SessionBeanLocal, SessionBeanRemote, Serializable {

	@PersistenceContext(name = "EnterpriseApp")
	EntityManager em;

	public void method() {
		System.out.println("Hello from session bean");

	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void createEmployee() {
		Employee emp = new Employee();
		emp.setName("Satya Deep");
		em.persist(emp);
	}
}
