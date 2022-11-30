package get_request;

import baseUrl.RestfulBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class Get05 extends RestfulBaseUrl {
    /*
    Given
        https://restful-booker.herokuapp.com/booking
    When
        User sends get request to the URL
    Then
        Status code is 200
And
   Among the data there should be someone whose firstname is "Ali" and lastname is "Cengiz"
 */

    @Test
    public void name() {
        // set url
        // https://restful-booker.herokuapp.com/booking?firstname=Kimie&lastname=Jackie
        spec.pathParam("first","booking").queryParams("firstname","Kimie","lastname","Jackie");

        // send request n get response
        Response response=given().spec(spec).when().get("/{first}"); // /{first}=booking?firstname=Ali&lastname=Cengiz
        //response.prettyPrint();

        // do assertion
        assertEquals(200,response.getStatusCode());
        assertTrue(response.asString().contains("bookingid"));
    }
}
