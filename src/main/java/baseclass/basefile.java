package baseclass;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;


public class basefile {

	RequestSpecification requestSpecification;
	Response response;
	JsonPath jsonpath;
	public static String token;
	public static String bearer_token;
	public static String requesturl;
	public static String createTable;

	public static String Excel_Sheet() throws IOException {
		File excel = new File("src\\test\\resources\\rest_Assured_test_data.xlsx");
		FileInputStream file = new FileInputStream(excel);

		@SuppressWarnings("resource")
		XSSFWorkbook wb = new XSSFWorkbook(file);
		XSSFSheet sh = wb.getSheet("Sheet1");
		int rowcount = sh.getLastRowNum();
		for (int i = 0; i <= rowcount; i++) {
			createTable = sh.getRow(i).getCell(0).getStringCellValue();
			System.out.println(createTable);
		}
		return createTable;
	}

	// What are the different HTTP Methods we use in RestAPI
	// Get //POST //PUT //PATCH //Delete //when ever i create my request

	// Basically why did i create all this methods because//i want reduce my
	// redundancy(reputation)
	// this is a static method because i don't have to create a object for this.
	// my get method will have a body?
	// get method is used only to fetch the data from the server 190
	public static Response GetRequest(String requesturl) {
		RequestSpecification requestSpecification = RestAssured.given();
		requestSpecification.contentType(ContentType.JSON);
		// i am providing the get method along with the request URL

		// This get method is going to execute and fetch the response that response i am
		// string it in a variable
		Response response = requestSpecification.get(requesturl);
		// This return response will be called in my test method //where ever i create a
		// test method to execute i call the return

		return response;
	}

	// post method is used to create the data Login,
	// This is the post request with body
	public static Response PostRequest(String requesturl, String requestpayload, String bearer_token) {
		RequestSpecification requestSpecification = RestAssured.given().body(requestpayload);
		requestSpecification.contentType(ContentType.JSON);
		requestSpecification.header("Authorization", "Bearer " + bearer_token);

		Response resposne = requestSpecification.post(requesturl);

		return resposne;
	}

//	// This is the post request without body
//	public static Response PostRequest(String requesturl, String bearer_token) {
//		RequestSpecification requestSpecification = RestAssured.given();
//		Response resposne = requestSpecification.post(requesturl);
//		requestSpecification.contentType(ContentType.JSON);
//		requestSpecification.header("Authorization", "Bearer " + bearer_token);
//		return resposne;
//	}
	public static Response putRequest(String requesturl, String requestPayload, String bearer_token) {
		RequestSpecification requestspecification = RestAssured.given().body(requestPayload);
		requestspecification.contentType(ContentType.JSON);
		requestspecification.header("Authorization", "Bearer " + bearer_token);
		Response response = requestspecification.post(requesturl);
		return response;
	}

	public static Response deleteRequest(String requesturl, String requestPayload, String bearer_token) {
		RequestSpecification requestspecification = RestAssured.given().body(requestPayload);
		requestspecification.contentType(ContentType.JSON);
		requestspecification.header("Authorization", "Bearer " + bearer_token);
		Response response = requestspecification.delete(requesturl);
		return response;
	}

}
