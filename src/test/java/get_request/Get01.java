package get_request;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;

public class Get01 {
    /*
// 1 : Postman, manuel API testleri için kullanılır
// 2 : Otomasyon testleri için de Rest Assured Library kullanılır
// 3 : Otomasyon testlerimizi yaparken aşağıdaki adımları izliyoruz
//   a) Gereksinimleri anlama
//   b) Test Case yazma
//     * Test Case yazımımda "Gherkin" dili kullanılır
//       Bizler yazılım diline hakim olsakta karşımızdaki kişiler hakim olmayabilir
//       Ama Gherkin diliyle yazılan testleri anlamakta zorluk çekmezler
//       Gherkin dilinde kullandığımız keywordler
//         - Given : Ön koşullar
//         - When : Yapılacak aksiyonlar (get, put, post, patch, delete)
//         - Then : Istek yaptıktan sonra (request gönderdikten sonra) doğrulama
//         - And : Çoklu işlemlerde kullanılır
//   c) Test kodlarımızı yazmaya başlarız
//      *i      Set the URL
//      *ii     Set the Expected Data (beklenen datanın oluşturulması post, put, patch)
//      *iii    Type code to request (talep göndermek için kod yazımı)
//      *iv     Do Assertion (doğrulama yapma)
     */
    
    /*
Given
        https://restful-booker.herokuapp.com/booking/101
    When
        User sends a GET Request to the url
    Then
        HTTP Status Code should be 200
    And
        Content Type should be JSON
    And
        Status Line should be HTTP/1.1 200 OK
 */

    @Test
    public void get01() {
        //  * Set the URL
        String url="https://restful-booker.herokuapp.com/booking/101";
        //* Set the Expected Data (beklenen datanın oluşturulması post, put, patch)
        // bizden post,put yada patch istenmedigi icin bu casede kullanmıyoruz

        //* Type code to request (talep göndermek için kod yazımı)
        Response response=given().when().get(url);
        response.prettyPrint();

        //  * Do Assertion (doğrulama yapma)
        response.then().assertThat().statusCode(200).contentType("application/json").statusLine("HTTP/1.1 200 OK");

        // status code konsola yazdıralim
        System.out.println("Status code:"+response.getStatusCode());

        // header konsola yazdiralim
        System.out.println("Header : "+response.getHeader("Server"));

        // headers konsola yazdiralim
        System.out.println("Headers: "+response.getHeaders());

        // Time konsola yazdiralim
        System.out.println("time : "+response.getTime());

        // content type konsola yazdıralim
        System.out.println("Content type : "+ response.contentType());

        // status line yazdiralim
        System.out.println("status line : "+response.statusLine());

    }
}
