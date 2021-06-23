package healthclub;

import healthclub.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class PolicyHandler{
    @Autowired MyPageRepository myPageRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverPaymentApproved_StatusUpdate(@Payload PaymentApproved paymentApproved){

        if(!paymentApproved.validate()) return;

        System.out.println("\n\n##### listener StatusUpdate : " + paymentApproved.toJson() + "\n\n");

        // Sample Logic //

        System.out.println("##### listener  : " + paymentApproved.toJson());

        MyPage mypage = new MyPage();
        mypage.setId(paymentApproved.getId());
        mypage.setPrice(paymentApproved.getPrice());
        mypage.setStatus(paymentApproved.getEventType());
        mypage.setPart(paymentApproved.getPart());
        mypage.setName(paymentApproved.getName());
        myPageRepository.save(mypage);
            
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverHelpAssigned_StatusUpdate(@Payload HelpAssigned helpAssigned){

        if(!helpAssigned.validate()) return;

        System.out.println("\n\n##### listener StatusUpdate : " + helpAssigned.toJson() + "\n\n");


        myPageRepository.findById(helpAssigned.getId()).ifPresent(MyPage ->{
            System.out.println("##### listener  : " + helpAssigned.toJson());
            System.out.println("##### wheneverPickupAssigned_MyPageRepository.findById : exist" );
            MyPage.setTrainer(helpAssigned.getTrainer());
            MyPage.setStatus(helpAssigned.getEventType());
            myPageRepository.save(MyPage);
        });
            
    }


    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString){}


}
