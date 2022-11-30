package get_request;

import baseUrl.AutoExerciseBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

import static io.restassured.RestAssured.*;

public class OdevAutomationExercisesProducts extends AutoExerciseBaseUrl {
    /*
Given
    https://automationexercise.com/api/productsList
When
    User sends a GET Request to the url
Then
    HTTP Status Code should be 200
And
    Content Type should be "text/html; charset=utf-8"
And
    Status Line should be HTTP/1.1 200 OK
And
     There must be 12 Women, 9 Men, 13 Kids usertype in products
  */

    @Test
    public void get01() {

        // set url, send request, get response
        spec.pathParam("first","productsList");

        Response response=given().spec(spec).when().get("/{first}");
     //   response.prettyPrint();

        // do assertion
        JsonPath json=response.jsonPath();
        //json.prettyPrint();

        response.then().statusCode(200).contentType("text/html; charset=utf-8").statusLine("HTTP/1.1 200 OK");

        List<Integer> usertypeWomen=json.getList("products.category.usertype.findAll{it.usertype=='Women'}.id");
        System.out.println("usertypeWomen.size() = " + usertypeWomen.size());

        List<Integer> usertypeMen=json.getList("products.category.usertype.findAll{it.usertype=='Men'}.id");
        System.out.println("usertypeMen.size() = " + usertypeMen.size());

        List<Integer> usertypeKids=json.getList("products.category.usertype.findAll{it.usertype=='Kids'}.id");
        System.out.println("usertypeKids.size() = " + usertypeKids.size());

        SoftAssert softAssert=new SoftAssert();

        softAssert.assertEquals(usertypeWomen.size(),12,"women usertype eslesmiyor");
        softAssert.assertEquals(usertypeMen.size(),9,"men usertype eslesmiyor");
        softAssert.assertEquals(usertypeKids.size(),13,"kids usertype eslesmiyor");
        softAssert.assertAll();
    }
}
