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
    @Autowired CallRepository callRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverHelpAssigned_StatusUpdate(@Payload HelpAssigned helpAssigned){

        if(!helpAssigned.validate()) return;

        System.out.println("\n\n##### listener StatusUpdate : " + helpAssigned.toJson() + "\n\n");

        callRepository.findById(helpAssigned.getId()).ifPresent(Call ->{
            System.out.println("##### wheneverPickupAssigned_MatchRepository.findById : exist" );
            Call.setStatus(helpAssigned.getEventType()); 
            callRepository.save(Call);
        });
            
    }


    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString){}


}
