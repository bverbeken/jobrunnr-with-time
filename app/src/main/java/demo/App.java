package demo;

import org.jobrunr.configuration.JobRunr;
import org.jobrunr.storage.InMemoryStorageProvider;

import java.time.LocalDateTime;

import static org.jobrunr.scheduling.BackgroundJob.scheduleRecurrently;

public class App {

    public static void main(String[] args) {
        JobRunr.configure()
                .useStorageProvider(new InMemoryStorageProvider())
                .useBackgroundJobServer()
                .initialize();
        scheduleJob1();
    }

    private static void scheduleJob1() {
        scheduleRecurrently("job1", "*/5 * * * * *", () -> System.out.println("Job 1 " + LocalDateTime.now()));
    }

    private static void scheduleJob2() {
        scheduleRecurrently("job2", "*/5 * * * * *", () -> System.out.println("Job 2 " + getNow()));
    }

    public static LocalDateTime getNow() {
        return LocalDateTime.now();
    }
}
