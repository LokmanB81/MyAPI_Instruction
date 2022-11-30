package get_request;

import baseUrl.JsonplaceholderBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class Get07 extends JsonplaceholderBaseUrl {
     /*
      Given
       https://jsonplaceholder.typicode.com/todos
When
    I send GET Request to the URL == > URL'e Get Request gonderin
Then
    1)Status code is 200 == > Status kodu 200 olmali
    2)Print all ids greater than 190 on the console ==> id si 190 dan buyuk olanlari konsola yazdirin
      Assert that there are 10 ids greater than 190 == > 10 tane id nin 190 dan buyuk oldugunu dogrulayin
    3)Print all userIds whose ids are less than 5 on the console ==> id si 5 den kucuk olan tum userid lerini konsolunu yazdirin
      Assert that the number of userIds whose ids are less than 5 is 4 ==> id si 5 den kucuk olan 4 tane userId oldugunu dogrulayin
    4)Print all titles whose ids are less than 5 ==> ıd si 5 den kucuk olan tum basliklari yazdirin
      Assert that "delectus aut autem" is one of the titles whose id is less than 5 ==> id si 5 den kucuk olan datalarin birinin
      basliginin "delectus aut autem" icerdigini dogrulayin
   */

    @Test
    public void test01(){
        // set url
        spec.pathParam("first","todos");

        // send request n get response
        Response response=given().spec(spec).when().get("/{first}");
        //response.prettyPrint();

        //  1)Status code is 200
        response.then().assertThat().statusCode(200);

        // 2)Print all ids greater than 190 on the console
        JsonPath json=response.jsonPath();

        List<Integer> idler=json.getList("findAll{it.id>190}.id"); //groovy language= java temelli programlama dili
       System.out.println("id's that is greater than 190"+idler);               // findAll{it.id>190}.id --- id si 190 dan buyuk olanlarin id leri

        assertEquals("id 190 dan buyuk olan eslesmedi",10,idler.size());

        // 3)Print all userIds whose ids are less than 5 on the console
        List<Integer> userIdler=json.getList("findAll{it.id<5}.userId");
        System.out.println("userIds whose ids are less than 5"+userIdler);

        //Assert that the number of userIds whose ids are less than 5 is 4
        assertEquals("number of userIds whose ids are less than 5 is 4  degil",4,userIdler.size());

        // 4)Print all titles whose ids are less than 5

        List<String> titles=json.getList("findAll{it.id<5}.title"); // id si 5 ten kucuk olanların title ları
        System.out.println("ll titles whose ids are less than 5"+titles);

        // Assert that "delectus aut autem" is one of the titles whose id is less than 5
        assertTrue("id si 5 ten kucuk olan title lardan herhangi bir tanesi icermemektedir",titles.contains("delectus aut autem"));

        assertTrue("id si 5 ten kucuk olan title lardan herhangi bir tanesi icermemektedir",
                titles.stream().anyMatch(t-> t.equals("delectus aut autem")));

    }

}
