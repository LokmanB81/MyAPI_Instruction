package get_request;

import baseUrl.DummyRestApiBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.DummyRestDataPojo;
import pojos.DummyRestPojo;
import utils.ObjectMapperUtils;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get17 extends DummyRestApiBaseUrl {
    /*
    Given
        URL: https://dummy.restapiexample.com/api/v1/employee/1
    When
        User sends GET Request
    Then
        Status code is 200
    And
        "employee_name" is "Tiger Nixon"
    And
        "employee_salary" is 320800
    And
        "employee_age" is 61
    And
        "status" is "success"
    And
        "message" is "Successfully! Record has been fetched."
     */


    @Test
    public void get17() {
        spec.pathParams("first","employee","second","1");

        DummyRestDataPojo dummyRestDataPojo=new DummyRestDataPojo("Tiger Nixon",320800,61,"");
        DummyRestPojo expectedData=new DummyRestPojo("success",dummyRestDataPojo,"Successfully! Record has been fetched.");

        Response response=given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        DummyRestPojo actualData= ObjectMapperUtils.convertJsonToJava(response.asString(),DummyRestPojo.class);

        response.then().assertThat().statusCode(200);

        assertEquals(expectedData.getStatus(),actualData.getStatus());
        assertEquals(expectedData.getMessage(),actualData.getMessage());

        assertEquals(dummyRestDataPojo.getEmployee_name(),actualData.getData().getEmployee_name());
        assertEquals(dummyRestDataPojo.getEmployee_salary(),actualData.getData().getEmployee_salary());
        assertEquals(dummyRestDataPojo.getEmployee_age(),actualData.getData().getEmployee_age());
        assertEquals(dummyRestDataPojo.getProfile_image(),actualData.getData().getProfile_image());

    }
}
/*
{
    "status": "success",
    "data": {
        "id": 1,
        "employee_name": "Tiger Nixon",
        "employee_salary": 320800,
        "employee_age": 61,
        "profile_image": ""
    },
    "message": "Successfully! Record has been fetched."
}
 */