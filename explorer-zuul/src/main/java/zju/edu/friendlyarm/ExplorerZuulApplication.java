package zju.edu.friendlyarm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulServer;

/**
 * @author xhzhao
 */
@SpringBootApplication
@EnableEurekaClient
@EnableZuulServer
public class ExplorerZuulApplication {
    public static void main(String[] args) {
        SpringApplication.run(ExplorerZuulApplication.class, args);
    }
}
