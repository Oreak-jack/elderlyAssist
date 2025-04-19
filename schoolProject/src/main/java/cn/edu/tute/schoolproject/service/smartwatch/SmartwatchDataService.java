package cn.edu.tute.schoolproject.service.smartwatch;

import cn.edu.tute.schoolproject.entity.SmartwatchData;
import java.util.List;

public interface SmartwatchDataService {
    // 保存智能手表数据
    int saveSmartwatchData(SmartwatchData data);

    // 获取用户最新的智能手表数据
    SmartwatchData getLatestDataByUserId(Integer userId);

    // 获取用户指定时间范围内的智能手表数据
    List<SmartwatchData> getDataByUserIdAndTimeRange(Integer userId, String startTime, String endTime);

    // 根据ID获取智能手表数据
    SmartwatchData getDataById(Integer id);

    // 更新智能手表数据
    int updateSmartwatchData(SmartwatchData data);

    // 删除智能手表数据
    int deleteSmartwatchData(Integer id);
}