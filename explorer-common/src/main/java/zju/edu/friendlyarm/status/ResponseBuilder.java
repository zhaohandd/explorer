package zju.edu.friendlyarm.status;

/**
 * @author xhzhao
 */
public class ResponseBuilder {

    private ResponseBuilder() {
        throw new IllegalStateException("No instance");
    }

    public static <T> Response<T> ok() {
        return ok(null);
    }

    public static <T> Response<T> ok(T data) {
        Response<T> response = new Response<>();
        response.setCode(ResponseStatusEnum.OK.getCode());
        response.setTitle(ResponseStatusEnum.OK.getTitle());
        response.setMsg(ResponseStatusEnum.OK.getMsg());
        response.setData(data);
        return response;
    }

    public static <T> Response<T> error(ResponseStatus status, Object... extra) {
        Response<T> response = new Response<>();
        response.setCode(status.getCode());
        response.setTitle(status.getTitle());
        response.setMsg(status.getMsg(extra));
        return response;
    }

    public static <T> Response<T> error(Exception customException) {
        Response<T> response = new Response<>();
        response.setTitle("提示");
        response.setMsg(customException.getMessage());
        return response;
    }
}
