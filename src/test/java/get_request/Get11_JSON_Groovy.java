package get_request;

import baseUrl.GoRestBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertTrue;


public class Get11_JSON_Groovy extends GoRestBaseUrl {
    @Test
    public void get01() {
        /*
    Given
        https://gorest.co.in/public/v1/users
    When
        User send GET Request
    Then
        The value of "pagination limit" is 10
    And
        The "current link" should be "https://gorest.co.in/public/v1/users?page=1"
    And
        The number of users should  be 10
    And
        We have at least one "active" status
    And
        Niranjan Gupta, Samir Namboothiri and Gandharva Kaul are among the users
        "Fr. Paramartha Bandopadhyay", "Pres. Amarnath Dhawan" and "Sujata Chaturvedi"
    And
        The female users are less than or equals to male users
 */

        // set url
        spec.pathParam("first","users");

        // send request n get response
        Response response=given().spec(spec).when().get("/{first}");
        response.prettyPrint();

        // do assertion
        response.then().assertThat().statusCode(200)
                .body("meta.pagination.limit",equalTo(10)
        ,"meta.pagination.links.current",equalTo("https://gorest.co.in/public/v1/users?page=1")
        ,"data.id",hasSize(10)
        ,"data.status",hasItem("active")
        ,"data.name",hasItems("Pres. Amarnath Dhawan","Sujata Chaturvedi","Navin Panicker"));

       //  The female users are less than or equals to male users
        // 1. yol
        List<String> genders=response.jsonPath().getList("data.gender");
        System.out.println(genders);

        for (String w:genders ) {
            int numfemales=0;
            if(w.equalsIgnoreCase("female")){
                numfemales++;
            }

            assertTrue(numfemales<= genders.size()-numfemales);

        }


        // 2. yol kadın ve erkek sayilarini Groovy ile bulalim

        List<Integer> numFemales=response.jsonPath().getList("data.findAll{it.gender=='female'}.id");
      //  List<String> numFemalesName=response.jsonPath().getList("data.findAll{it.gender=='female'}.name");
        List<Integer> numMale=response.jsonPath().getList("data.findAll{it.gender=='male'}.id");
      //  List<String> numMaleNames=response.jsonPath().getList("data.findAll{it.gender=='male'}.name");

        assertTrue(numFemales.size()<=numMale.size());

    }
}
