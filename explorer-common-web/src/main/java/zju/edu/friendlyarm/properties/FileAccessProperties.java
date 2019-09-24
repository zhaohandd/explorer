package zju.edu.friendlyarm.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author xhzhao
 */
@Data
@ConfigurationProperties(prefix = "file-access")
public class FileAccessProperties {

    /**
     * 所有用户上传的文件都存在这个文件夹下
     */
    private String storePath;

    /**
     * 文件服务器的url
     */
    private String httpServer;
}
