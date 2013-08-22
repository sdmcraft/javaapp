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
    	
    	String host = "http://abc.com";    
    	String endpoint = "/social_properties.json";
    	String key = "xxx";
    	String secret = "yyy";
    	Map<String,String[]> queryParams = new HashMap<String,String[]>();
    	queryParams.put("proxy_company", new String[]{"jjesquire"});

        OAuthService service = new ServiceBuilder().provider(MyApi.class).apiKey(key).apiSecret(secret).build();
        Token accessToken = new Token("", "");
        OAuthRequest request = new OAuthRequest(Verb.GET, host + endpoint);
        buildQuery(request, queryParams);
        service.signRequest(accessToken, request);
        Response response = request.send();        
        System.out.println("Status Code:" + response.getCode());
        System.out.println("Status Code:" + response.getBody());
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
