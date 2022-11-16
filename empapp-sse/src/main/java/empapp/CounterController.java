package empapp;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Controller
@RequestMapping("/api/counter")
@Slf4j
@AllArgsConstructor
public class CounterController {

    private CounterService counterService;

    @GetMapping
    public SseEmitter count() {
        log.info("count start");
        var emitter = new SseEmitter();
        counterService.count(emitter);
        log.info("count end");
        return emitter;
    }
}
