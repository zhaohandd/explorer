package zju.edu.frinedlyarm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author xhzhao
 */
@SpringBootApplication
@EnableEurekaServer
public class ExplorerEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExplorerEurekaApplication.class, args);
    }
}
