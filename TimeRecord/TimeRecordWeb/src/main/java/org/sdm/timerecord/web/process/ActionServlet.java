package org.sdm.timerecord.web.process;

import org.json.JSONObject;
import org.sdm.timerecord.business.Context;
import org.sdm.timerecord.business.action.Action;
import org.sdm.timerecord.web.Utils;

import java.io.IOException;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.naming.InitialContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ActionServlet extends HttpServlet
{
    private static final Map<String, String> actionMap = new HashMap<String, String>();

    static
    {
        actionMap.put("user-profile-update", "TimeRecordEnterprise-1.0/UpdateUser/remote");
        actionMap.put("principal-update", "TimeRecordEnterprise-1.0/UpdatePrincipal/remote");
    }

    private InitialContext initialContext;
    private String jndiProvider;

    @Override
    public void init() throws ServletException
    {
        try
        {
            jndiProvider = getServletContext().getInitParameter("jndi-provider");

            Properties properties = new Properties();
            properties.put(javax.naming.Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
            properties.put(javax.naming.Context.PROVIDER_URL, jndiProvider);
            initialContext = new InitialContext(properties);
        }
        catch (Exception ex)
        {
            throw new ServletException(ex);
        }
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        try
        {
            Action action = (Action) initialContext.lookup(actionMap.get(request.getParameter("action")));

            if (action != null)
            {
                Map<String, Object> result = action.execute(request.getParameterMap(), (Context) request.getSession().getAttribute("Context"));
                JSONObject resultObj = Utils.mapToJSON(result);
                response.setContentType("application/json");
                response.getWriter().println(resultObj.toString());
            }
        }
        catch (Exception ex)
        {
            throw new ServletException(ex);
        }
        finally
        {        	
            response.getWriter().close();
        }
    }
}
