package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.code.linkedinapi.client.GroupsApiClient;
import com.google.code.linkedinapi.client.LinkedInApiClientFactory;
import com.google.code.linkedinapi.schema.GroupMembership;
import com.google.code.linkedinapi.schema.GroupMemberships;
import com.google.code.linkedinapi.schema.Post;
import com.google.code.linkedinapi.schema.Posts;

public class LinkedInServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		final String consumerKeyValue = "d7ntgynwx8pt";
		final String consumerSecretValue = "VFg43PjOl1YexRFQ";
		final String accessTokenValue = "44c1ce80-fdd7-41d5-922b-a7f88bdad93f";
		final String tokenSecretValue = "d7e7bd1c-3fe2-4fc0-839b-349957b66695";
		
		final LinkedInApiClientFactory factory = LinkedInApiClientFactory.newInstance(consumerKeyValue, consumerSecretValue);
		final GroupsApiClient client = factory.createLinkedInApiClient(accessTokenValue, tokenSecretValue);
		GroupMemberships memberships = client.getGroupMemberships();
		for (GroupMembership membership : memberships.getGroupMembershipList()) {
			System.out.println(membership.getGroup().getName());
			System.out.println(membership.getGroup().getId());
			System.out.println(membership.getMembershipState().getCode());
			
			Posts posts = client.getPostsByGroup(membership.getGroup().getId());
			for(Post post: posts.getPostList())
			{
				resp.getWriter().println(post.getTitle());
				System.out.println(post.getTitle());
			}			
		}
		resp.getWriter().close();
		
	}
	
	public static void main(String[] args) throws ServletException, IOException {
		LinkedInServlet linkedInServlet = new LinkedInServlet();
		linkedInServlet.doGet(null, null);
	}

}
