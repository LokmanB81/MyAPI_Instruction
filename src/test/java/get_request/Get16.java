package get_request;

import baseUrl.DummyRestApiBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.awt.geom.RectangularShape;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;

public class Get16 extends DummyRestApiBaseUrl {
    /*
           URL: https://dummy.restapiexample.com/api/v1/employees
           HTTP Request Method: GET Request
           Test Case: Type by using Gherkin Language
           Assert:  i) Status code is 200
                   ii) There are 24 employees
                  iii) "Tiger Nixon" and "Garrett Winters" are among the employees
                   iv) The greatest age is 66
                    v) The name of the lowest age is "Tatyana Fitzpatrick"
                   vi) Total salary of all employees is 6,644,770
    */
    /*
    Given
            https://dummy.restapiexample.com/api/v1/employees

     When
            User sends Get request
     Then
            Status code is 200
      And
            There are 24 employees
      And
            "Tiger Nixon" and "Garrett Winters" are among the employees
      And
            The greatest age is 66
      And
            The name of the lowest age is "Tatyana Fitzpatrick"
      And
            Total salary of all employees is 6,644,770

     */

    @Test
    public void get16(){
        spec.pathParam("1","employees");

        Response response=given().spec(spec).when().get("/{1}");
      //  response.prettyPrint();

        //Status code is 200 , There are 24 employees ,
        // "Tiger Nixon" and "Garrett Winters" are among the employees ,
        response.then().assertThat().statusCode(200)
                .body("data.id",hasSize(24)
                        ,"data.employee_name",hasItems("Garrett Winters","Garrett Winters"));


        // The greatest age is 66
        List<Integer> ages =response.jsonPath().getList("data.employee_age");// body'deki data.employee_age degerlerini list e alalim
        System.out.println("ages = " + ages);
        Collections.sort(ages);
        System.out.println("sorted ages = " + ages);
        System.out.println(ages.get(ages.size()-1));
        assertEquals(66,(int)ages.get(ages.size()-1));


      //  The name of the lowest age is "Tatyana Fitzpatrick"
        String lowestageperson=response.jsonPath().getString("data.findAll{it.employee_age=="+ages.get(0)+"}.employee_name"); // lowestageperson=[Tatyana Fitzpatrick]
        String expectedLowestperson="[Tatyana Fitzpatrick]";
        assertEquals(lowestageperson.toString(),expectedLowestperson);

        //Total salary of all employees is 6,644,770
        List<Integer> salaries=response.jsonPath().getList("data.employee_salary");
        System.out.println("salaries = " + salaries);

        // 1. yol
        Integer totalSalary=0;
        for (Integer w:salaries) {
            totalSalary+=w;
        }
        System.out.println("totalSalary = " + totalSalary);

        Integer expectedTotalSalary=6644770;
        assertEquals(expectedTotalSalary,totalSalary);

        // 2. yol
       Integer totalSalary_2= salaries.stream().reduce(0,(t,u)-> t+u);
       // salaries.stream().reduce(0, Integer::sum);

        assertEquals(expectedTotalSalary,totalSalary_2);
        assertEquals(expectedTotalSalary,salaries.stream().reduce(0,(t,u)-> t+u));

    }
}
