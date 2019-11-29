package zju.edu.friendlyarm.status;

import org.springframework.core.NestedRuntimeException;

import java.io.Serializable;

/**
 * @author xhzhao
 */
@SuppressWarnings("serial")
public class Response<T> implements Serializable {

    @SuppressWarnings("rawtypes")
    public static Response SUCCESS = Response.valueOf(Status.OK);
    @SuppressWarnings("rawtypes")
    public static Response ERROR = Response.valueOf(Status.E500_ERROR);
    @SuppressWarnings("rawtypes")
    public static Response FAILURE = Response.valueOf(Status.E200_FAILURE);

    public static enum Status {
        OK(0, "成功"), E100_FAILURE(-100, "参数异常，请重试"), E200_FAILURE(-200, "操作失败，请重试"), E421_NOT_AUTHORIZED(-421, "身份验证未通过"), E500_ERROR(-500, "服务器异常，请重试"),

        /**
         * 请求成功
         */
        //        OK(10000, "OK"),

        /**
         * 通用错误
         */
        GENERAL_ERROR(10001, "General error"),

        /**
         * 参数错误
         */
        ILLEGAL_PARAMETER(10002, "Illegal parameter"),;

        private int code;
        private String msg;

        private Status(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public int getCode() {
            return this.code;
        }

        public String getMsg() {
            return this.msg;
        }
    }

    @SuppressWarnings("rawtypes")
    public static Response OK = Response.valueOf(ResponseStatusEnum.OK);
    @SuppressWarnings("rawtypes")
    public static Response GENERAL_ERROR = Response.valueOf(ResponseStatusEnum.GENERAL_ERROR);
    @SuppressWarnings("rawtypes")
    public static Response ILLEGAL_PARAMETER = Response.valueOf(ResponseStatusEnum.ILLEGAL_PARAMETER);
    @SuppressWarnings("rawtypes")
    public static Response EXISTS_PHONE = Response.valueOf(10003,"Phone Exists Already");

    protected Response() {
    }

    @SuppressWarnings("rawtypes")
    public static Response valueOf(int code, String msg) {
        Response response = new Response();
        response.setCode(code);
        response.setMsg(msg);
        return response;
    }

    @SuppressWarnings("rawtypes")
    public static Response valueOf(int ddErrorCode, String title, String ddErrorMsg) {
        Response response = new Response();
        response.setCode(ddErrorCode);
        response.setTitle(title);
        response.setMsg(ddErrorMsg);
        return response;
    }

    public static <T> Response<T> valueOf(T data) {
        Response<T> response = new Response<T>();
        response.setCode(ResponseStatusEnum.OK.getCode());
        response.setTitle(ResponseStatusEnum.OK.getTitle());
        response.setMsg(ResponseStatusEnum.OK.getMsg());
        response.setData(data);
        return response;
    }

    public static <T> Response<T> valueOf(ResponseStatus status, T data) {
        Response<T> response = new Response<T>();
        response.setCode(status.getCode());
        response.setTitle(status.getTitle());
        response.setMsg(status.getMsg());
        response.setData(data);
        return response;
    }

    public static <T> Response<T> valueOf(int code, String msg, T data) {
        Response<T> response = new Response<T>();
        response.setCode(code);
        response.setMsg(msg);
        response.setData(data);
        return response;
    }

    public static <T> Response<T> valueOf(ResponseStatus status) {
        Response<T> response = new Response<T>();
        response.setCode(status.getCode());
        response.setTitle(status.getTitle());
        response.setMsg(status.getMsg());
        return response;
    }

    public static <T> Response<T> valueOf(NestedRuntimeException e) {
        Response<T> response = new Response<T>();
        response.setTitle("提示");
        response.setMsg(e.getMessage());
        return response;
    }

    private int code;
    private String title = "提示";   //错误提示场景中，需要增加标题文案
    private String msg;
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Response [code=" + code + ", title=" + title + ", msg=" + msg + ", data=" + data + "]";
    }
}
