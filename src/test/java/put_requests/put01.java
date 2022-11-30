package put_requests;

import baseUrl.JsonplaceholderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTest_Data;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class put01 extends JsonplaceholderBaseUrl {
    /*
    Given
     1) https://jsonplaceholder.typicode.com/todos/198
     2) {
             "userId": 21,
             "title": "Wash the dishes",
             "completed": false
           }
    When
I send PUT Request to the Url
 Then
      Status code is 200
      And response body is like   {
                   "userId": 21,
                   "title": "Wash the dishes",
                   "completed": false
                  }
 */

    @Test
    public void put01() {
        // set the url
        spec.pathParams("1","todos","2",198);

        // set ecpected data
        JsonPlaceHolderTest_Data obj=new JsonPlaceHolderTest_Data();
        Map<String,Object> expectedData=obj.expectedDataMethod(21,"Wash the dishes",false);

        // send the request n get the response
        Response response=given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().put("/{1}/{2}");
        response.prettyPrint();

        // do assertion

        response.then().assertThat().statusCode(200);

        Response response1=given().spec(spec).when().get("/{1}/{2}");

        Map<String,Object> actualData=response.as(HashMap.class);
        assertEquals(expectedData.get("userId"),actualData.get("userId"));
        assertEquals(expectedData.get("title"),actualData.get("title"));
        assertEquals(expectedData.get("completed"),actualData.get("completed"));


    }
}
