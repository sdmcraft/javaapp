package misc;

import java.util.HashSet;
import java.util.Set;

import com.google.code.linkedinapi.client.LinkedInApiClient;
import com.google.code.linkedinapi.client.LinkedInApiClientFactory;
import com.google.code.linkedinapi.client.enumeration.PostField;
import com.google.code.linkedinapi.schema.Post;

public class LinkedInDemo {

	public static void main(String[] args) {
		try {
			final String consumerKeyValue = "y0nzebl6oe7a";
			final String consumerSecretValue = "7NSSBlqg8GxXc0GK";
			final String accessTokenValue = "13cf7525-9bae-4169-b82b-2f2301cce6c4";
			final String tokenSecretValue = "0ad89328-dc73-4320-a680-107ef7956d3c";

			final LinkedInApiClientFactory factory = LinkedInApiClientFactory
					.newInstance(consumerKeyValue, consumerSecretValue);
			final LinkedInApiClient client = factory.createLinkedInApiClient(
					accessTokenValue, tokenSecretValue);
			Set<PostField> set = new HashSet<PostField>();
			set.add(PostField.CREATION_TIMESTAMP);
			set.add(PostField.LIKES);
			Post post = client.getPost("g-5076290-S-252439655", set);
			System.out.println(post.getCreationTimestamp());
			System.out.println(post.getLikes());
			int a = 1;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
