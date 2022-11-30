package get_request;

import baseUrl.RestfulBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;
import pojos.BookindatesPojo;
import pojos.BookingPojo;
import utils.ObjectMapperUtils;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get15 extends RestfulBaseUrl {
    /*
        Given
	            https://restful-booker.herokuapp.com/booking/22
        When
		 		I send GET Request to the URL
		Then
		 		Status code is 200
           {
    "firstname": "Guoqiang",
    "lastname": "Morante Briones",
    "totalprice": 111,
    "depositpaid": true,
    "bookingdates": {
        "checkin": "2018-01-01",
        "checkout": "2019-01-01"
    },
    "additionalneeds": "Breakfast"
}
     */

    @Test
    public void get15() {
        spec.pathParams("1","booking","2",22);

        BookindatesPojo bookindatesPojo=new BookindatesPojo("2018-01-01","2019-01-01");
        System.out.println("bookindatesPojo = " + bookindatesPojo);

        BookingPojo expectedData=new BookingPojo("Guoqiang","Morante Briones",111,
                true,bookindatesPojo,"Breakfast");

        Response response=given().spec(spec).when().get("/{1}/{2}");
      //  response.prettyPrint();

       BookingPojo actualData= ObjectMapperUtils.convertJsonToJava(response.asString(),BookingPojo.class);

        System.out.println("actualData = " + actualData);

        // soft ssertion

        SoftAssert softAssert=new SoftAssert();
        softAssert.assertEquals(response.statusCode(),200,"status code fail");
        softAssert.assertEquals(actualData.getFirstname(),expectedData.getFirstname(),"firstname fail");
        softAssert.assertEquals(actualData.getLastname(),expectedData.getLastname(),"lastname fail");
        softAssert.assertEquals(actualData.getTotalprice(),expectedData.getTotalprice(),"totalprice fail");
        softAssert.assertEquals(actualData.getDepositpaid(),expectedData.getDepositpaid(),"depositpaid fail");
        softAssert.assertEquals(actualData.getAdditionalneeds(),expectedData.getAdditionalneeds(),"additionalneeds fail");
        softAssert.assertEquals(actualData.getBookingdates().getCheckin(),bookindatesPojo.getCheckin(),"checkin fail");
        softAssert.assertEquals(actualData.getBookingdates().getCheckout(),bookindatesPojo.getCheckout(),"checkout fail");
        softAssert.assertAll();

        // Hard assertion
        assertEquals(200,response.statusCode());

        assertEquals(expectedData.getFirstname(),actualData.getFirstname());
        assertEquals(expectedData.getLastname(),actualData.getLastname());
        assertEquals(expectedData.getTotalprice(),actualData.getTotalprice());
        assertEquals(expectedData.getDepositpaid(),actualData.getDepositpaid());
        assertEquals(bookindatesPojo.getCheckin(),actualData.getBookingdates().getCheckin());
        assertEquals(bookindatesPojo.getCheckout(),actualData.getBookingdates().getCheckout());
        assertEquals(expectedData.getAdditionalneeds(),actualData.getAdditionalneeds());

    }
}
