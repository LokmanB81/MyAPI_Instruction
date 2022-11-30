package deleteRequests;

import baseUrl.DummyRestApiBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.DummyRestDeletePojo;
import utils.ObjectMapperUtils;

import static io.restassured.RestAssured.given;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class delete02 extends DummyRestApiBaseUrl {
    /*
    Given
     URL: https://dummy.restapiexample.com/api/v1/delete/2

     When
     HTTP Request Method: DELETE Request


     Test Case: Type by using Gherkin Language
     Then
     Assert:     i) Status code is 200
     And
                 ii) "status" is "success"
     And
                 iii) "data" is "2"
     And
                 iv) "message" is "Successfully! Record has been deleted"
       */

    /*
    {
    "status": "success",
    "data": "2",
    "message": "Successfully! Record has been deleted"
}
     */

    @Test
    public void delete02(){

        spec.pathParams("first","delete","second","2");
        DummyRestDeletePojo expectedData=new DummyRestDeletePojo("success","2","Successfully! Record has been deleted");
        System.out.println("expectedData = " + expectedData);
        Response response=given().spec(spec).when().delete("/{first}/{second}");
        response.prettyPrint();

      //  DummyRestDeletePojo actualData=response.as(DummyRestDeletePojo.class);
        DummyRestDeletePojo actualData= ObjectMapperUtils.convertJsonToJava(response.asString(),DummyRestDeletePojo.class);
        System.out.println("actualData = " + actualData);
        response.then().assertThat().statusCode(200);

        assertEquals(expectedData.getStatus(),actualData.getStatus());
       assertEquals(expectedData.getData(), actualData.getData());
        assertEquals(expectedData.getMessage(),actualData.getMessage());



    }
}
