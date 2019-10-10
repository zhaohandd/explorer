package zju.edu.friendlyarm.mapper;

import org.springframework.stereotype.Service;
import zju.edu.friendlyarm.pojo.LiverImage;

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

    LiverImage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LiverImage record);

    int updateByPrimaryKey(LiverImage record);
}