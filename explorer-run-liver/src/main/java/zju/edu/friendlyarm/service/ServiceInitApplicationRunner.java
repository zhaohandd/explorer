package zju.edu.friendlyarm.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import zju.edu.als.execption.ExecuteException;
import zju.edu.als.execption.ExecutorNotFoundExecption;
import zju.edu.als.executor.GeneralExecutor;
import zju.edu.als.factory.DefaultExecutorFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author xhzhao
 */
@Component
public class ServiceInitApplicationRunner implements ApplicationRunner {

    private static Logger logger = LoggerFactory.getLogger(ServiceInitApplicationRunner.class);

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(() -> {
            GeneralExecutor general = null;
            try {
                general = DefaultExecutorFactory.getExecutor(GeneralExecutor.class);
                general.execute("python", "service_init.sh");
            } catch (ExecutorNotFoundExecption | ExecuteException executorNotFoundExecption) {
                logger.info("{}执行出错", general);
            }
        });
    }
}