package zju.edu.friendlyarm.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author xhzhao
 */
@AllArgsConstructor
public enum ErrorCode {
    /**
     * 成功
     */
    SUCCESS(200, "success"),
    /**
     * 失败
     */
    FAIL(-1, "fail");

    @Getter
    @Setter
    private int code;
    @Getter
    @Setter
    private String msg;


}
