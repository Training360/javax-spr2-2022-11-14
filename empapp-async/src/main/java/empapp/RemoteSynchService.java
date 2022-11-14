package empapp;

import empapp.entity.Employee;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RemoteSynchService {

    @SneakyThrows
    @Async
    public void sync(Employee employee) {
        log.info("RemoteSynchService thread: {}", Thread.currentThread().getName());
        Thread.sleep(10_000);
        log.info("Employee has been synchronized: {}", employee.getName());
    }
}
