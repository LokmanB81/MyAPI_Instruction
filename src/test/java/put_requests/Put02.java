package put_requests;

import baseUrl.DummyRestApiBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.DummyRestDataPojo;
import pojos.DummyRestPojo;
import utils.ObjectMapperUtils;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Put02 extends DummyRestApiBaseUrl {
/*
Given
        URL: https://dummy.restapiexample.com/api/v1/update/21

        When
       HTTP Request Method: PUT Request
       Request body: {
                        "employee_name": "Ali Can",
                        "employee_salary": 111111,
                        "employee_age": 23,
                        "profile_image": "Perfect image"
                     }
       Test Case: Type by using Gherkin Language

       Then
       Assert:
                i) Status code is 200
                And
                ii) Response body should be like the following
                    {
                        "status": "success",
                        "data": {
                            "employee_name": "Ali Can",
                            "employee_salary": 111111,
                            "employee_age": 23,
                            "profile_image": "Perfect image"
                        },
                        "message": "Successfully! Record has been updated."
                    }
     */

    @Test
    public void put02() {
        spec.pathParams("first","update","second","21");
        DummyRestDataPojo dummyRestDataPojo=new DummyRestDataPojo("Ali Can",111111,23,"Perfect image");
        DummyRestPojo expectedData=new DummyRestPojo("success",dummyRestDataPojo,"Successfully! Record has been updated.");

        Response response=given().spec(spec).contentType(ContentType.JSON).body(dummyRestDataPojo).put("/{first}/{second}");
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
