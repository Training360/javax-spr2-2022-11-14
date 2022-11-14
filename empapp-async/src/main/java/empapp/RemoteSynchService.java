package empapp;

import empapp.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class RemoteSynchService {

    private TaskExecutor taskExecutor;

    @SneakyThrows
    @Async
    public void sync(Employee employee) {
        log.info("RemoteSynchService thread: {}", Thread.currentThread().getName());
        Thread.sleep(10_000);
        log.info("Employee has been synchronized: {}", employee.getName());
    }

    public void syncProgrammable(Employee employee) {
        log.info("RemoteSynchService thread: {}", Thread.currentThread().getName());

        taskExecutor.execute(() -> {
            try {
                Thread.sleep(10_000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            log.info("RemoteSynchService thread: {}", Thread.currentThread().getName());
            log.info("Employee has been synchronized: {}", employee.getName());
        });
    }
}
