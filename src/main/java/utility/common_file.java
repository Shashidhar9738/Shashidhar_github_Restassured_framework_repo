package utility;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class common_file {

	public static JsonPath jsonpath;

	public static String getResponse(String response_body, String response_obj) {

		jsonpath = new JsonPath(response_body);
		String response_obj_value = jsonpath.getString(response_obj);

		return response_obj_value;

	}

	public static int get_status_code(Response response) {

		int status_code = response.getStatusCode();

		return status_code;

	}

	

}
