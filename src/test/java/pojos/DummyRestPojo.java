package pojos;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DummyRestPojo {
   /* "status": "success",
            "data": {
        "employee_name": "Tom Hanks",
                "employee_salary": 111111,
                "employee_age": 23,
                "profile_image": "Perfect image",
                "id": 4891
    },
            "message": "Successfully! Record has been added."
}

    */

    private String status;
    private DummyRestDataPojo data;
    private String message;

    public DummyRestPojo() {
    }

    public DummyRestPojo(String status, DummyRestDataPojo data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public DummyRestDataPojo getData() {
        return data;
    }

    public void setData(DummyRestDataPojo data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "DummyRestPojo{" +
                "status='" + status + '\'' +
                ", data=" + data +
                ", message='" + message + '\'' +
                '}';
    }
}
