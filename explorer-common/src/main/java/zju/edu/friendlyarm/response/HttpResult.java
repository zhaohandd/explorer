package zju.edu.friendlyarm.response;

import lombok.Getter;
import lombok.Setter;

/**
 * @author xhzhao
 */
@Getter
@Setter
public class HttpResult<T> {
    private Integer code;
    private String msg;
    private T data;

    public HttpResult() {
        this.code = ErrorCode.SUCCESS.getCode();
    }

    public static <T> HttpResult<T> success() {
        return build(ErrorCode.SUCCESS);
    }

    public static <T> HttpResult<T> success(T data) {
        return build(ErrorCode.SUCCESS.getCode(), ErrorCode.SUCCESS.getMsg(), data);
    }

    public static <T> HttpResult<T> fail() {
        return build(ErrorCode.FAIL);
    }

    public static <T> HttpResult<T> build(T data) {
        return build(ErrorCode.SUCCESS, data);
    }

    public static <T> HttpResult<T> build(ErrorCode code) {
        return build(code.getCode(), code.getMsg());
    }

    public static <T> HttpResult<T> build(Boolean success) {
        return build(success ? ErrorCode.SUCCESS : ErrorCode.FAIL);
    }

    public static <T> HttpResult<T> build(ErrorCode code, T data) {
        return build(code.getCode(), code.getMsg(), data);
    }

    public static <T> HttpResult<T> build(Boolean success, T data) {
        return build(success ? ErrorCode.SUCCESS : ErrorCode.FAIL, data);
    }

    public static <T> HttpResult<T> build(ErrorCode code, String msg) {
        return build(code.getCode(), msg);
    }

    public static <T> HttpResult<T> build(Boolean success, String msg) {
        return build(success ? ErrorCode.SUCCESS : ErrorCode.FAIL, msg);
    }

    public static <T> HttpResult<T> build(ErrorCode code, String msg, T data) {
        return build(code.getCode(), msg, data);
    }

    public static <T> HttpResult<T> build(Boolean success, String msg, T data) {
        return build(success ? ErrorCode.SUCCESS : ErrorCode.FAIL, msg, data);
    }

    public static <T> HttpResult<T> build(int code, String msg) {
        return build(code, msg, null);
    }

    public static <T> HttpResult<T> build(int code, String msg, T data) {
        HttpResult<T> httpResult = new HttpResult<>();
        httpResult.setCode(code);
        httpResult.setMsg(msg);
        httpResult.setData(data);
        return httpResult;
    }



}
