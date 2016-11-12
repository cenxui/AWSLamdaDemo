package project;

import org.json.JSONObject;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

/**
 * This class is for aws api gateway method get
 * Since if the declaration of input is String, it will
 * make String serialized exception. 
 * @author cenxui
 * 2016/11/10
 */
public class HttpGetHandler implements RequestHandler<Object, Object> {

    @Override
    public Object handleRequest(Object input, Context context) {
    	String result = input.toString();
    	
    	JSONObject jsonObject = new JSONObject(result.replace("=", ":"));
    	
    	JSONObject query = jsonObject.optJSONObject("params").optJSONObject("querystring");
    	
    	return "name : " + query.optString("name") + " age : " + query.optString("age");
    }

}
