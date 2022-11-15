package empapp;

import empapp.dto.MessageCommand;
import empapp.dto.MessageDto;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class MessageController {

    @MessageMapping("/messages")
    @SendTo("/topic/employees")
    public MessageDto sendMessage(MessageCommand command) {
        return new MessageDto(command.getText());
    }
}
