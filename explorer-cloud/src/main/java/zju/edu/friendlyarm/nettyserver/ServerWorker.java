package zju.edu.friendlyarm.nettyserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;

/**
 * @author xhzhao
 */
@Component
@EnableScheduling
public class ServerWorker {

    private static Logger logger = LoggerFactory.getLogger(ServerWorker.class);

    @Scheduled(cron = "0 0 3 * * ?")
    public void serverSend() {
        logger.info("server start...");
        NettyServer server = new NettyServer();
        server.start(new InetSocketAddress("127.0.0.1", 8090));
        logger.info("server end...");
    }
}
