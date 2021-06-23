
package healthclub;

public class PaymentApproved extends AbstractEvent {

    private Long id;
    private String price;
    private String paymentAction;
    private String name;
    private String part;

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
    public String getStateAction() {
        return paymentAction;
    }

    public void setStateAction(String paymentAction) {
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

