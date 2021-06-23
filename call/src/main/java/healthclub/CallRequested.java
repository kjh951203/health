package healthclub;

public class CallRequested extends AbstractEvent {

    private Long id;
    private String price;
    private String status;
    private String part;
    private String name;

    public CallRequested(){
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public String getPart() {
        return part;
    }

    public void setPart(String part) {
        this.part = part;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
