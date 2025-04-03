package api.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.EndPoints_UsingProperties;
import api.payloads.UserPOJO;
import io.restassured.response.Response;

public class UserTestsWithProperties {
	Faker faker;
	UserPOJO userPayload;

	@BeforeClass
	void setUpData() {

		faker = new Faker();
		userPayload = new UserPOJO();
		userPayload.setId(faker.number().numberBetween(1000, 9999));
		userPayload.setUsername(faker.name().fullName());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().emailAddress());
		userPayload.setPassword(faker.internet().password(5,10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
	}

	@Test (priority = 0)
	public void createUserTest() throws InterruptedException {
		Response response = EndPoints_UsingProperties.createUser(this.userPayload);
		response.then().log().all();
		Thread.sleep(2000);
	}

	@Test (priority = 1, dependsOnMethods = "createUserTest")
	public void getUserTest() throws InterruptedException {

		Response response = EndPoints_UsingProperties.getUser(this.userPayload.getUsername());
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		Thread.sleep(2000);
	}


	@Test (priority = 2, dependsOnMethods = "getUserTest")
	public void updateUserTest() throws InterruptedException {
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().emailAddress());
		Response response = EndPoints_UsingProperties.updateUser(this.userPayload,this.userPayload.getUsername());
		response.then().log().all(); 
		Thread.sleep(2000);
		
	}
	
	@Test (priority = 3, dependsOnMethods = "updateUserTest")
	public void getUserTest1() throws InterruptedException {

		Response response = EndPoints_UsingProperties.getUser(this.userPayload.getUsername());
		response.then().log().all();
		Thread.sleep(2000);
	}

	@Test (priority = 4, dependsOnMethods = "getUserTest1") 
	public void deleteUserTest() throws InterruptedException {
		Thread.sleep(2000);
		Response response = EndPoints_UsingProperties.deleteUser(this.userPayload.getUsername());
		
		response.then().log().body().statusCode(200);
	
	}

}
