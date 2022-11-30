package get_request;

import baseUrl.RestfulBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class Get06_JsonPath extends RestfulBaseUrl {
    /*
       Given
           https://restful-booker.herokuapp.com/booking/2325
       When
           User send a GET request to the URL
       Then
           HTTP Status Code should be 200
       And
           Response content type is "application/json"
       And
           Response body should be like;
       {
    "firstname": "Bradley",
    "lastname": "Pearson",
    "totalprice": 132,
    "depositpaid": false,
    "bookingdates": {  // outer JSON
        "checkin": "2022-10-27",  // inner JSON
        "checkout": "2022-11-07"   /// inner JSON
    },
    "additionalneeds": "None"
}
    */

@Test
    public void test01(){
    // set url

    spec.pathParams("first","booking","second","2325");

    // send request n get response
    Response response=given().spec(spec).when().get("/{first}/{second}");

    response.prettyPrint();

    // assertion

    // 1 .yol
    response.then().assertThat()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("firstname",equalTo( "Bradley"),
                    "lastname",equalTo("Pearson"),
                    "totalprice",equalTo(132),
                    "depositpaid",equalTo(false),
                    "bookingdates.checkin",equalTo("2022-10-27"),
                    "bookingdates.checkout",equalTo("2022-11-07"),
                    "additionalneeds",equalTo("None"));

    // 2. yol JSON path

    JsonPath json=response.jsonPath();

    assertEquals("Bradley",json.getString("firstname"));// fisrtname  icerigi Bradley esitmi
    assertEquals("Pearson",json.getString("lastname"));
    assertEquals(132,json.getInt("totalprice"));
    assertFalse(json.getBoolean("depositpaid"));
    assertEquals("2022-10-27",json.getString("bookingdates.checkin"));
    assertEquals("2022-11-07",json.getString("bookingdates.checkout"));
    assertEquals("None",json.getString("additionalneeds"));


    // 3. yol
    // softassert class 3 adimda kullanilir

    // 1. adım
    SoftAssert softAssert=new SoftAssert();

    // 2. adim assertion yapma
    softAssert.assertEquals(json.getString("firstname"),"Bradley","First Name Hatali");
    softAssert.assertEquals(json.getString("lastname"),"Pearson","Last Name Hatali");
    softAssert.assertEquals(json.getInt("totalprice"),132,"Total Price Hatali");
    softAssert.assertEquals(json.getBoolean("depositpaid"),false,"Depositpaid Hatali");
    softAssert.assertEquals(json.getString("bookingdates.checkin"),"2022-10-27","checkin hatali");
    softAssert.assertEquals(json.getString("bookingdates.checkout"),"2022-11-07","checkout hatali");
    softAssert.assertEquals(json.getString("additionalneeds"),"None","additionalneeds hatali");
    softAssert.assertAll();
    /*
    son satira  softAssert.assertAll(); yazılmazsa test hatali olsa dahi PASS verir..
    // iii) Dogrulama islemleri sonunda softAssert.assertAll() diyerek yaptigimiz tum dogrulama islemlerinin kontrol edilmesini
// sagliyoruz.
// Eger sistemin sonunda softAssert.assertAlll() kullanmaz isek taleplerimiz hatali olsa bile testimiz pass olacaktır.
     */
}
}
