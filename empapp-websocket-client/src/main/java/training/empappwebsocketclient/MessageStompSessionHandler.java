package training.empappwebsocketclient;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;

import java.lang.reflect.Type;

@Slf4j
public class MessageStompSessionHandler implements StompSessionHandler {

    @Override
    public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
        log.info("WebSocket connected");
        session.subscribe("/topic/employees", this);
    }

    @Override
    public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload, Throwable exception) {
        log.error("Error occured", exception);
    }

    @Override
    public void handleTransportError(StompSession session, Throwable exception) {
        log.error("Error occured", exception);
    }

    @Override
    public Type getPayloadType(StompHeaders headers) {
        return MessageDto.class;
    }

    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        if (payload instanceof MessageDto message) {
            log.info("Message has come: {}", message.getText());
        }
        else {
            log.error("Invalid object: {}", payload.getClass().getName());
        }
    }
}
