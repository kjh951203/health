package healthclub.external;

public class Payment {

    private Long id;
    private Integer price;
    private String paymentAction;
    private String name;
    private String part;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Integer getPrice() {
        return price;
    }
    public void setPrice(Integer price) {
        this.price = price;
    }
    public String getPaymentAction() {
        return paymentAction;
    }
    public void setPaymentAction(String paymentAction) {
        this.paymentAction = paymentAction;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPart() {
        return part;
    }
    public void setPart(String part) {
        this.part = part;
    }

}
