package post_requests;

import baseUrl.JsonplaceholderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.TodosPojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post03Pojo extends JsonplaceholderBaseUrl {
    /*
         Given
            https://jsonplaceholder.typicode.com/todos
            {
            "userId": 55,
            "title": "Tidy your room",
            "completed": false
            }
        When
            I send POST Request to the Url
        Then
            Status code is 201
        And
            response body is like {
                                    "userId": 55,
                                    "title": "Tidy your room",
                                    "completed": false,
                                    "id": 201
                                    }
     */

    @Test
    public void post03Pojo() {
        // set the url
        spec.pathParam("1","todos");

        // set the expected data
        TodosPojo expectedData=new TodosPojo(55,"Tidy your room",false);
        System.out.println("expectedData = " + expectedData);

        // send request n get response
        Response response=given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{1}");
        response.prettyPrint();

        // do assertion
        TodosPojo actualData=response.as(TodosPojo.class);
        System.out.println("actualData = " + actualData);

        assertEquals(201,response.statusCode());

        assertEquals(expectedData.getUserId(),actualData.getUserId());
        assertEquals(expectedData.getTitle(),actualData.getTitle());
        assertEquals(expectedData.getCompleted(),actualData.getCompleted());

    }
}
