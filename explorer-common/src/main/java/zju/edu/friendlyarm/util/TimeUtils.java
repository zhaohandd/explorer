package zju.edu.friendlyarm.util;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 获取文件时间工具类
 * @author xhzhao
 */
@Slf4j
public class TimeUtils {

    /**
     * 获取更新的文件的时间
     * @param srcFilename 文件路径
     * @return 时间
     */
    public static String lastModifiedTime(String srcFilename) {
        File file = new File(srcFilename);
        long lastModified = file.lastModified();
        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date(lastModified));
        log.info("Get the time of the file: ", time);
        return time;
    }
}
