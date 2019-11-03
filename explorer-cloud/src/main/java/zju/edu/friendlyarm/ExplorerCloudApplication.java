package zju.edu.friendlyarm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import zju.edu.friendlyarm.config.UploaderProperties;

/**
 * @author xhzhao
 */
@SpringBootApplication
@EnableConfigurationProperties(UploaderProperties.class)
public class ExplorerCloudApplication {
    public static void main(String[] args) {
        SpringApplication.run(ExplorerCloudApplication.class, args);
    }
}
