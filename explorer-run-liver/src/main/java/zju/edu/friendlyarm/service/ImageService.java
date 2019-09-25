package zju.edu.friendlyarm.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author xhzhao
 */
public interface ImageService {

    /**
     * 图片文件的上传
     * @param file
     * @return
     */
    Long createOrUpdate(MultipartFile file);

    /**
     * 模型运行，调用python
     */
    void run(String imageName);




}
