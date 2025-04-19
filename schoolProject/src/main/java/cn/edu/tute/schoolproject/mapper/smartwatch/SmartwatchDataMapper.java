package cn.edu.tute.schoolproject.mapper.smartwatch;

import cn.edu.tute.schoolproject.entity.SmartwatchData;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface SmartwatchDataMapper {
    // 插入智能手表数据
    int insertSmartwatchData(SmartwatchData data);

    // 根据用户ID获取最新的智能手表数据
    SmartwatchData getLatestDataByUserId(Integer userId);

    // 根据用户ID获取指定时间范围内的智能手表数据
    List<SmartwatchData> getDataByUserIdAndTimeRange(Integer userId, String startTime, String endTime);

    // 根据ID获取智能手表数据
    SmartwatchData getDataById(Integer id);

    // 更新智能手表数据
    int updateSmartwatchData(SmartwatchData data);

    // 删除智能手表数据
    int deleteSmartwatchData(Integer id);
}