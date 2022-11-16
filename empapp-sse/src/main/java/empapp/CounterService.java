package empapp;

import empapp.dto.CounterDto;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.UUID;
import java.util.stream.IntStream;

@Service
public class CounterService {

    @Async
    public void count(SseEmitter emitter) {
        IntStream.range(0, 10)
                .peek(i -> {
                    try {
                        Thread.sleep(1_000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                })
                .forEach(i -> {
                    try {
                        var builder = SseEmitter.event()
                                .id(UUID.randomUUID().toString())
                                .comment("Counter message")
                                .name("counter")
                                .reconnectTime(10_000)
                                .data(new CounterDto(i));
                        emitter.send(builder);
                    } catch (IOException e) {
                        throw new RuntimeException("Can not send", e);
                    }
                });
        emitter.complete();
    }
}
