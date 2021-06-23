package healthclub;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;
import java.util.Date;

@Entity
@Table(name="Help_table")
public class Help {

    @Id
    private Long id;
    private String trainer;

    @PostPersist
    public void onPostPersist(){
        HelpAssigned helpAssigned = new HelpAssigned();
        BeanUtils.copyProperties(this, helpAssigned);
        helpAssigned.publishAfterCommit();


    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getTrainer() {
        return trainer;
    }

    public void setTrainer(String trainer) {
        this.trainer = trainer;
    }




}
