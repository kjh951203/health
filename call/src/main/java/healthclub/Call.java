package healthclub;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;
import java.util.Date;

@Entity
@Table(name="Call_table")
public class Call {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String price;
    private String status;
    private String part;
    private String name;

    @PostPersist
    public void onPostPersist(){
        CallRequested callRequested = new CallRequested();
        BeanUtils.copyProperties(this, callRequested);
        callRequested.publishAfterCommit();

        //Following code causes dependency to external APIs
        // it is NOT A GOOD PRACTICE. instead, Event-Policy mapping is recommended.

        healthclub.external.Payment payment = new healthclub.external.Payment();
        // mappings goes here
        payment.setId(Long.valueOf(this.getId()));
        payment.setPrice(Integer.valueOf(this.getPrice()));
        payment.setPaymentAction("Approved");
        payment.setName(String.valueOf(this.getName()));
        payment.setPart(String.valueOf(this.getPart()));

        CallApplication.applicationContext.getBean(healthclub.external.PaymentService.class)
            .paymentRequest(payment);


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
