package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Properties;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.QueueBrowser;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pojo.MyListener;

/**
 * Servlet implementation class for Servlet: JMSservlet
 * 
 */
public class JMSservlet extends javax.servlet.http.HttpServlet implements
		javax.servlet.Servlet {

	static final long serialVersionUID = 1L;

	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public JMSservlet() {
		super();
	}

	public void init() {
		Integer hits = 0;
		getServletContext().setAttribute("hits", hits);

		try {
			Properties properties = new Properties();
			properties.put(Context.INITIAL_CONTEXT_FACTORY,
					"org.jnp.interfaces.NamingContextFactory");
			properties.put(Context.PROVIDER_URL, "jnp://127.0.0.1:1099");

			InitialContext jndiContext = new InitialContext(properties);
			QueueConnectionFactory queueConnectionFactory = (QueueConnectionFactory) jndiContext
					.lookup("ConnectionFactory");
			getServletContext().setAttribute("queueConnectionFactory",
					queueConnectionFactory);

			Queue testQueue = (Queue) jndiContext.lookup("queue/testQueue");
			getServletContext().setAttribute("testQueue", testQueue);

			QueueConnection queueConnection = queueConnectionFactory
					.createQueueConnection();
			getServletContext()
					.setAttribute("queueConnection", queueConnection);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void destroy() {
		try {
			((QueueConnection) getServletContext().getAttribute(
					"queueConnection")).close();
		} catch (JMSException ex) {
			ex.printStackTrace();
		}
	}

	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request,
	 *      HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Queue testQueue = (Queue) getServletContext().getAttribute("testQueue");
		QueueConnection queueConnection = (QueueConnection) getServletContext()
				.getAttribute("queueConnection");
		Integer hits = (Integer) getServletContext().getAttribute("hits");
		PrintWriter out = response.getWriter();
		out.write("<HTML>");
		out.write("<BODY>");
		try {
			queueConnection.start();
			QueueSession queueSession = queueConnection.createQueueSession(
					false, Session.AUTO_ACKNOWLEDGE);

			if ("produce".equals(request.getParameter("action"))) {
				QueueSender queueSender = queueSession.createSender(testQueue);
				TextMessage textMessage = queueSession.createTextMessage();
				textMessage.setText("Message: " + (hits++));
				getServletContext().setAttribute("hits", hits);
				queueSender.send(textMessage);
				out.write("<BR>");
				out.write("Sent " + textMessage.getText());
			} else if ("consume".equals(request.getParameter("action"))) {

				QueueReceiver queueReceiver = queueSession
						.createReceiver(testQueue);
				TextMessage textMessage = null;
				while ((textMessage = (TextMessage) queueReceiver
						.receiveNoWait()) != null) {
					out.write("<BR>");
					out.write(textMessage.getText());
				}
				out.write("<BR>");
				out.write("That's it. No more messages.");
			} else if ("async-consume".equals(request.getParameter("action"))) {
				MessageConsumer consumer = queueSession
						.createConsumer(testQueue);
				MyListener listener = new MyListener();
				consumer.setMessageListener(listener);
				synchronized (pojo.Globals.list) {
					while (pojo.Globals.list.size() == 0) {
						pojo.Globals.list.wait();
					}
					for (String s : pojo.Globals.list) {
						out.write("<BR>");
						out.write(s);
					}
					pojo.Globals.list.clear();
				}
				out.write("<BR>");
				out.write("That's it. No more messages.");
				consumer.close();
			}
			else if ("browse".equals(request.getParameter("action"))) {
				QueueBrowser browser = queueSession.createBrowser(testQueue);
	            Enumeration msgs = browser.getEnumeration();

	            if (!msgs.hasMoreElements()) {
	                out.write("<BR>");
	            	out.write("No messages in queue");
	            } else {
	                while (msgs.hasMoreElements()) {
	                    Message tempMsg = (Message) msgs.nextElement();
	                    out.write("<BR>");
	                    out.write("Message: " + tempMsg);
	                }
	            }
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			// try {
			// queueConnection.close();
			// } catch (Exception ex) {
			// ex.printStackTrace();
			// }
		}
		out.write("</BODY>");
		out.write("</HTML>");
		out.close();

	}

	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request,
	 *      HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
