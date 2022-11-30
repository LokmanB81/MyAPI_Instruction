package Odev_Exercises;

import baseUrl.RegresBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.RegresPojo;
import test_data.RegresTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class RegresPostRequest extends RegresBaseUrl {
    /*
    //2:  Map ve Pojo Class ile ayrı ayrı yapınız.
        Given
            1) https://reqres.in/api/users
            2) {
                "name": "morpheus",
                "job": "leader"
                }
        When
            I send POST Request to the Url
        Then
            Status code is 201
            And response body should be like {
                                                "name": "morpheus",
                                                "job": "leader",
                                                "id": "496",
                                                "createdAt": "2022-10-04T15:18:56.372Z"
                                              }
*/

    @Test
    public void post01Map() {
        spec.pathParam("1","users");

        RegresTestData regresTestData=new RegresTestData();
        Map<String,String> ecpectedData=regresTestData.expectedRegresDataMethod("morpheus","leader");

        Response response=given().spec(spec).contentType(ContentType.JSON).body(ecpectedData).when().post("/{1}");



        Map<String,String> actualData=response.as(HashMap.class);

        response.then().statusCode(201);

        assertEquals(ecpectedData.get("name"),actualData.get("name"));
        assertEquals(ecpectedData.get("job"),actualData.get("job"));
    }

    @Test
    public void post01Pojo() {
        spec.pathParam("1","users");

        RegresPojo ecpectedData=new RegresPojo("morpheus","leader");
        System.out.println("ecpectedData = " + ecpectedData);

        Response response=given().spec(spec).contentType(ContentType.JSON).body(ecpectedData).when().post("/{1}");
        response.prettyPrint();

        RegresPojo actualData=response.as(RegresPojo.class);
        System.out.println("actualData = " + actualData);

        response.then().statusCode(201);

        assertEquals(ecpectedData.getName(),actualData.getName());
        assertEquals(ecpectedData.getJob(),actualData.getJob());


    }
}
