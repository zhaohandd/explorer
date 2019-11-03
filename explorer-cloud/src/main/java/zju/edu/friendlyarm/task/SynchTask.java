package zju.edu.friendlyarm.task;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 定时任务，执行本地和云上文件的同步任务
 *
 * @author xhzhao
 */
@Component
@Configuration
@EnableScheduling
public class SynchTask {

    /**
     * 每天凌晨1:00执行同步任务
     */
    @Scheduled(cron = "0 0 1 * * ?")
    public void task() {
        // TODO: 全量同步 or 增量同步
        System.out.println("同步任务" + LocalDateTime.now());
    }
}
