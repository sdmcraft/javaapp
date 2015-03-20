package misc;

import java.util.HashMap;
import java.util.Map;

import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.DefaultApi10a;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;


public class OAuthDemo
{
    public static void main(String[] args)
    {
    	
//    	String host = "http://api.linkedin.com";    
//    	String endpoint = "/v1/people/~/group-memberships";
//    	String key = "y0nzebl6oe7a";
//    	String secret = "7NSSBlqg8GxXc0GK";
//    	String accesToken = "13cf7525-9bae-4169-b82b-2f2301cce6c4";
//    	String accesSecret = "0ad89328-dc73-4320-a680-107ef7956d3c";
    	
//    	Map<String,String[]> queryParams = new HashMap<String,String[]>();
//    	queryParams.put("proxy_company", new String[]{"jjesquire"});

    	String host = "http://co-nightly.dev.omniture.com";    
    	//String endpoint = "/linkedin/surfaces";
    	String endpoint = "/linkedin/surfaces";
    	String key = "XOMqd0Gyfr82RAF1AptoZg";
    	String secret = "mCRIvEGG1SuI9GNmtCSxkblIsKxYaggiYzo5QtoGaE";
    	String accesToken = "";
    	String accesSecret = "";

    	Map<String,String[]> queryParams = new HashMap<String,String[]>();
    	queryParams.put("proxy_company", new String[]{"jjesquire"});
    	queryParams.put("proxy_user", new String[]{"jimmy"});
    	//queryParams.put("permission", new String[]{"moderate"});
    	
        OAuthService service = new ServiceBuilder().provider(MyApi.class).apiKey(key).apiSecret(secret).build();
        Token accessToken = new Token(accesToken, accesSecret);
        OAuthRequest request = new OAuthRequest(Verb.GET, host + endpoint);
        buildQuery(request, queryParams);
        service.signRequest(accessToken, request);
        Response response = request.send();        
        System.out.println("Status Code:" + response.getCode());
        System.out.println("Body:" + response.getBody());
    }
    
    private static void buildQuery(OAuthRequest request, Map<String,String[]> queryParams)
    {
    	for(Map.Entry<String, String[]> entry : queryParams.entrySet())
    	{
    		String key = entry.getKey();
    		for(String value : entry.getValue())
    		{
    			request.addQuerystringParameter(key, value);
    		}
    	}
    }

    public static class MyApi extends DefaultApi10a
    {
        @Override
        public String getAccessTokenEndpoint()
        {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public String getAuthorizationUrl(Token arg0)
        {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public String getRequestTokenEndpoint()
        {
            // TODO Auto-generated method stub
            return null;
        }
    }
}
