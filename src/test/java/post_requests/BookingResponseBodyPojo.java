package post_requests;

import org.codehaus.jackson.annotate.JsonIgnoreProperties; //Bu annotation aynı
// levelde bilinmeyen verileri  görmezden gelerek diğer verilerin bu class tipinde
// Pojo class'a çevrilmesine yarıyor.
import pojos.BookingPojo;
@JsonIgnoreProperties(ignoreUnknown = true)

public class BookingResponseBodyPojo {

    private Integer bookingid;
    private BookingPojo booking;

    /*
    {
 		                            "bookingid": 16,
 		                            "booking" :{
     */

    public BookingResponseBodyPojo() {}

    public BookingResponseBodyPojo(Integer bookingid, BookingPojo booking) {
        this.bookingid = bookingid;
        this.booking = booking;
    }

    public Integer getBookingid() {
        return bookingid;
    }

    public void setBookingid(Integer bookingid) {
        this.bookingid = bookingid;
    }

    public BookingPojo getBooking() {
        return booking;
    }

    public void setBooking(BookingPojo bookingPojo) {
        this.booking = bookingPojo;
    }

    @Override
    public String toString() {
        return "BookingResponseBodyPojo{" +
                "bookingid=" + bookingid +
                ", booking=" + booking +
                '}';
    }
}
