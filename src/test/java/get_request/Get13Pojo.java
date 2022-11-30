package get_request;

import baseUrl.GoRestBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.GoRestDataPojo;
import pojos.GoRestPojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get13Pojo extends GoRestBaseUrl {
    /*
        Given
            https://gorest.co.in/public/v1/users/2508
        When
            User send GET Request to the URL
        Then
            Status Code should be 200
        And
            Response body should be like
          {
            "meta": null,
            "data": {
                "id": 2508,
                "name": "Sharmila Deshpande VM",
                "email": "deshpande_sharmila_vm@becker.name",
                "gender": "female",
                "status": "active"
                 }
          }
    */

    @Test
    public void get13Pojo(){
        // set the url
        spec.pathParams("1","users","2",2508);

        // set the expected data

        GoRestDataPojo goRestDataPojo=new GoRestDataPojo(2508,"Sharmila Deshpande VM","deshpande_sharmila_vm@becker.name",
                "female","active");
        GoRestPojo expectedData=new GoRestPojo(null,goRestDataPojo);

        // send the request n get the response
        Response response=given().spec(spec).when().get("/{1}/{2}");
        response.prettyPrint();

        // do assertion


        GoRestPojo actualData=response.as(GoRestPojo.class);// Json response'ı GoRestPojo data tipinde actualData icine alalim

        assertEquals(200,response.statusCode());
        assertEquals(expectedData.getMeta(),actualData.getMeta());

        assertEquals(goRestDataPojo.getId(),actualData.getData().getId());
        assertEquals(goRestDataPojo.getName(),actualData.getData().getName());
        assertEquals(goRestDataPojo.getEmail(),actualData.getData().getEmail());
        assertEquals(goRestDataPojo.getGender(),actualData.getData().getGender());
        assertEquals(goRestDataPojo.getStatus(),actualData.getData().getStatus());
    }
}
