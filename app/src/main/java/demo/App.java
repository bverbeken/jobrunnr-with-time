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
        scheduleJob3();
    }

    private static void scheduleJob1() {
        scheduleRecurrently("job1", "*/5 * * * * *", () -> System.out.println("Job 1 " + LocalDateTime.now()));
    }

    private static void scheduleJob2() {
        scheduleRecurrently("job2", "*/5 * * * * *", () -> System.out.println("Job 2 " + getNow()));
    }

    private static void scheduleJob3() {
        scheduleRecurrently("job3", "*/5 * * * * *", Job3::fetchAndHandleNewRecords);
    }

    public static class Job3 {
        public void fetchAndHandleNewRecords() {
            System.out.println("Job 3 " + LocalDateTime.now()); // now the date will be updated on each run.
        }
    }

    public static LocalDateTime getNow() {
        return LocalDateTime.now();
    }
}
