package post_requests;

import baseUrl.RestfulBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookindatesPojo;
import pojos.BookingPojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post04Pojo extends RestfulBaseUrl {
    /*
         Given
          1)  https://restful-booker.herokuapp.com/booking
          2)   {
                "firstname": "Ali",
                "lastname": "Can",
                "totalprice": 999,
                "depositpaid": true,
                "bookingdates": {
                    "checkin": "2021-09-21",
                    "checkout": "2021-12-21"
                 }
                 }

        When
 		    I send POST Request to the URL
 	    Then
 		    Status code is 200
 		And
 		    Response body is like {
 		                            "bookingid": 16,
 		                            "booking" :{
                                        "firstname": "Ali",
                                        "lastname": "Can",
                                        "totalprice": 999,
                                        "depositpaid": true,
                                        "bookingdates": {
                                            "checkin": "2021-09-21",
                                            "checkout": "2021-12-21"
                                        }
                                        "additionalneeds": "Breakfast"
                                     }
                                  }
     */

    @Test
    public void post04Pojo() {
        spec.pathParam("1","booking");

        BookindatesPojo bookindatesPojo=new BookindatesPojo("2021-09-21","2021-12-21");
        BookingPojo expectedData=new BookingPojo("Ali","Can",999,true,bookindatesPojo,"Breakfast");
        System.out.println("expectedData = " + expectedData);

        Response response=given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{1}");
        response.prettyPrint();

        BookingResponseBodyPojo actualData=response.as(BookingResponseBodyPojo.class);
        System.out.println("actualData = " + actualData);

        assertEquals(200,response.statusCode());

        assertEquals(expectedData.getFirstname(),actualData.getBooking().getFirstname());
        assertEquals(expectedData.getLastname(),actualData.getBooking().getLastname());
        assertEquals(expectedData.getTotalprice(),actualData.getBooking().getTotalprice());
        assertEquals(expectedData.getDepositpaid(),actualData.getBooking().getDepositpaid());
        assertEquals(expectedData.getAdditionalneeds(),actualData.getBooking().getAdditionalneeds());
        assertEquals(expectedData.getBookingdates().getCheckin(),actualData.getBooking().getBookingdates().getCheckin());
        assertEquals(expectedData.getBookingdates().getCheckout(),actualData.getBooking().getBookingdates().getCheckout());




    }
}
