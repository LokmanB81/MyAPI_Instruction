package Odev_Exercises;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class AutomationExercisesGetRequest {
    /*
        Given
            https://automationexercise.com/api/brandsList
        When
            User sends a GET Request to the url
        Then
            HTTP Status Code should be 200
        And
            Content Type should be "text/html; charset=utf-8"
        And
            Status Line should be HTTP/1.1 200 OK
        And
             Number of H&M brands must be equal to Polo(H&M marka sayısı Polo marka sayısına eşit olmalı)
*/

    @Test
    public void get01() {

        Response response=given().when().get("https://automationexercise.com/api/brandsList");
      //  response.prettyPrint();
        JsonPath json=response.jsonPath();
        //json.prettyPrint();

        response.then().statusCode(200).contentType("text/html; charset=utf-8").statusLine("HTTP/1.1 200 OK");

        List<Integer> HMBrandsNumber=json.getList("brands.findAll{it.brand=='H&M'}.id");
        List<Integer> PoloBrandsNumber=json.getList("brands.findAll{it.brand=='Polo'}.id");

        System.out.println(HMBrandsNumber.size()+ "********"+PoloBrandsNumber.size());

        assertEquals(HMBrandsNumber.size(),PoloBrandsNumber.size());

    }
}
