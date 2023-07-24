package github_test_cases;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import baseclass.basefile;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import resource.pojoclass;
import utility.Authentication;
import utility.base_url;
import utility.common_file;
import utility.payloadconvertor;

@SuppressWarnings("unused")
public class github_testcases extends basefile {
	public static String endpoint = base_url.getbaseuri("/user/repos");
	public static String bearer_token = Authentication.getbearertoken();
	public static Response response;
	public static ObjectMapper objectmapper;
	RequestSpecification requestSpecification;
	public static int rowcount;
	public static String createTable;
	public static XSSFSheet sh;
	public static String createpojo;
	// public static String name;

	@Test(enabled = true)
	public void create_table() throws IOException {
		CreateRepoDynamic();
		deleteRepository();
		System.out.println(basefile.Excel_Sheet());
	}

	@Test(enabled = false)
	public void CreateRepoDynamic() throws IOException {
		// String request_payload=PayLoadConvertor.generate_Paload("CreateRepo.json");
		// System.out.println(request_payload);
		// i do not have to give it since we will give it dynamically at runtime
		// create object for pojoclass
		pojoclass createpojo = new pojoclass();
		createpojo.setName(basefile.Excel_Sheet());
		createpojo.setDecsription("this_is_created_by_me_on_this_sunday");
		// i have to map this at class level
		objectmapper = new ObjectMapper();
		String data = objectmapper.writerWithDefaultPrettyPrinter().writeValueAsString(createpojo);
		response = basefile.PostRequest(endpoint, data, bearer_token);
		String responseBody = response.getBody().asString();
		System.out.println(responseBody);
		Assert.assertEquals(common_file.getResponse(data, "name"), common_file.getResponse(responseBody, "name"));
		Assert.assertEquals(common_file.get_status_code(response), 201);

	}

	@Test(enabled = false)
	public void deleteRepository() throws IOException {
		String data = objectmapper.writerWithDefaultPrettyPrinter().writeValueAsString(createpojo);
		response = basefile.deleteRequest(endpoint, data, bearer_token);
		String responseBody = response.getBody().asString();
		 System.out.println(response);

//		Assert.assertEquals(common_file.getResponse(data, "name"),
//		common_file.getResponse(responseBody, "name"));
		Assert.assertEquals(common_file.get_status_code(response), 204);
		//String jsonresponse = response.asString();

	}

}
