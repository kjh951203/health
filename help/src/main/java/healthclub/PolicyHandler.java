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
    @Autowired HelpRepository helpRepository;
    @Autowired CallReqListRepository CallReqListRepository;
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverPaymentApproved_HelpRequest(@Payload PaymentApproved paymentApproved){

        if(!paymentApproved.validate()) return;

        System.out.println("\n\n##### listener HelpRequest : " + paymentApproved.toJson() + "\n\n");

        CallReqList callReqList = new CallReqList();
        callReqList.setId(paymentApproved.getId());
        callReqList.setPart(paymentApproved.getPart());
        callReqList.setPrice(paymentApproved.getPrice());
        callReqList.setName(paymentApproved.getName());
        CallReqListRepository.save(callReqList);
        // Sample Logic //
        Help help = new Help();
        helpRepository.save(help);
            
    }


    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString){}


}
