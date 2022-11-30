package baseUrl;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class AutoExerciseBaseUrl {
    protected RequestSpecification spec;

    @Before
    public void setup() {
        spec = new RequestSpecBuilder().setBaseUri("https://automationexercise.com/api/").build();
    }
}