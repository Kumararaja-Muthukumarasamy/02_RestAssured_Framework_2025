package api.endpoints;

import static io.restassured.RestAssured.*;

import api.payloads.UserPOJO;
import api.utils.ReadProperties;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class EndPoints_UsingProperties {

	public static Response createUser(UserPOJO userData) {

		Response response =	given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(userData)
				.when()
				.post(ReadProperties.getProperties().getString("post_url"));
		return response;
	}

	public static Response getUser(String username) {
		
		Response response = given()
				.pathParam("username", username)
				.when()
				.get(ReadProperties.getProperties().getString("get_url"));

		return response;
	}

	public static Response updateUser(UserPOJO payload, String username) {

		return given()
				.contentType(ContentType.JSON)  
				.accept(ContentType.JSON) 
				.body(payload)
				.pathParam("username", username)
				.when()
				.put(ReadProperties.getProperties().getString("put_url"));

	}

	public static Response deleteUser(String username) {

		return	given()
				.pathParam("username", username)
				.when()
				.delete(ReadProperties.getProperties().getString("delete_url"));
	}

}
