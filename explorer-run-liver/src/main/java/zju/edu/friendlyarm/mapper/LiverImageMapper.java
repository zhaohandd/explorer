package zju.edu.friendlyarm.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import zju.edu.friendlyarm.pojo.LiverImage;

import java.util.List;

/**
 * @author xhzhao
 */
@Service
public interface LiverImageMapper {
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入一条数据
     * @param record
     * @return
     */
    int insert(LiverImage record);

    int insertSelective(LiverImage record);

    /**
     * 根据主键查找
     * @param id
     * @return
     */
    LiverImage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LiverImage record);

    int updateByPrimaryKey(LiverImage record);

    /**
     * 根据账号查找
     * @param doctorNum
     * @param patientNum
     * @return
     */

    List<LiverImage> selectByNum(@Param("doctorNum") Double doctorNum, @Param("patientNum") Double patientNum);
}