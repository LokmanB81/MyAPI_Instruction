package get_request;

import baseUrl.JsonplaceholderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Get04_HasSize_HasItem extends JsonplaceholderBaseUrl {
    /*
    Given
        https://jsonplaceholder.typicode.com/todos
    When
   I send a GET request to the Url
 And
     Accept type is "application/json"
 Then
     HTTP Status Code should be 200
 And
     Response format should be "application/json"
 And
     There should be 200 todos
 And
     "quis eius est sint explicabo" should be one of the todos title
 And
     2, 7, and 9 should be among the userIds
 */

    @Test
    public void test01(){

        // set url
        spec.pathParam("first","todos");

        // set expected data(put post patch)

        // send request and get response
        Response response=given().spec(spec).when().accept(ContentType.JSON).get("/{first}");
        //response.prettyPrint();

        // do assertion
        response.then().assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id",hasSize(200), // hasSize : body deki id adedi 200 mu
                        "title",hasItem("quis eius est sint explicabo") // hasItem : body icersindeki title ların herhangi biri  " .... " iceriyor mu
                        ,"userId",hasItems(2,7,9)); // hasItems : body deki userId lerden herhangi birinde 2,7,9 olan varmı (and mantıgı)

    }
}
