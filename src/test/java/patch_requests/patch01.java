package patch_requests;

import baseUrl.JsonplaceholderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTest_Data;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class patch01 extends JsonplaceholderBaseUrl {
     /*
    Given
     1) https://jsonplaceholder.typicode.com/todos/198
     2) {
             "title": "Wash the dishes"
           }
    When
I send PATCH Request to the Url
 Then
      Status code is 200
      And response body is like   {
                   "userId": 10,
                   "title": "Wash the dishes",
                   "completed": true,
                   "id": 198
                   }
 */

    @Test
    public void patch01() {
        //set the url
        spec.pathParams("1","todos","2","198");

        // set expected data
        JsonPlaceHolderTest_Data obj=new JsonPlaceHolderTest_Data();
       Map<String,Object> expectedData=obj.expectedDataMethod(null,"Wash the dishes",null);
        System.out.println("expectedData = " + expectedData);

        // send request n get response
        Response response=given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().patch("/{1}/{2}");
        response.prettyPrint();

        // do assertion
        Map<String,Object> actualData=response.as(HashMap.class);
        System.out.println("actualData = " + actualData);

        assertEquals(200,response.statusCode());
        assertEquals(expectedData.get("title"),actualData.get("title"));
    }
}
