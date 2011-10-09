package ejb.session.stateless;

import javax.ejb.Remote;

@Remote
public interface SessionBeanRemote {
	public void method();
	public void createEmployee();
}
