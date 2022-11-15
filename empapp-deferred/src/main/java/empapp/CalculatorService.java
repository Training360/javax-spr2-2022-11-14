package empapp;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.DeferredResult;

@Service
@Slf4j
public class CalculatorService {

    @SneakyThrows
    @Async
    public void calculate(DeferredResult<Integer> result) {
        log.info("start calculate");
        Thread.sleep(10_000);
        result.setResult(42);
        log.info("end calculate");
    }
}
