package Odev_Exercises;

import baseUrl.RegresBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class RegresPatchRequest extends RegresBaseUrl {
    /*
    //4: Map ile ve Pojo Class ile ayrı ayrı Object Mapper kullanarak yapınız.
  /*
       Given
           1) https://reqres.in/api/users/2
           2) {
                "name": "neo"
               }
       When
            I send PATCH Request to the Url
       Then
             Status code is 200
             And response body is like   {
                                                "name": "neo",
                                                "updatedAt": "2022-10-02T12:53:21.675Z"
                                         }
    */

    @Test
    public void patch01() throws IOException {
        spec.pathParams("1","users","2","2");

        String jsonInString="{\n" +
                "                \"name\": \"neo\"\n" +
                "               }";

        HashMap expectedData=new ObjectMapper().readValue(jsonInString, HashMap.class);
        System.out.println("expectedData = " + expectedData);

        Response response=given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().patch("/{1}/{2}");
        response.prettyPrint();

        HashMap actualData=new ObjectMapper().readValue(response.asString(),HashMap.class);
        assertEquals(200,response.statusCode());

        assertEquals(expectedData.get("name"),actualData.get("name"));
        assertEquals(expectedData.get("job"),actualData.get("job"));
    }


}
