package empapp;

import empapp.dto.MessageCommand;
import empapp.dto.MessageDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
public class MessageController {

    @MessageMapping("/messages")
    @SendTo("/topic/employees")
    public MessageDto sendMessage(MessageCommand command) {
        log.info("Message has come: {}", command);
        return new MessageDto(command.getText().toUpperCase());
    }
}
