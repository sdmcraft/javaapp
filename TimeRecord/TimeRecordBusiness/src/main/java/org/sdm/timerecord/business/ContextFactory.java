package org.sdm.timerecord.business;

import java.security.Principal;
import java.util.List;
import org.sdm.timerecord.business.Context;
import javax.ejb.Remote;

@Remote
public interface ContextFactory {

	public Context getContext(List<Principal> principals);
}
