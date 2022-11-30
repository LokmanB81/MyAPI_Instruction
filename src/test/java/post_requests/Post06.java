package post_requests;

import baseUrl.DummyRestApiBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.DummyRestDataPojo;
import pojos.DummyRestPojo;
import utils.ObjectMapperUtils;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post06 extends DummyRestApiBaseUrl {
    /*
       URL: https://dummy.restapiexample.com/api/v1/create
       HTTP Request Method: POST Request
       Request body:
                     {
                        "employee_name": "Tom Hanks",
                        "employee_salary": 111111,
                        "employee_age": 23,
                        "profile_image": "Perfect image",
                        "id": 4891
                     }
       Test Case: Type by using Gherkin Language
       Assert:
                i) Status code is 200
                ii) Response body should be like the following
                    {
                        "status": "success",
                        "data": {
                            "employee_name": "Tom Hanks",
                            "employee_salary": 111111,
                            "employee_age": 23,
                            "profile_image": "Perfect image",
                            "id": 4891
                        },
                        "message": "Successfully! Record has been added."
                    }
     */
    /*
    given https://dummy.restapiexample.com/api/v1/create
     When
		 		I send GET Request to the URL
		 		Request body:
                     {
                        "employee_name": "Tom Hanks",
                        "employee_salary": 111111,
                        "employee_age": 23,
                        "profile_image": "Perfect image",
                        "id": 4891
                     }
		 		Then
		 		Status code is 200
		 		And
		 		Response body should be like the following
                    {
                        "status": "success",
                        "data": {
                            "employee_name": "Tom Hanks",
                            "employee_salary": 111111,
                            "employee_age": 23,
                            "profile_image": "Perfect image",
                            "id": 4891
                        },
                        "message": "Successfully! Record has been added."
                    }
     */

    @Test
    public void post06() {
        spec.pathParam("1","create");

        DummyRestDataPojo dummyRestDataPojo=new DummyRestDataPojo("Tom Hanks", 111111,23,"Perfect image");
        DummyRestPojo expectedData=new DummyRestPojo("success",dummyRestDataPojo,"Successfully! Record has been added.");

       Response response=given().spec(spec).contentType(ContentType.JSON).body(dummyRestDataPojo).when().post("/{1}");
        response.prettyPrint();

       DummyRestPojo actualData= ObjectMapperUtils.convertJsonToJava(response.asString(),DummyRestPojo.class);
      //  DummyRestPojo actualData=response.as(DummyRestPojo.class);
        System.out.println("actualData = " + actualData);

        assertEquals(200,response.statusCode());
        assertEquals(expectedData.getStatus(),actualData.getStatus());
        assertEquals(expectedData.getMessage(),actualData.getMessage());

        assertEquals(dummyRestDataPojo.getEmployee_name(),actualData.getData().getEmployee_name());
        assertEquals(dummyRestDataPojo.getEmployee_salary(),actualData.getData().getEmployee_salary());
        assertEquals(dummyRestDataPojo.getEmployee_age(),actualData.getData().getEmployee_age());
        assertEquals(dummyRestDataPojo.getProfile_image(),actualData.getData().getProfile_image());

         }
}
