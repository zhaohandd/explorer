package zju.edu.friendlyarm.status;

/**
 * @author xhzhao
 */
public enum ResponseStatusEnum implements ResponseStatus {
    /**
     * 请求成功
     */
    OK(10000, "提示", "OK"),

    /**
     * 通用错误
     */
    GENERAL_ERROR(10001, "提示", "网络异常，请重试"),

    /**
     * 参数错误
     */
    ILLEGAL_PARAMETER(10002, "提示", "网络异常，请重试"),

    /**
     * 未经认证 todo.需要优化文案
     */
    UNAUTHORIZED(10003, "提示", "请重新登录"),

    /**
     * 操作失败
     */
    OPERATION_FAILURE(10004, "提示", "操作失败，请重试"),

    REQUEST_HYSTRIX(10005, "提示", "网络异常，请重试"),

    NO_AUTHORITY(10006, "提示", "权限未开通"),

    PARAMETER_NOT_VALID(10007, "提示", "参数有误"),

    SIGN_FAILED(10008, "提示", "验签失败"),

    /**
     * 自定义提示
     */
    CUSTOM_TIP(10009, "提示", "自定义提示"),

    REQUEST_SENTINAL_BLOCK(10010, "提示", "网络异常，请重试"),

    ENTITY_NOT_FOUND(10011, "提示", "对应的信息未找到"),

    JSON_FORMAT_ERROR(10012, "提示", "json格式不正确"),
    PARAM_IS_EMPTY(10013, "提示", "参数缺失");

    private int code;
    private String title;
    private String msg;

    private ResponseStatusEnum(int code, String title, String msg) {
        this.code = code;
        this.title = title;
        this.msg = msg;
    }

    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String getMsg() {
        return this.msg;
    }

    @Override
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public static ResponseStatusEnum valueOf(int code) {
        for (ResponseStatusEnum ele : ResponseStatusEnum.values()) {
            if (ele.getCode() == code) {
                return ele;
            }
        }
        return null;
    }


}
