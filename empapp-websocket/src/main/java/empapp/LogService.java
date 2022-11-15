package empapp;

import empapp.dto.EmployeeHasBeenCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LogService {

    @EventListener
    public void handleEvent(EmployeeHasBeenCreatedEvent event) {
        log.info("Event has arrived: {}", event);
    }
}
