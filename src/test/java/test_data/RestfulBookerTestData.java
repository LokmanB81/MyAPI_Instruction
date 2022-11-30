package test_data;

import java.util.HashMap;
import java.util.Map;

public class RestfulBookerTestData {

    public Map<String,String> bookingDatesMap(String checkin, String checkout){
        Map<String,String > dataKeyMap = new HashMap<>();
        dataKeyMap.put("checkin",checkin);
        dataKeyMap.put("checkout",checkout);

        return dataKeyMap;
    }

    public Map<String,Object> expectedDataMethod(String firstname,String lastname,Integer totalprice,
                       Boolean depositpaid,Map<String ,String> bookingdates){

        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("firstname",firstname);
        expectedData.put("lastname",lastname);
        expectedData.put("totalprice",totalprice);
        expectedData.put("depositpaid",depositpaid);
        expectedData.put("bookingdates",bookingdates);


        return expectedData;
    }
}
