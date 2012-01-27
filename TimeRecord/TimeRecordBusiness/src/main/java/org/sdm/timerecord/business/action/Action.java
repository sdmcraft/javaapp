package org.sdm.timerecord.business.action;

import java.util.Map;

public interface Action {

	public void execute(Map<String, String[]> params) throws Exception;
}
