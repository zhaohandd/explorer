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
import zju.edu.als.executor.GeneralExecutor;
import zju.edu.als.executor.Python2Executor;
import zju.edu.als.factory.DefaultExecutorFactory;
import zju.edu.friendlyarm.mapper.LiverImageMapper;
import zju.edu.friendlyarm.pojo.LiverImage;
import zju.edu.friendlyarm.properties.FileAccessProperties;
import zju.edu.friendlyarm.service.ImageService;
import zju.edu.friendlyarm.util.FileAccessHelper;

import javax.validation.constraints.NotNull;
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

    private final String path_cmd = "/home/pi/Documents/Unet-CT/testUnet1022.py" ;
    private final String path_save = "/home/pi/Documents/data/" ;

    @Autowired
    private LiverImageMapper imageMapper;
    @Autowired
    private FileAccessHelper fileAccessHelper;

    @Override
    public Integer createOrUpdate(Integer doctorNum, Integer patientNum, MultipartFile file) throws IOException {
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

        //本机数据保存
        File path = new File(fileAccessHelper.buildStorePath(image.getRelativePath()));
        toSave.put(path, file);


        for (Map.Entry<File, MultipartFile> entry : toSave.entrySet()) {
            FileUtils.writeByteArrayToFile(entry.getKey(), entry.getValue().getBytes());
        }

        return image.getId();
    }

//    @Override
//    public void run(Integer id) {
//        LiverImage image = imageMapper.selectByPrimaryKey(id);
//        Python2Executor python2Executor = null;
//        try {
//            python2Executor = DefaultExecutorFactory.getExecutor(Python2Executor.class);
//            String[] path = new String[1];
//            path[0] = path_save + image.getRelativePath();
//            python2Executor.execute(path_cmd, path);
//        } catch (ExecutorNotFoundExecption | ExecuteException executorNotFoundExecption) {
//            logger.info("{}执行出错", python2Executor);
//        }
//    }

    @Override
    public void run(Integer id) {
        LiverImage image = imageMapper.selectByPrimaryKey(id);
        GeneralExecutor general = null;
        try {
            general = DefaultExecutorFactory.getExecutor(GeneralExecutor.class);
            String path = path_save + image.getRelativePath();
            general.execute("python", path_cmd, path);
        } catch (ExecutorNotFoundExecption | ExecuteException executorNotFoundExecption) {
            logger.info("{}执行出错", general);
        }
    }

    @Override
    public List<String> getOldRecords(Integer doctorNum, Integer patientNum) {
        List<String> list = new ArrayList<>();
        List<LiverImage> images = imageMapper.selectByNum(doctorNum, patientNum);
        for (LiverImage image : images) {
            String url = fileAccessHelper.buildHttpUrl(image.getRelativePath(), image.getUpdateAt());
            list.add(url);
        }
        return list;
    }

    @Override
    public List<String> getResultRecords(Integer doctorNum, Integer patientNum) {
        List<String> list = new ArrayList<>();
        List<LiverImage> images = imageMapper.selectByNum(doctorNum, patientNum);
        for (LiverImage image : images) {
            String name = image.getRelativePath().substring(0, image.getRelativePath().lastIndexOf("."));
            String jpg = image.getRelativePath().substring(image.getRelativePath().lastIndexOf("."));
            String relativePath = name + "_result" + jpg;
            String url = fileAccessHelper.buildHttpUrl(relativePath, image.getUpdateAt());
            list.add(url);
        }
        return list;
    }
}
