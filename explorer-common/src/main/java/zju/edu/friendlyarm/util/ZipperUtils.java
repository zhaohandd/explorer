package zju.edu.friendlyarm.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Expand;
import org.apache.tools.ant.taskdefs.Zip;
import org.apache.tools.ant.types.FileSet;

import java.io.File;

/**
 * 解压缩工具类
 * @author xhzhao
 */
@Slf4j
public class ZipperUtils {

    private static final String ENCODING = "GBK";

    /**
     * 压缩
     * @param srcPathname 源文件（夹）
     * @param zipFilepath 目标压缩文件
     */
    public static void zip(String srcPathname, String zipFilepath) {
        File file = new File(srcPathname);
        if (!file.exists()) {
            throw new RuntimeException("source file or directory " + srcPathname + " does not exist.");
        }

        Project project = new Project();
        FileSet fileSet = new FileSet();
        fileSet.setProject(project);

        if (file.isDirectory()) {
            fileSet.setDir(file);
        } else {
            fileSet.setFile(file);
        }

        Zip zip = new Zip();
        zip.setProject(project);
        zip.setDestFile(new File(zipFilepath));
        zip.addFileset(fileSet);
        zip.setEncoding(ENCODING);
        zip.execute();
        log.info("Compress Saved: " + zipFilepath);
    }

    /**
     * 解压
     * @param zipFilepath zip压缩
     * @param destDir 目标文件夹
     */
    public static void unzip(String zipFilepath, String destDir) {
        if (!new File(zipFilepath).exists()) {
            throw new RuntimeException("zip file " + zipFilepath + " does not exist.");
        }

        Project project = new Project();
        Expand expand = new Expand();
        expand.setProject(project);
        expand.setTaskType("unzip");
        expand.setTaskName("unzip");
        expand.setEncoding(ENCODING);

        expand.setSrc(new File(zipFilepath));
        expand.setDest(new File(destDir));
        expand.execute();

        log.info("Uncompress Saved: " + destDir);
    }

}
