package Odev_Exercises;

import baseUrl.PetStoreBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import pojos.PetSwaggerPojo;
import test_data.JsonPlaceHolderTest_Data;
import utils.ObjectMapperUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertTrue;

public class PetstoreDeleteRequest extends PetStoreBaseUrl {
    /*
    //5:
    /*
    https://petstore.swagger.io/ documantation adresini kullanarak 'user' oluşturan ve oluşsan bu user'ı silen bir otomasyon kodu yazınız.
     */

    @Test   // user olusturma
    public void petstoreUserPost() {
        /*
       {
    "id": 0,
    "username": "string",
    "firstName": "string",
    "lastName": "string",
    "email": "string",
    "password": "string",
    "phone": "string",
    "userStatus"
         */
        spec.pathParam("first","user");

       PetSwaggerPojo expectedData=new PetSwaggerPojo(100,"abcde","lll","bbb","ccc@gmail.com","123456","5555",0);

        Response response=given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{first}");
        response.prettyPrint();
        response.then().statusCode(200);


        /*
        response body
        {
    "code": 200,
    "type": "unknown",
    "message": "100"
}
    */
        response.then().body("code",equalTo(200)
                ,"type",equalTo("unknown")
                ,"message",equalTo("100"));
    }



    @Test
    public void getUser() {

        spec.pathParams("1","user","2","abcde");
        Response response=given().spec(spec).when().get("/{1}/{2}");
        response.prettyPrint();
    }


    @Test   // user delete
    public void delete01() throws IOException {

        /*
        response body
        {
    "code": 200,
    "type": "unknown",
    "message": "abcde"
}
         */
        spec.pathParams("first","user","second","abcde");

        Response response = given().spec(spec).when().delete("/{first}/{second}");
        response.prettyPrint();



        response.then().statusCode(200);
        response.then().body("code",equalTo(200), "type",equalTo("unknown"),"message",equalTo("abcde"));

    }
}
