package zju.edu.friendlyarm.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import zju.edu.als.execption.ExecuteException;
import zju.edu.als.execption.ExecutorNotFoundExecption;
import zju.edu.als.executor.GeneralExecutor;
import zju.edu.als.factory.DefaultExecutorFactory;
import zju.edu.friendlyarm.config.UploaderProperties;
import zju.edu.friendlyarm.util.HttpUtils;

/**
 * 定时任务，执行本地和云上文件的同步任务
 *
 * @author xhzhao
 */
@Slf4j
@Component
@Configuration
@EnableScheduling
public class SynchTask {

    @Autowired
    private UploaderProperties properties;

    /**
     * 每天凌晨3:00执行同步任务
     */
    @Scheduled(cron = "0 0 3 * * ?")
    public void picUploadTask() {
        // TODO: scp 免密传输文件
        log.info(" >>> 同步开始 >>> ");
        String copyImagesTo = properties.getCopyImagesTo();
        GeneralExecutor general = null;
        try {
            general = DefaultExecutorFactory.getExecutor(GeneralExecutor.class);
            String ip = HttpUtils.getIp();
            String path = "/home/pi/Documents/data/";
            general.execute("scp -r ", ip + path, copyImagesTo);
        } catch (ExecutorNotFoundExecption | ExecuteException executorNotFoundExecption) {
            log.error("{}同步发生错误", general);
        }
        log.info(" >>> 同步完成 >>> ");
    }
}
