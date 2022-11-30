package post_requests;

import baseUrl.JsonplaceholderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import test_data.JsonPlaceHolderTest_Data;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post05ObjectMapperMap extends JsonplaceholderBaseUrl {
    /*
         Given
           1) https://jsonplaceholder.typicode.com/todos
           2) {
                 "userId": 55,
                 "title": "Tidy your room",
                 "completed": false
               }
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
    public void post05ObjectMapper() throws IOException {
        spec.pathParam("1","todos");

        // set expected data
        //  // json formatindaki request datasini bir string degiskene atayıp objectMapper.readValue ile HasMap'e donusturelim...
      /*  String jsonInString="{\n" +
                "                                    \"userId\": 55,\n" +
                "                                    \"title\": \"Tidy your room\",\n" +
                "                                    \"completed\": false,\n" +
                "                                    \"id\": 201\n" +
                "                                    }";

       */
        JsonPlaceHolderTest_Data obj=new JsonPlaceHolderTest_Data();
        String jsonInString=obj.expectedDataInString(55,"Tidy your room",false);

        HashMap expectedData=new ObjectMapper().readValue(jsonInString, HashMap.class);
        System.out.println("expectedData = " + expectedData);

       Response response= given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{1}");
        response.prettyPrint();

        // do assertion
        // json response'ı string seklinde hasmap'e cevirelim..objectMapper.readValue ile
        HashMap actualData=new ObjectMapper().readValue(response.asString(),HashMap.class);
        assertEquals(201,response.statusCode());

        assertEquals(expectedData.get("userId"),actualData.get("userId"));
        assertEquals(expectedData.get("completed"),actualData.get("completed"));
        assertEquals(expectedData.get("title"),actualData.get("title"));

    }
}
