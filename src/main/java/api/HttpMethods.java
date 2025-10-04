package api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.equalTo;


public class HttpMethods {
    @Test

    public void getMethod() {
        Response response = RestAssured.get("http://localhost:3000/posts");
        int code = response.getStatusCode();
        System.out.println("code: "+code);
        Assert.assertEquals(200, code);
        response.prettyPrint();
        List<Map<String, Object>> posts = response.jsonPath().getList("");
        Assert.assertFalse("Posts list should not be empty", posts.isEmpty());

// Optional: Validate the newly added post is present
//        boolean found = posts.stream()
//                .anyMatch(post -> "master".equals(post.get("title")) && (Integer) post.get("views") == 470);
//        Assert.assertTrue("Post with title 'master' and views 470 not found", found);

    }

    @Test
    public void postMethod() {
        RequestSpecification request = given();
        request.header("Content-type","application/json");

        JSONObject json = new JSONObject();
        json.put("id", "3");
        json.put("title", "master");
        json.put("views", 470);

        request.body(json.toJSONString());

        Response response = request.post("http://localhost:3000/posts");
        int code = response.getStatusCode();
        System.out.println("code: "+code);
        Assert.assertEquals(201, code);

    }

    @Test
    public void putMethod() {
        RequestSpecification request = given();
        request.header("Content-type","application/json");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", "3");
        jsonObject.put("title", "master");
        jsonObject.put("views", "600");


        request.body(jsonObject.toJSONString());
        Response response = request.put("http://localhost:3000/posts/3");
        int code = response.getStatusCode();
        System.out.println("put code: "+code);
        Assert.assertEquals(200, code);
    }

    @Test //deletes last occurence if duplicated
    public void deleteMethod() {
        RequestSpecification request = given();
        Response response = request.delete("http://localhost:3000/posts/5");
        int code = response.getStatusCode();

        response.then().log().all();
        System.out.println("delete code: "+code);
        Assert.assertEquals(200, code);
    }

    @Test
    public void combinedMethod() {

        RestAssured.baseURI = "http://localhost:3000/posts";

        // Step 2: Create JSON payload
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", "5");
        jsonObject.put("title", "coolie");
        jsonObject.put("views", "9000");

        // Step 3: Send POST request and validate response
        Response response = given()
                .basePath("")
                .contentType(ContentType.JSON)
                .body(jsonObject.toJSONString())
                .when()
                .post()
                .then()
                .statusCode(201) // Expected response code for successful POST
                .body("title", equalTo("coolie"))
                .body("views", equalTo("9000"))
                .log().all() // Log full response
         .extract().response(); // Return response if needed

        // Optional: Print ID and createdAt from response
        String id = response.jsonPath().getString("id");
        String createdAt = response.jsonPath().getString("title");

        System.out.println("User created with ID: " + id);
        System.out.println("Created At: " + createdAt);
    }

    @Test
    public void validateUserList() {
        // Send GET request
        Response response = RestAssured
                .given()
                .baseUri("https://jsonplaceholder.typicode.com")
                .when()
                .get("/users");

        // Assert status code
        Assert.assertEquals(200, response.getStatusCode());

        // Parse JSON array response as List of Maps
        List<Map<String, Object>> users = response.jsonPath().getList("");

        // Assert the list is not empty
        Assert.assertFalse("User list is empty", users.isEmpty());

        // Optional: Check that a user with name "Leanne Graham" exists
        boolean userFound = users.stream()
                .anyMatch(user -> "Leanne Graham".equals(user.get("name")));
        Assert.assertTrue("Expected user not found", userFound);
    }

}
