package project;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import data.DataResource;

public class HttpPostHandler implements RequestHandler<String, String> {

    @Override
    public String handleRequest(String input, Context context) {
    	
    	
		return DataResource.getInstance().putItem("3", "2014/1/11");
    }

}