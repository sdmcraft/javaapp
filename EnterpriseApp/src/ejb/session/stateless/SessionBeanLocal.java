package ejb.session.stateless;

import javax.ejb.Local;

@Local
public interface SessionBeanLocal {
	public void method();
	public void createEmployee();
}
