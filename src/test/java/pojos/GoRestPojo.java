package pojos;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class GoRestPojo {
    /*
    {
            "meta": null,
            "data": {
                "id": 2508,
                "name": "Sharmila Deshpande VM",
                "email": "deshpande_sharmila_vm@becker.name",
                "gender": "female",
                "status": "active"
                 }
          }
     */

    private Object meta; // meta'nin varsayilan degeri null oldugu icin data tipini object yapalim...
    private GoRestDataPojo data;

    public GoRestPojo() { }

    public GoRestPojo(String meta, GoRestDataPojo data) {
        this.meta = meta;
        this.data = data;
    }

    public Object getMeta() {
        return meta;
    }

    public void setMeta(Object meta) {
        this.meta = meta;
    }

    public GoRestDataPojo getData() {
        return data;
    }

    public void setData(GoRestDataPojo data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "GoRestPojo{" +
                "meta='" + meta + '\'' +
                ", data=" + data +
                '}';
    }
}
