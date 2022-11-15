package empapp;

import lombok.SneakyThrows;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.DeferredResult;

@Service
public class CalculatorService {

    @SneakyThrows
    @Async
    public void calculate(DeferredResult<Integer> result) {
        Thread.sleep(10_000);
        result.setResult(42);
    }
}
