package org.sdm.timerecord.business.action;

import org.sdm.timerecord.business.Context;

import java.util.Map;


public interface Action
{
    public Map<String, String[]> execute(Map<String, String[]> params, Context ctx) throws Exception;
}
