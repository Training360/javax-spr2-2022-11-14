package empapp;

import empapp.dto.MessageDto;
import lombok.AllArgsConstructor;
import org.springframework.jms.core.JmsTemplate;

@Gateway
@AllArgsConstructor
public class EventStoreGateway {

    private JmsTemplate jmsTemplate;

    public void sendMessage(String text) {
        jmsTemplate.convertAndSend("eventsQueue", new MessageDto(text));
    }
}
