package zju.edu.friendlyarm.status;

/**
 * @author xhzhao
 */
public interface ResponseStatus {

    int getCode();

    String getMsg();

    String getTitle();

    default String getMsg(Object... extra) {
        String msg = getMsg();
        if (msg == null) {
            return "";
        }
        if (msg.contains("%s") && extra != null && extra.length > 0) {
            return String.format(msg, extra);
        }
        return msg.replace("%s", "");
    }

}
