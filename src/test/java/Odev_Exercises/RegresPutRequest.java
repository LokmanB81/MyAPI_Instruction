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

public class RegresPutRequest extends RegresBaseUrl {
    /*
    //3: Map ile ve Pojo Class ile ayrı ayrı Gson kullanarak yapınız.

/*
        Given
            1) https://reqres.in/api/users/2
            2) {
                "name": "morpheus",
                "job": "zion president"
                }
        When
            I send Put Request to the Url
        Then
            Status code is 200
            And response body should be like {
                                                "name": "morpheus",
                                                "job": "zion president",
                                                "updatedAt": "2022-10-02T11:35:05.693Z"
                                            }
*/

    @Test
    public void put01Map() {
        spec.pathParams("1","users","2","2");

        RegresTestData regresTestData=new RegresTestData();
        Map<String,String> ecpectedData=regresTestData.expectedRegresDataMethod("morpheus","zion president");

        Response response=given().spec(spec).contentType(ContentType.JSON).body(ecpectedData).when().put("/{1}/{2}");
        response.prettyPrint();

        Map<String,String> actualData=response.as(HashMap.class);

        response.then().statusCode(200);

        assertEquals(ecpectedData.get("name"),actualData.get("name"));
        assertEquals(ecpectedData.get("job"),actualData.get("job"));
    }

    @Test
    public void put01Pojo() {
        spec.pathParams("1","users","2","2");

        RegresPojo ecpectedData=new RegresPojo("morpheus","zion president");
        System.out.println("ecpectedData = " + ecpectedData);

        Response response=given().spec(spec).contentType(ContentType.JSON).body(ecpectedData).when().put("/{1}/{2}");
        response.prettyPrint();

        RegresPojo actualData=response.as(RegresPojo.class);
        System.out.println("actualData = " + actualData);

        response.then().statusCode(200);

        assertEquals(ecpectedData.getName(),actualData.getName());
        assertEquals(ecpectedData.getJob(),actualData.getJob());
    }
}
