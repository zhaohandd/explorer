package zju.edu.friendlyarm.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author xhzhao
 */
public interface ImageService {

    /**
     * 图片文件的上传
     * @param file 文件
     * @return
     */
    Long createOrUpdate(MultipartFile file);

    /**
     * 模型运行，调用python
     * @param imageName 图片名称
     */
    void run(String imageName);




}
