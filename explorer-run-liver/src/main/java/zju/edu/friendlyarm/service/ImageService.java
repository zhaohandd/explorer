package zju.edu.friendlyarm.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @author xhzhao
 */
public interface ImageService {

    /**
     * 上传图片
     * @param doctorNum 医生账号
     * @param patientNum 病人账号
     * @param file 上传的文件
     * @return 成功的id
     * @throws IOException
     */
    Integer createOrUpdate(Double doctorNum, Double patientNum, MultipartFile file) throws IOException;

    /**
     * 模型运行，调用python
     * @param imageName 图片名称
     */
    void run(String imageName);

    /**
     * 根据医生账号和病患账号获取患者记录
     * @param doctorNum 医生账号
     * @param patientNum 病患账号
     * @return url的列表
     */
    List<String> getOldRecords(Double doctorNum, Double patientNum);

}
