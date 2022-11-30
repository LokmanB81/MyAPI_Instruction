package post_requests;

import baseUrl.JsonplaceholderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTest_Data;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class Post01 extends JsonplaceholderBaseUrl {
     /*
    Given
      1) https://jsonplaceholder.typicode.com/todos
      2) {
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
    public void post01() {

        // set the url
        spec.pathParam("first","todos");

        // set ecpected data
     /*   Map<String,Object> postData=new HashMap<>();
        postData.put("userId",55);
        postData.put("title","Tidy your room");
        postData.put("completed", false);

      */

        // JsonPlaceHolderTest_Data class taki metodu kullanip exceptedData map'i oluturalim
        JsonPlaceHolderTest_Data jsonPlaceHolderTestData=new JsonPlaceHolderTest_Data();
        Map<String,Object> expectedData= jsonPlaceHolderTestData.expectedDataMethod(55,"Tidy your room",false);


        // send  the request n get the response
       Response response= given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{first}");
       response.prettyPrint();

       // do assertion
        response.then().assertThat().statusCode(201);



       Map<String,Object> actualData=response.as(HashMap.class); // GSON
       assertEquals(expectedData.get("userId"),actualData.get("userId"));
       assertEquals(expectedData.get("title"),actualData.get("title"));
       assertEquals(expectedData.get("completed"),actualData.get("completed"));






    }
}
