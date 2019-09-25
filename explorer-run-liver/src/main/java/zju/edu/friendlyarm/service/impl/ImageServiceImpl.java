package zju.edu.friendlyarm.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import zju.edu.als.execption.ExecuteException;
import zju.edu.als.execption.ExecutorNotFoundExecption;
import zju.edu.als.executor.Python2Executor;
import zju.edu.als.factory.DefaultExecutorFactory;
import zju.edu.friendlyarm.service.ImageService;

/**
 * @author xhzhao
 */
@Slf4j
public class ImageServiceImpl implements ImageService {

    private static Logger logger = LoggerFactory.getLogger(ImageServiceImpl.class);

    private static final String HOME = "/home/user/Unet-CT/";

    @Override
    public Long createOrUpdate(MultipartFile file) {
        return null;
    }

    @Override
    public void run(String imageName) {
        Python2Executor python2Executor = null;
        try {
            python2Executor = DefaultExecutorFactory.getExecutor(Python2Executor.class);
            python2Executor.execute(HOME + "testUnet.py", imageName);
        } catch (ExecutorNotFoundExecption | ExecuteException executorNotFoundExecption) {
            logger.info("{}执行出错", python2Executor);
        }
    }
}
