
package healthclub;

public class HelpAssigned extends AbstractEvent {

    private Long id;
    private String trainer;

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

