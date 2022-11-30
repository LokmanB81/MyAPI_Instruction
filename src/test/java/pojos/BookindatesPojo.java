package pojos;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class BookindatesPojo {
    // parametresiz cons oluturuyoruz
    public BookindatesPojo(){}

    // 1. tum keyler icin variable r olusturuyoruz
    private String checkin;
    private String checkout;

    // parametreli cons olusturyoruz
    public BookindatesPojo(String checkin,String checkout){
        this.checkin=checkin;
        this.checkout=checkout;
    }

    // public getter ve setter metodları olusturuyoruz
    public String getCheckin() {
        return checkin;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public String getCheckout() {
        return checkout;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }

    // toString metodu olusturuyoruz--- pojo class ı yazdırabilmek icin
    @Override
    public String toString() {
        return "BookindatesPojo{" +
                "checkin='" + checkin + '\'' +
                ", checkout='" + checkout + '\'' +
                '}';
    }


}
