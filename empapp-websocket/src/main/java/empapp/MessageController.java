package empapp;

import empapp.dto.EmployeeHasBeenCreatedEvent;
import empapp.dto.MessageCommand;
import empapp.dto.MessageDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
@AllArgsConstructor
public class MessageController {

    private SimpMessagingTemplate template;

    @EventListener
    public void handleEvent(EmployeeHasBeenCreatedEvent event) {
        template.convertAndSend("/topic/employees", new MessageDto(event.toString()));
    }

    @MessageMapping("/messages")
    @SendTo("/topic/employees")
    public MessageDto sendMessage(MessageCommand command) {
        log.info("Message has come: {}", command);
        return new MessageDto(command.getText().toUpperCase());
    }
}
