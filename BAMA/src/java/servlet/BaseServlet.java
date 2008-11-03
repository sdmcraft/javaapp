/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import entity.BamaAccount;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;

/**
 *
 * @author satyam
 */
public class BaseServlet extends HttpServlet {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        if (session.isNew()) {
            HttpClient client = new HttpClient();
            HttpMethod method = new GetMethod("http://sdmhost-xp.corp.adobe.com/api/xml");
            session.setAttribute("client", client);
            session.setAttribute("method", method);
        }

        int responseCode = -1;

        if ((request.getParameter("action") != null) && !("".equals(request.getParameter("action")))) {

            if ("login".equals(request.getParameter("action"))) {
                responseCode = login(request);
                if(responseCode == 200)
                    response.sendRedirect("http://localhost:8080/BAMA/html/DashboardPG.html");
            } else if ("account-create".equals(request.getParameter("action"))) {
                BamaAccount account = createAccount(request);
                responseCode = syncAccount(request, account);
            }
        }

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet BaseServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Response Code: " + responseCode + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }

    public int login(HttpServletRequest request) throws IOException {
        StringBuilder queryStr = new StringBuilder("");
        HttpSession session = request.getSession();
        HttpClient client = (HttpClient) session.getAttribute("client");
        HttpMethod method = (HttpMethod) session.getAttribute("method");

        queryStr.append("action=login");
        if ((request.getParameter("login") != null) && !("".equals(request.getParameter("login")))) {
            queryStr.append("&login=" + request.getParameter("login"));
        }
        if ((request.getParameter("password") != null) && !("".equals(request.getParameter("password")))) {
            queryStr.append("&password=" + request.getParameter("password"));
        }
        method.setQueryString(queryStr.toString());
        return client.executeMethod(method);
    }

    public int syncAccount(HttpServletRequest request,BamaAccount account) throws IOException {
        StringBuilder queryStr = new StringBuilder("");
        HttpSession session = request.getSession();
        HttpClient client = (HttpClient) session.getAttribute("client");
        HttpMethod method = (HttpMethod) session.getAttribute("method");
        int responseCode = -1;

        queryStr.append("action=account-update");
        if (account.getName() != null) {
            queryStr.append("&name=" + account.getName());
        }
        if (account.getDomainName() != null) {
            queryStr.append("&domain-name=" + account.getDomainName());
        }
        if (account.getSchemaVersion() != -1) {
            queryStr.append("&schema-version=" + Integer.toString(account.getSchemaVersion()));
        }
        if (account.getType() != -1) {
            queryStr.append("&type=" + Integer.toString(account.getType()));
        }
        if (account.getZoneId() != -1) {
            queryStr.append("&zone-id=" + Integer.toString(account.getZoneId()));
        }
        if (account.getDateCreated() != null) {
            queryStr.append("&date-created=" + dateFormat.format(account.getDateCreated()));
        }
        if (account.getDateModified() != null) {
            queryStr.append("&date-modified=" + dateFormat.format(account.getDateModified()));
        }
        if (account.getDateExpired() != null) {
            queryStr.append("&date-expired=" + dateFormat.format(account.getDateExpired()));
        }
        if (account.getComments() != null) {
            queryStr.append("&comment=" + account.getComments());
        }
        if (account.getTemplateId() != -1) {
            queryStr.append("&template-id=" + Integer.toString(account.getTemplateId()));
        }
        if ((request.getParameter("accesskey") != null) && !("".equals(request.getParameter("accesskey")))) {
            queryStr.append("&accesskey=" + request.getParameter("accesskey"));
        }

        method.setQueryString(queryStr.toString());
        responseCode = client.executeMethod(method);
        InputStream response = method.getResponseBodyAsStream();
        return responseCode;

    }

    public BamaAccount createAccount(HttpServletRequest request) {
        BamaAccount account = new BamaAccount();
        if ((request.getParameter("comment") != null) && !("".equals(request.getParameter("comment")))) {
            account.setComments(request.getParameter("comment"));
        }
        if ((request.getParameter("date-created") != null) && !("".equals(request.getParameter("date-created")))) {
            try {
                account.setDateCreated(dateFormat.parse(request.getParameter("date-created")));
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
        }
        if ((request.getParameter("date-expired") != null) && !("".equals(request.getParameter("date-expired")))) {
            try {
                account.setDateExpired(dateFormat.parse(request.getParameter("date-expired")));
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
        }
        if ((request.getParameter("date-modified") != null) && !("".equals(request.getParameter("date-modified")))) {
            try {
                account.setDateModified(dateFormat.parse(request.getParameter("date-modified")));
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
        }
        if ((request.getParameter("disabled") != null) && !("".equals(request.getParameter("disabled")))) {
            try {
                account.setDisabled(dateFormat.parse(request.getParameter("disabled")));
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
        }
        if ((request.getParameter("domain-name") != null) && !("".equals(request.getParameter("domain-name")))) {
            account.setDomainName(request.getParameter("domain-name"));
        }
        if ((request.getParameter("name") != null) && !("".equals(request.getParameter("name")))) {
            account.setName(request.getParameter("name"));
        }
        if ((request.getParameter("payment-type") != null) && !("".equals(request.getParameter("payment-type")))) {
            account.setPaymentType(new Integer(request.getParameter("payment-type")));
        }
        if ((request.getParameter("schema-version") != null) && !("".equals(request.getParameter("schema-version")))) {
            account.setSchemaVersion(Integer.parseInt(request.getParameter("schema-version")));
        }
        if ((request.getParameter("serial-key") != null) && !("".equals(request.getParameter("serial-key")))) {
            account.setSerialKey(request.getParameter("serial-key"));
        }
        if ((request.getParameter("status") != null) && !("".equals(request.getParameter("status")))) {
            account.setStatus(request.getParameter("status"));
        }
        if ((request.getParameter("template-id") != null) && !("".equals(request.getParameter("template-id")))) {
            account.setTemplateId(new Integer(request.getParameter("template-id")));
        }
        if ((request.getParameter("type") != null) && !("".equals(request.getParameter("type")))) {
            account.setType(Integer.parseInt(request.getParameter("type")));
        }
        if ((request.getParameter("zone-id") != null) && !("".equals(request.getParameter("zone-id")))) {
            account.setZoneId(Integer.parseInt(request.getParameter("zone-id")));
        }
        account.setInSync("N");
        /*if ((request.getParameter("account-id") != null) && !("".equals(request.getParameter("account-id")))) {
        account.setAccountId(Integer.parseInt(request.getParameter("account-id")));
        }*/

        persist(account);
        return account;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>    

    protected void persist(BamaAccount account) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("BAMAPU");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(account);
            em.getTransaction().commit();
            em.close();
            emf.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
