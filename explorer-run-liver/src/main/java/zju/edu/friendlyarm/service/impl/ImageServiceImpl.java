package zju.edu.friendlyarm.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import zju.edu.als.execption.ExecuteException;
import zju.edu.als.execption.ExecutorNotFoundExecption;
import zju.edu.als.executor.Python2Executor;
import zju.edu.als.factory.DefaultExecutorFactory;
import zju.edu.friendlyarm.mapper.LiverImageMapper;
import zju.edu.friendlyarm.pojo.LiverImage;
import zju.edu.friendlyarm.properties.FileAccessProperties;
import zju.edu.friendlyarm.service.ImageService;
import zju.edu.friendlyarm.util.FileAccessHelper;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xhzhao
 */
@Slf4j
@Service
public class ImageServiceImpl implements ImageService {

    private static Logger logger = LoggerFactory.getLogger(ImageServiceImpl.class);

    private final FileAccessProperties fileAccessProperties = new FileAccessProperties();

    @Autowired
    private LiverImageMapper imageMapper;
    @Autowired
    private FileAccessHelper fileAccessHelper;

    @Override
    public Integer createOrUpdate(Double doctorNum, Double patientNum, MultipartFile file) throws IOException {
        Map<File, MultipartFile> toSave = new HashMap<>(1);
        String fileName = file.getOriginalFilename();
        if (null != fileName) {
            fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);
            fileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "_" + fileName;
        }
        LiverImage image = new LiverImage();
        image.setDoctorNum(doctorNum);
        image.setPatientNum(patientNum);
        image.setImageName(fileName);
        image.setRelativePath(fileAccessHelper.buildRelativePath(doctorNum, patientNum, fileName));
        imageMapper.insert(image);

        toSave.put(new File(fileAccessHelper.buildStorePath(image.getRelativePath())), file);

        for (Map.Entry<File, MultipartFile> entry : toSave.entrySet()) {
            FileUtils.writeByteArrayToFile(entry.getKey(), entry.getValue().getBytes());
        }

        return image.getId();
    }

    @Override
    public void run(String imageName) {
        Python2Executor python2Executor = null;
        try {
            python2Executor = DefaultExecutorFactory.getExecutor(Python2Executor.class);
            python2Executor.execute(fileAccessProperties.getStorePath() + "testUnet.py", imageName);
        } catch (ExecutorNotFoundExecption | ExecuteException executorNotFoundExecption) {
            logger.info("{}执行出错", python2Executor);
        }
    }

    @Override
    public List<String> getOldRecords(Double doctorNum, Double patientNum) {
        List<String> list = new ArrayList<>();
        List<LiverImage> images = imageMapper.selectByNum(doctorNum, patientNum);
        for (LiverImage image : images) {
            String url = fileAccessHelper.buildHttpUrl(image.getRelativePath(), image.getUpdateAt());
            list.add(url);
        }
        return list;
    }
}
