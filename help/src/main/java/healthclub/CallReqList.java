package healthclub;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="CallReqList_table")
public class CallReqList {

        @Id
        @GeneratedValue(strategy=GenerationType.AUTO)
        private Long id;
        private String price;
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
