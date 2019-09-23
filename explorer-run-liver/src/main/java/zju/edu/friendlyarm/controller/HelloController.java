package zju.edu.friendlyarm.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xhzhao
 */
@Api(tags = "Hello接口")
@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello() {
        return "hello explorer";
    }
}
