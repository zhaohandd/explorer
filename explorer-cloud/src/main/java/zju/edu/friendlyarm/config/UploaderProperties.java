package zju.edu.friendlyarm.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author xhzhao
 */
@Data
@ConfigurationProperties("uploader")
public class UploaderProperties {

    private String copyImagesTo;

    private String cronExpression;
}
