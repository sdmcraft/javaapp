package org.sdm.timerecord.business.action;

import javax.ejb.Remote;

@Remote
public interface CreateUserRemote {
	public void createUser(String name, byte[] image) throws Exception;
}
