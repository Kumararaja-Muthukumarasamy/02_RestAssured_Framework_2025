package api.endpoints;

import static io.restassured.RestAssured.*;

import api.payloads.UserPOJO;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndPoints {

	public static Response createUser(UserPOJO userData) {

		Response response =	given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(userData)
				.when()
				.post(URLs.post_url);
		return response;
	}

	public static Response getUser(String username) {
		
		Response response = given()
				.pathParam("username", username)
				.when()
				.get(URLs.get_url);

		return response;
	}

	public static Response updateUser(UserPOJO payload, String username) {

		return given()
				.contentType(ContentType.JSON)  
				.accept(ContentType.JSON) 
				.body(payload)
				.pathParam("username", username)
				.when()
				.put(URLs.put_url);

	}

	public static Response deleteUser(String username) {

		return	given()
				.pathParam("username", username)
				.when()
				.delete(URLs.delete_url);
	}

}
