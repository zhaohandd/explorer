package zju.edu.friendlyarm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author xhzhao
 */
@SpringBootApplication
@MapperScan("zju.edu.friendlyarm.mapper")
public class ExplorerLiverApplication {
    public static void main(String[] args) {
        SpringApplication.run(ExplorerLiverApplication.class, args);
    }
}
