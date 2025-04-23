package Practise_Package;

import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.*;
import com.microsoft.playwright.options.RequestOptions;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;


public class APIResponseCode
{
	Playwright playwright;
	APIRequestContext requestContext ;
	APIRequest request;
	
	@Test
	public void POST_APIResponseValidation() throws IOException {

        Map<String, Object> data = new HashMap<>();
        data.put("name", "morpheus");
        data.put("job", "leader");

        try (Playwright playwright = Playwright.create()) {
            APIRequestContext requestContext = playwright.request().newContext();

            APIResponse POSTresponse = requestContext.post("https://reqres.in/api/users",
                    RequestOptions.create()
                            .setHeader("Content-Type", "application/json")
                            .setData(data)
            );
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode postJsonResponse = objectMapper.readTree(POSTresponse.body());
            System.out.println("<---------------POST Response Validation ------------------>\n");
            System.out.println("Status Code in Response Status Code: " + POSTresponse.status());
            System.out.println(postJsonResponse.toPrettyString());
            System.out.println("The Newly Created Name ::" + postJsonResponse.get("name").asText());
            System.out.println("The Newly Created Job ::" + postJsonResponse.get("job").asText());
            System.out.println("The Newly Created Id ::" + postJsonResponse.get("id").asText());
            System.out.println("The New Record createdAt ::" +postJsonResponse.get("createdAt").asText());
            requestContext.dispose();
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
	
	@Test
	public void PUT_APIResponseValidation() throws IOException {

        Map<String, Object> data1 = new HashMap<>();
        data1.put("name", "morpheus_TheQA");
        data1.put("job", "leader_Team");

        try (Playwright playwright = Playwright.create()) {
            APIRequestContext requestContext = playwright.request().newContext();

            APIResponse PUTresponse = requestContext.put("https://reqres.in/api/users/2",
                    RequestOptions.create()
                            .setHeader("Content-Type", "application/json")
                            .setData(data1)
            );
            
            
            System.out.println("<---------------PUT Response Validation ------------------>\n");
            
            System.out.println("Status Code in Response Status Code: " + PUTresponse.status());
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode putJsonResponse = objectMapper.readTree(PUTresponse.body());
            System.out.println(putJsonResponse.toPrettyString());
            if(putJsonResponse.get("name")!=null)
            {
            
            	System.out.println("The Updated Name ::" + putJsonResponse.get("name").asText());
            }
            if(putJsonResponse.get("job")!=null)
            {
            	System.out.println("The Updated Job ::" + putJsonResponse.get("job").asText());
            }
            if(putJsonResponse.get("createdAt")!=null)
            {
            	System.out.println("The Updated Records CreatedAt ::" + putJsonResponse.get("createdAt").asText());
            }
        
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }

}
