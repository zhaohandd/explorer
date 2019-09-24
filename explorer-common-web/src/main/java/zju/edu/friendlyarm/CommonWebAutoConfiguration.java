package zju.edu.friendlyarm;

import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import zju.edu.friendlyarm.properties.FileAccessProperties;
import zju.edu.friendlyarm.util.FileAccessHelper;

/**
 * 所有web模块都可以依赖此模块
 *
 * @author 陈增辉 on 2019/7/24
 */
@Configuration
@PropertySource(value = "classpath:common-web.properties")
@AutoConfigureBefore(CommonWebAutoConfiguration.class)
@EnableConfigurationProperties({FileAccessProperties.class})
public class CommonWebAutoConfiguration {

    @Bean
    public FileAccessHelper accessHelper(FileAccessProperties fileAccessProperties) {
        return new FileAccessHelper(fileAccessProperties);
    }
}
