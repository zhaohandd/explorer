package zju.edu.friendlyarm.mapper;

import zju.edu.friendlyarm.pojo.LiverImage;

public interface LiverImageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LiverImage record);

    int insertSelective(LiverImage record);

    LiverImage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LiverImage record);

    int updateByPrimaryKey(LiverImage record);
}