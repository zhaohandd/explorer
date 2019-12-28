package zju.edu.friendlyarm.nettyclient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author xhzhao
 */
@Component
@EnableScheduling
public class ClientWorker {
    private static Logger logger = LoggerFactory.getLogger(ClientWorker.class);
    @Scheduled(cron = "0 0 3 * * ?")
    public void clientReceive() {
        logger.info("client start...");
        NettyClient client = new NettyClient();
        client.start();
        logger.info("client end...");
    }
}
