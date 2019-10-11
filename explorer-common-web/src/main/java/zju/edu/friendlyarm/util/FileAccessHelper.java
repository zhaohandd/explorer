package zju.edu.friendlyarm.util;

import com.google.common.base.Joiner;
import zju.edu.friendlyarm.properties.FileAccessProperties;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Objects;

/**
 * @author xhzhao
 */
public class FileAccessHelper {

    private final FileAccessProperties fileAccessProperties;

    public FileAccessHelper(FileAccessProperties fileAccessProperties) {
        this.fileAccessProperties = fileAccessProperties;
    }

    private static final Joiner FILE_JOINER = Joiner.on("/");

    /**
     * 构造完整的http访问地址
     * @param relativePath 存于数据库中的相对路径
     * @param updateAt 更新时间
     * @return 完整的http访问地址
     */
    public String buildHttpUrl(String relativePath, LocalDateTime updateAt) {
        Objects.requireNonNull(relativePath);
        String url = FILE_JOINER.join(fileAccessProperties.getHttpServer(), relativePath);
        return url;
//        return url + "?time=" + Date.from((updateAt.toInstant(ZoneOffset.of("+8"))));
    }

    /**
     * 构造相对路径，用以存到数据库
     * @param doctorNum 一级目录
     * @param patientNum 二级目录
     * @param fileName 文件名
     * @return 相对路径，用以存到数据库
     */
    public String buildRelativePath(Double doctorNum, Double patientNum, String fileName) {
        Objects.requireNonNull(doctorNum);
        Objects.requireNonNull(patientNum);
        Objects.requireNonNull(fileName);
        return FILE_JOINER.join("image", doctorNum, patientNum, fileName);
    }

    /**
     * 拼装出图片文件在磁盘上的绝对路径
     *
     * @param relativePath 存于数据库中的相对路径
     * @return 图片文件在磁盘上的绝对路径
     */
    public String buildStorePath(String relativePath) {
        Objects.requireNonNull(relativePath);
        return FILE_JOINER.join(fileAccessProperties.getStorePath(), relativePath);
    }
}
