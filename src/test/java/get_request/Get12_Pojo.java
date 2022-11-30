package get_request;

import baseUrl.RestfulBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookindatesPojo;
import pojos.BookingPojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get12_Pojo extends RestfulBaseUrl {
     /*
     Given
         https://restful-booker.herokuapp.com/booking/18
     When
    I send GET Request to the URL
   Then
    Status code is 200
And
    Response body is like:
                        {
                             "firstname": "Guoqiang",
    "lastname": "Liu",
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
    public void getPojo(){
        // set the url
        spec.pathParams("1","booking","2","19");

        // set the expected data
        BookindatesPojo bookindatesPojo=new BookindatesPojo("2018-01-01","2019-01-01" );
       System.out.println("bookindatesPojo.toString() = " + bookindatesPojo);
        BookingPojo expectedData=new BookingPojo("Guoqiang", "Liu",111,true,bookindatesPojo,"Breakfast");
        System.out.println("bookingPojo.toString() = " + expectedData);

        // send the request n get the response
        Response response=given().spec(spec).when().get("/{1}/{2}");
        response.prettyPrint();

        // do assertion
        BookingPojo actualData=response.as(BookingPojo.class);
        System.out.println("actualData = " + actualData);

        assertEquals(expectedData.getFirstname(),actualData.getFirstname());
        assertEquals(expectedData.getLastname(),actualData.getLastname());
        assertEquals(expectedData.getTotalprice(),actualData.getTotalprice());
        assertEquals(expectedData.getDepositpaid(),actualData.getDepositpaid());
        assertEquals(expectedData.getAdditionalneeds(),actualData.getAdditionalneeds());

        // 1. yol
        assertEquals(expectedData.getBookingdates().getCheckin(),actualData.getBookingdates().getCheckin());
        assertEquals(expectedData.getBookingdates().getCheckout(),actualData.getBookingdates().getCheckout());

        // 2. yol - 60,61. satirlar icin -- tavsiye edilen--
        assertEquals(bookindatesPojo.getCheckin(),actualData.getBookingdates().getCheckin());
        assertEquals(bookindatesPojo.getCheckout(),actualData.getBookingdates().getCheckout());

    }
}
